package com.eros.demo.common.poi;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eros.demo.common.poi.exception.UnsupportExcelException;
import com.eros.demo.common.poi.support.ExcelExtension;
import com.eros.demo.common.poi.support.ExcelSimpleTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Excel读取器
 * 
 * @author guanheng
 *
 */
public class ExcelReader {

	private ExcelReader() {
	}

	/**
	 * 从excel读取简单一维表
	 * 
	 * @param is
	 *            excel文件流
	 * @param extension
	 *            文件后缀名，例如：xlsx
	 * @return
	 * @throws UnsupportExcelException
	 * @throws IOException
	 */
	public static List<ExcelSimpleTable> readExcelSimpleTable(InputStream is, String extension) throws UnsupportExcelException, IOException {
		return readExcelSimpleTable(is, extension, null);
	}

	/**
	 * 从excel读取简单一维表
	 * 
	 * @param is
	 *            excel文件流
	 * @param extension
	 *            文件后缀名，例如：xlsx
	 * 
	 * @param includeSheetNames
	 *            需要读取的sheet名称集合，为null表示读取全部sheet
	 * @return
	 * @throws UnsupportExcelException
	 * @throws IOException
	 */
	public static List<ExcelSimpleTable> readExcelSimpleTable(InputStream is, String extension, List<String> includeSheetNames) throws UnsupportExcelException, IOException {
		Workbook workbook = null;
		try {
			if (ExcelExtension.XLS.value().equals(extension)) {
				workbook = new HSSFWorkbook(is);
			}
			else if (ExcelExtension.XLSX.value().equals(extension)) {
				workbook = new XSSFWorkbook(is);
			}
			else {
				throw new UnsupportExcelException(extension);
			}

			return readSimpleTable(workbook, includeSheetNames);
		}
		finally {
			IOUtils.closeQuietly(workbook);
		}
	}

	private static List<ExcelSimpleTable> readSimpleTable(Workbook workbook, List<String> includeSheetNames) {
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		List<ExcelSimpleTable> simpleTables = Lists.newArrayList();

		// Read the sheet
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			Sheet sheet = workbook.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			else if (includeSheetNames != null && !includeSheetNames.contains(sheet.getSheetName())) {
				continue;
			}

			ExcelSimpleTable simpleTable = new ExcelSimpleTable();
			simpleTable.setSheetName(sheet.getSheetName());

			if (sheet.getLastRowNum() > 0) {
				List<Map<String, String>> datas = Lists.newArrayList();
				Row headerRow = sheet.getRow(0);
				int cellCount = headerRow.getLastCellNum();
				String[] headers = new String[cellCount];
				for (int i = 0; i < cellCount; i++) {
					headers[i] = getCellStringValue(headerRow.getCell(i), evaluator);
				}

				// Read the row data
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					Row dataRow = sheet.getRow(i);
					if (dataRow != null) {
						Map<String, String> data = Maps.newHashMap();
						for (int j = 0; j < cellCount; j++) {
							data.put(headers[j], getCellStringValue(dataRow.getCell(j), evaluator));
						}

						datas.add(data);
					}
				}

				simpleTable.setDatas(datas);
			}

			simpleTables.add(simpleTable);
		}

		return simpleTables;
	}

	private static String getCellStringValue(Cell cell, FormulaEvaluator evaluator) {
		if (cell == null) {
			return "";
		}

		String strCell;
		switch (cell.getCellType()) {
		case STRING:
			strCell = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				// 如果是date类型则 ，获取该cell的date值
				strCell = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DateUtil.getJavaDate(cell.getNumericCellValue()));
			}
			else {
				// 纯数字
				strCell = String.valueOf(cell.getNumericCellValue());
				strCell = strCell.replaceAll("0+?$", "").replaceAll("[.]$", "");
			}
			break;
		case BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA:
			strCell = getCellStringValue(evaluator.evaluate(cell));
			break;
		default:
			strCell = "";
			break;
		}
		return strCell.trim();
	}

	private static String getCellStringValue(CellValue cellValue) {
		if (cellValue == null) {
			return "";
		}

		String strCell;
		switch (cellValue.getCellType()) {
		case STRING:
			strCell = cellValue.getStringValue();
			break;
		case NUMERIC:
			strCell = String.valueOf(cellValue.getNumberValue());
			strCell = strCell.replaceAll("0+?$", "").replaceAll("[.]$", "");
			break;
		case BOOLEAN:
			strCell = String.valueOf(cellValue.getBooleanValue());
			break;
		default:
			strCell = "";
			break;
		}
		return strCell.trim();
	}
}
