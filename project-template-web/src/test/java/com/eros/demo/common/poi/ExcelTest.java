package com.eros.demo.common.poi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.eros.demo.common.poi.exception.UnsupportExcelException;
import com.eros.demo.common.poi.support.ExcelColumnTitle;
import com.eros.demo.common.poi.support.ExcelSimpleTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ExcelTest {

	@Test
	public void testReadExcelSimpleTable1() throws UnsupportExcelException, IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("test1.xls");
		List<ExcelSimpleTable> simpleTables = ExcelReader.readExcelSimpleTable(is, "xls");
		System.out.println(simpleTables.size());
	}

	@Test
	public void testReadExcelSimpleTable2() throws UnsupportExcelException, IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("test1.xlsx");
		List<ExcelSimpleTable> simpleTables = ExcelReader.readExcelSimpleTable(is, "xlsx");
		System.out.println(simpleTables.size());
	}

	@Test
	public void testWriteExcelSimpleTable() throws IOException {
		List<Map<String, String>> datas = Lists.newArrayList();
		Map<String, String> data1 = Maps.newHashMap();
		data1.put("name", "jack");
		data1.put("age", "18");
		data1.put("regTime", "2017-05-12 10:35:42");
		datas.add(data1);

		Map<String, String> data2 = Maps.newHashMap();
		data2.put("name", "mary");
		data2.put("age", "22");
		data2.put("regTime", "2018-11-24 16:05:55");
		datas.add(data2);

		List<ExcelColumnTitle> columnTitles = Lists.newArrayList();
		ExcelColumnTitle titleName = new ExcelColumnTitle();
		titleName.setKey("name")
		         .setValue("姓名");
		columnTitles.add(titleName);

		ExcelColumnTitle titleAge = new ExcelColumnTitle();
		titleAge.setKey("age")
		        .setValue("年龄");
		columnTitles.add(titleAge);

		ExcelColumnTitle titleRegTime = new ExcelColumnTitle();
		titleRegTime.setKey("regTime")
		            .setValue("注册时间");
		columnTitles.add(titleRegTime);

		ExcelSimpleTable simpleTable = new ExcelSimpleTable();
		simpleTable.setSheetName("测试sheet")
		           .setColumnTitles(columnTitles)
		           .setDatas(datas);

		byte[] bytes = ExcelWriter.writeExcelSimpleTable(simpleTable);
		FileUtils.writeByteArrayToFile(new File("E:/test1.xlsx"), bytes);
	}
}
