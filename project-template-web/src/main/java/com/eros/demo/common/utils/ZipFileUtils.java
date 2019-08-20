package com.eros.demo.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.google.common.collect.Lists;

/**
 * zip压缩包工具类
 * 
 * @author guanheng
 *
 */
public class ZipFileUtils {

	/**
	 * 压缩文件列表，并填充进输出流中
	 * 
	 * @param datas
	 *            文件二进制数组列表
	 * @param fileNames
	 *            文件名列表
	 * @param out
	 *            输出流
	 * @throws IOException
	 */
	public static void zip(List<byte[]> datas, List<String> fileNames, OutputStream out) throws IOException {
		ZipOutputStream zip = new ZipOutputStream(out);
		List<String> existFileNames = Lists.newArrayList();
		try {
			int dataSize = datas.size();
			for (int i = 0; i < dataSize; i++) {
				byte[] data = datas.get(i);
				String fileName = fileNames.get(i);
				int fi = 1;
				while (existFileNames.contains(fileName)) {
					fileName = fileName.replace(FilenameUtils.getBaseName(fileName), FilenameUtils.getBaseName(fileNames.get(i)) + "-" + fi);
					fi++;
				}
				existFileNames.add(fileName);
				ZipEntry entry = new ZipEntry(fileName);
				entry.setSize(data.length);
				zip.putNextEntry(entry);
				zip.write(data);
				zip.closeEntry();
			}
		}
		finally {
			IOUtils.closeQuietly(zip);
			IOUtils.closeQuietly(out);
		}
	}
}
