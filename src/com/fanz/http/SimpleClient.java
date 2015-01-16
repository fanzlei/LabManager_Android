package com.fanz.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class SimpleClient {
	private HttpClient client;
	private SimpleRequest request;
	private int tag;

	public SimpleClient(SimpleRequest request, int tag) {
		client = new DefaultHttpClient();
		this.request = request;
		this.tag = tag;
	}

	/**
	 * 使用异步任务执行post请求
	 */
	public void executePost() {
		new SimpleAsyncTask(this, tag).execute();
	}

	public HttpClient getHttpClient() {
		return client;
	}

	public SimpleRequest getSimpleRequest() {
		return request;
	}
}
