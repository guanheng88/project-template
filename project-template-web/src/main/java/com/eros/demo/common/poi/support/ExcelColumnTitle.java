package com.eros.demo.common.poi.support;

/**
 * Excel列标题
 * 
 * @author guanheng
 *
 */
public class ExcelColumnTitle {

	/**
	 * 标题key，用于读取和写入数据
	 */
	private String key;
	/**
	 * 标题内容
	 */
	private String value;

	public String getKey() {
		return key;
	}

	public ExcelColumnTitle setKey(String key) {
		this.key = key;
		return this;
	}

	public String getValue() {
		return value;
	}

	public ExcelColumnTitle setValue(String value) {
		this.value = value;
		return this;
	}
}
