package com.eros.demo.common.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Gson工具类
 * 
 * @author guanheng
 *
 */
public class JsonUtils {

	private static final Gson gson;

	static {
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gson = builder.create();
	}

	/**
	 * 将json字符串转换为指定对象
	 * 
	 * @param json
	 * @param typeOfT
	 * @return
	 */
	public static <T> T parseObject(String json, Type typeOfT) {
		return gson.fromJson(json, typeOfT);
	}

	/**
	 * 将对象转换为json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		return gson.toJson(object);
	}
}
