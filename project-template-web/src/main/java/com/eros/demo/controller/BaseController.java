package com.eros.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.eros.demo.common.exception.ServiceException;
import com.eros.demo.common.support.JsonResult;
import com.eros.demo.common.support.PagedList;
import com.eros.demo.common.support.ResultCode;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;

/**
 * 基础控制器
 */
public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private List<ResultCode> warnCodes;

	public BaseController() {
		warnCodes = Lists.newArrayList();
		warnCodes.add(ResultCode.UNAUTHORIZED);
		warnCodes.add(ResultCode.FORBIDDEN);
		warnCodes.add(ResultCode.NOT_FOUND);
		warnCodes.add(ResultCode.CONFLICT);
		warnCodes.add(ResultCode.LOCKED);
	}

	/**
	 * 统一异常处理
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	protected JsonResult exceptionHandler(Exception e) {
		JsonResult result;

		if (e instanceof MissingServletRequestParameterException) {
			result = new JsonResult(ResultCode.BAD_REQUEST, e.getMessage());
			logger.error(e.getMessage(), e);
		}
		else if (e instanceof ServiceException) {
			ServiceException exception = (ServiceException) e;
			result = new JsonResult(exception.getCode(), exception.getMessage());
			if (warnCodes.contains(exception.getCode())) {
				logger.warn(e.getMessage(), e);
			}
			else {
				logger.error(e.getMessage(), e);
			}
		}
		else {
			result = new JsonResult(ResultCode.UNKNOWN_ERROR);
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	protected JsonResult retJson() {
		return new JsonResult();
	}

	protected JsonResult retJson(Page<?> datas) {
		PagedList<?> pagedList = new PagedList<>(datas, datas.getTotal());
		return new JsonResult(pagedList);
	}

	protected JsonResult retJson(Object data) {
		return new JsonResult(data);
	}

	protected HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}

	protected HttpServletResponse getResponse() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getResponse();
	}

	protected void setSessionAttribute(String key, Object value) {
		HttpSession session = getRequest().getSession();
		session.setAttribute(key, value);
	}

	protected Object getSessionAttribute(String key) {
		HttpSession session = getRequest().getSession();
		return session.getAttribute(key);
	}

	@SuppressWarnings("unchecked")
	protected <T> T getSessionAttribute(String key, Class<T> clazz) {
		return (T) getSessionAttribute(key);
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}
}
