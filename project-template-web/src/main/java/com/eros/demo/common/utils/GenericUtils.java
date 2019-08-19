package com.eros.demo.common.utils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 通用工具类
 * 
 * @author guanheng
 *
 */
public class GenericUtils {

	private static char hexDigits[] = {
	        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};
	private static long uuidLastTime = 0;
	private static char uuidTimeDigits[] = {
	        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
	        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
	        'u', 'v', 'w', 'x', 'y', 'z'
	};

	/**
	 * Create an uuid
	 * 
	 * @return uuid
	 */
	public static String createUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * Create an uuid with time
	 * 
	 * @return
	 */
	public static String createTimeUUID() {
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replace("-", "");
		long curremtMillis = System.currentTimeMillis();
		if (uuidLastTime >= curremtMillis) {
			uuidLastTime = uuidLastTime + 1;
		}
		else {
			uuidLastTime = curremtMillis;
		}
		curremtMillis = uuidLastTime;

		for (int m = 0; m < 8; m++) {
			int k = (int) (curremtMillis % uuidTimeDigits.length);
			curremtMillis = (curremtMillis - k) / uuidTimeDigits.length;
			id = uuidTimeDigits[k] + id;
		}

		return id;
	}

	/**
	 * Create md5 code for source string.
	 * 
	 * @param source
	 * @return The converted md5 code of source string.
	 */
	public static String md5(String source) {
		try {
			byte[] btInput = source.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * Create sha1 code for source string.
	 * 
	 * @param source
	 * @return
	 */
	public static String sha1(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] shaBytes = md.digest(source.getBytes());
			int j = shaBytes.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte shaByte = shaBytes[i];
				str[k++] = hexDigits[shaByte >>> 4 & 0X0F];
				str[k++] = hexDigits[shaByte & 0X0F];
			}
			return new String(str);
		}
		catch (Exception e) {
			return null;
		}
	}
}
