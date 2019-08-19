package com.eros.demo.common.support;

public enum ResultCode {

    /**
     * 成功
     */
	SUCCESS(200, "成功"),

	/**
	 * 参数错误
	 */
	BAD_REQUEST(400, "参数错误 "),

	/**
	 * 未登录
	 */
	UNAUTHORIZED(401, "没有登录"),

	/**
	 * 无权限
	 */
	FORBIDDEN(403, "无权限访问"),

	/**
	 * 资源未找到
	 */
	NOT_FOUND(404, "资源未找到"),

	/**
	 * 资源冲突
	 */
	CONFLICT(409, "资源冲突"),

	/**
	 * 请求格式不支持
	 */
	UNSUPPORTED_MEDIA_TYPE(415, "请求格式不支持"),

	/**
	 * 资源锁定
	 */
	LOCKED(423, "资源锁定"),

	/**
	 * 系统异常
	 */
	EXCEPTION(500, "系统异常"),

	/**
	 * 未知的错误
	 */
	UNKNOWN_ERROR(500, "未知错误");

	private int val;
	private String msg;

	private ResultCode(int value, String msg) {
		this.val = value;
		this.msg = msg;
	}

	public int val() {
		return val;
	}

	public String msg() {
		return msg;
	}
}
