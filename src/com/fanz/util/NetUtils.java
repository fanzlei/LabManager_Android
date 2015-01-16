package com.fanz.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * 网络请求工具类
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class NetUtils {
	/**
	 * @return 返回从输入流中读取的字符串
	 * */
	public static String getFromInputStream(InputStream is) {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		int len = 0;
		String str = "";
		byte[] buff = new byte[1024];
		try {
			while ((len = is.read(buff)) != -1) {
				bo.write(buff, 0, len);
			}
			str = new String(bo.toByteArray(), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str;
	}
}
