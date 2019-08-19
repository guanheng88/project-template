package com.eros.demo.common.support;

public class JsonResult {

	private int code;
	private String msg;
	private Object data;

	public JsonResult() {
		this.code = ResultCode.SUCCESS.val();
		this.msg = ResultCode.SUCCESS.msg();
	}

	public JsonResult(Object data) {
		this.code = ResultCode.SUCCESS.val();
		this.msg = ResultCode.SUCCESS.msg();
		this.data = data;
	}

	public JsonResult(ResultCode code) {
		this.code = code.val();
		this.msg = code.msg();
	}

	public JsonResult(ResultCode code, String msg) {
		this.code = code.val();
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public JsonResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public JsonResult setData(Object data) {
		this.data = data;
		return this;
	}
}
