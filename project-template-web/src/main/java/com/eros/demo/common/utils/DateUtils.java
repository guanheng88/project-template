package com.eros.demo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * 
 * @author guanheng
 *
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static final String PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_YMD = "yyyy-MM-dd";

	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * 将指定格式字符串转换成日期
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date toDate(String dateStr) throws ParseException {
		return toDate(dateStr, PATTERN_YMDHMS);
	}

	/**
	 * 将指定格式字符串转换成日期
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date toDate(String dateStr, String pattern) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(dateStr);
		}
		catch (ParseException e) {
			logger.error(String.format("The date string is wrong: %s", dateStr), e);
			throw e;
		}
	}

	/**
	 * 将日期转换为字符串，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateString(Date date) {
		return toDateString(date, PATTERN_YMDHMS);
	}

	/**
	 * 将日期转换为字符串
	 * 
	 * @param date
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String toDateString(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	/**
	 * 获取两个日期天数差
	 * 
	 * @param lower
	 * @param higher
	 * @return
	 */
	public static int getDayDiff(Date lower, Date higher) {
		Date lowerStartDate = getStartDate(lower);
		Date higherStartDate = getStartDate(higher);
		long millisDiff = higherStartDate.getTime() - lowerStartDate.getTime();
		return (int) (millisDiff / (3600 * 24 * 1000));
	}

	/**
	 * 获取当日开始时间 (00:00:00)
	 * 
	 * @return
	 */
	public static Date getStartDate() {
		return getStartDate(new Date());
	}

	/**
	 * 获取指定日期开始时间 (00:00:00)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当日结束时间 (23:59:59)
	 * 
	 * @return
	 */
	public static Date getEndDate() {
		return getEndDate(new Date());
	}

	/**
	 * 获取指定日期结束时间 (23:59:59)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期增加秒数后的日期
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date plusSeconds(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期增加天数后的日期
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date plusDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}
}
