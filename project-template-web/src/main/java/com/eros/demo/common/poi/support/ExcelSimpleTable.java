package com.eros.demo.common.poi.support;

import java.util.List;
import java.util.Map;

/**
 * Excel简单一维表对象
 * 
 * @author guanheng
 *
 */
public class ExcelSimpleTable {

	private String sheetName;
	private List<ExcelColumnTitle> columnTitles;
	private List<Map<String, String>> datas;

	public String getSheetName() {
		return sheetName;
	}

	public ExcelSimpleTable setSheetName(String sheetName) {
		this.sheetName = sheetName;
		return this;
	}

	public List<ExcelColumnTitle> getColumnTitles() {
		return columnTitles;
	}

	public ExcelSimpleTable setColumnTitles(List<ExcelColumnTitle> columnTitles) {
		this.columnTitles = columnTitles;
		return this;
	}

	public List<Map<String, String>> getDatas() {
		return datas;
	}

	public ExcelSimpleTable setDatas(List<Map<String, String>> datas) {
		this.datas = datas;
		return this;
	}
}
