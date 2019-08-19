package com.eros.demo.common.exception;

import com.eros.demo.common.support.ResultCode;

/**
 * 业务异常
 * 
 * @author guanheng
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -1050136701705385854L;

	private final String message;
	private final ResultCode code;

	public ServiceException(String message) {
		super(message);
		this.message = message;
		code = ResultCode.EXCEPTION;
	}

	public ServiceException(ResultCode code) {
		super(code.msg());
		this.message = code.msg();
		this.code = code;
	}

	public ServiceException(String message, ResultCode code) {
		super(message);
		this.message = message;
		this.code = code;
	}

	public ServiceException(Throwable cause) {
		super(cause);
		this.message = ResultCode.EXCEPTION.msg();
		code = ResultCode.EXCEPTION;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public ResultCode getCode() {
		return code;
	}
}
