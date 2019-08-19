package com.eros.demo.common.poi.exception;

/**
 * Excel格式不支持异常
 * 
 * @author guanheng
 *
 */
public class UnsupportExcelException extends Exception {

	private static final long serialVersionUID = -6128782038175374878L;

	public UnsupportExcelException(String extension) {
		super(String.format("The excel file extension '%s' is not support.", extension));
	}
}
