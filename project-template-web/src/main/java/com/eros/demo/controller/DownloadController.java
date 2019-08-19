package com.eros.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownloadController extends BaseController {

	@RequestMapping(value = "")
	public void download() {
		String filePath = "./config/application.properties";
		byte[] fileBytes = getFileBytes(filePath);
		download(fileBytes, "application.properties");
	}

	private byte[] getFileBytes(String filePath) {
		File file = new File(filePath.toString());

		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		byte[] buffer = null;
		try {
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			buffer = bos.toByteArray();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(bos);
		}

		return buffer;
	}

	private void download(byte[] bytes, String filename) {
		HttpServletRequest req = getRequest();
		HttpServletResponse res = getResponse();
		try (OutputStream os = res.getOutputStream()) {
			String agent = req.getHeader("USER-AGENT");
			if (agent != null && agent.indexOf("MSIE") != -1) {
				// IE browser
				filename = URLEncoder.encode(filename, "UTF8");
				filename = filename.replaceAll("\\+", "%20");
			}
			else if (agent != null && agent.indexOf("Firefox") != -1) {
				filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
			}
			else {
				// other browser
				filename = URLEncoder.encode(filename, "UTF8");
				filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
			}

			res.reset();
			StringBuffer content = new StringBuffer("attachment; filename=");
			content.append(filename);
			res.setHeader("Content-Disposition", content.toString());
			res.setContentType("application/octet-stream; charset=utf-8");
			os.write(bytes);
			os.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
