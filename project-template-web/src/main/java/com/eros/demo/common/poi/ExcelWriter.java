package com.eros.demo.common.poi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eros.demo.common.poi.support.ExcelColumnTitle;
import com.eros.demo.common.poi.support.ExcelSimpleTable;
import com.google.common.collect.Lists;

/**
 * Excel写入器
 * 
 * @author guanheng
 *
 */
public class ExcelWriter {

	private static final String DEFAULT_SHEET_NAME = "Sheet1";

	private ExcelWriter() {
	}

	public static byte[] writeExcelSimpleTable(ExcelSimpleTable simpleTable) throws IOException {
		List<ExcelSimpleTable> simpleTables = Lists.newArrayList(simpleTable);
		return writeExcelSimpleTable(simpleTables);
	}

	public static byte[] writeExcelSimpleTable(List<ExcelSimpleTable> simpleTables) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			writeExcelSimpleTable(simpleTables, os);
			return os.toByteArray();
		}
		finally {
			IOUtils.closeQuietly(os);
		}
	}

	/**
	 * 将简单一维表数据写入excel文件
	 * 
	 * @param simpleTable
	 * @param os
	 * @throws IOException
	 */
	public static void writeExcelSimpleTable(ExcelSimpleTable simpleTable, OutputStream os) throws IOException {
		List<ExcelSimpleTable> simpleTables = Lists.newArrayList(simpleTable);
		writeExcelSimpleTable(simpleTables, os);
	}

	/**
	 * 将简单一维表数据写入excel文件（多个sheet）
	 * 
	 * @param simpleTables
	 * @param os
	 * @throws IOException
	 */
	public static void writeExcelSimpleTable(List<ExcelSimpleTable> simpleTables, OutputStream os) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		try {
			for (ExcelSimpleTable simpleTable : simpleTables) {
				String sheetName = simpleTable.getSheetName();
				Sheet sheet = workbook.createSheet(StringUtils.isEmpty(sheetName) ? DEFAULT_SHEET_NAME : sheetName);

				List<String> columnKeys = Lists.newArrayList();
				boolean isColumnTitleInit = false;
				int hdCellIdx = 0;
				if (simpleTable.getColumnTitles() != null && !simpleTable.getColumnTitles().isEmpty()) {
					// 根据定义初始化表头信息
					Row headRow = sheet.createRow(0);
					for (ExcelColumnTitle title : simpleTable.getColumnTitles()) {
						Cell cell = headRow.createCell(hdCellIdx);
						cell.setCellValue(title.getValue());
						sheet.autoSizeColumn(hdCellIdx);
						columnKeys.add(title.getKey());
						hdCellIdx++;
					}

					isColumnTitleInit = true;
				}

				List<Map<String, String>> datas = simpleTable.getDatas();
				if (!datas.isEmpty()) {
					if (!isColumnTitleInit) {
						// 表头为初始化，自动取字段名作为表头
						Row headRow = sheet.createRow(0);
						for (Map.Entry<String, String> dataEntry : datas.get(0).entrySet()) {
							Cell cell = headRow.createCell(hdCellIdx);
							cell.setCellValue(dataEntry.getKey());
							sheet.autoSizeColumn(hdCellIdx);
							columnKeys.add(dataEntry.getKey());
							hdCellIdx++;
						}
					}

					int bdRowIdx = 1;
					for (Map<String, String> map : datas) {
						Row row = sheet.createRow(bdRowIdx);
						int bdCellIdx = 0;
						for (String key : columnKeys) {
							Cell cell = row.createCell(bdCellIdx);
							// cell.setCellType(Cell.CELL_TYPE_STRING);
							cell.setCellValue(map.get(key));
							sheet.autoSizeColumn(bdCellIdx);
							bdCellIdx++;
						}

						bdRowIdx++;
					}
				}
			}

			workbook.write(os);
		}
		finally {
			IOUtils.closeQuietly(workbook);
		}
	}
}
