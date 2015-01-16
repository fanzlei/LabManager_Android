package com.fanz.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

/**
 * 请求参数封装类 这里Android端只是向服务器发送POST请求
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class SimpleRequestParam {

	/** 网络请求的entity */
	private UrlEncodedFormEntity urlEncodedFormEntity;
	private List<NameValuePair> list;

	public SimpleRequestParam() {

		list = new ArrayList<NameValuePair>();
	}

	/** 添加参数到entity */
	public void add2Entity(String key, String value) {
		BasicNameValuePair pair = new BasicNameValuePair(key, value);
		list.add(pair);

	}

	public UrlEncodedFormEntity getEntity() {
		try {
			urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urlEncodedFormEntity;
	}

}
