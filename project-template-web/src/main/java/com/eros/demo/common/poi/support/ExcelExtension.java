package com.eros.demo.common.poi.support;

/**
 * Excel扩展名
 * 
 * @author guanheng
 *
 */
public enum ExcelExtension {

    /**
     * 扩展名 - xls
     */
	XLS("xls"),

	/**
	 * 扩展名 - xlsx
	 */
	XLSX("xlsx");

	private String value;

	private ExcelExtension(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
