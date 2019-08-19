package com.eros.demo.common.utils;

import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 数学工具类
 * 
 * @author guanheng
 *
 */
public class MathUtils {

	/**
	 * 根据算术表达式计算结果
	 * 
	 * @param formula
	 *            - 表达式，例如：4+5*(3-1)
	 * @return
	 * @throws ScriptException
	 */
	public static double calcWithFormula(String formula) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		double result = (double) engine.eval(formula);
		return result;
	}

	/**
	 * 随机获取指定范围内的整数
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return 随机值
	 */
	public static int randomInt(int min, int max) {
		int range = max - min;
		Random random = new Random();
		int result = random.nextInt(range) + min;

		return result;
	}
}
