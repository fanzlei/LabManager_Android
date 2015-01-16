package com.fanz.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 网络访问客户端封装
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class SimpleClient {
	private HttpClient client;
	private SimpleRequest request;
	private String tag;

	public SimpleClient(SimpleRequest request, String tag) {
		client = new DefaultHttpClient();
		this.request = request;
		this.tag = tag;
	}

	/**
	 * 使用异步任务执行post请求
	 * */
	public void executePost() {
		new MyTask().execute(this, tag);
	}

	public HttpClient getHttpClient() {
		return client;
	}

	public SimpleRequest getSimpleRequest() {
		return request;
	}
}
