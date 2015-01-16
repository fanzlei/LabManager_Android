package com.fanz.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;

/**
 * 网络访问客户端封装
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class SimpleClient {
	private HttpClient client;
	private SimpleRequest request;

	// 当前需要访问服务器的Activity
	private Context context;
	private int tag;

	public SimpleClient(Context context, SimpleRequest request, int tag) {
		client = new DefaultHttpClient();
		this.request = request;
		this.tag = tag;
		this.context = context;
	}

	public Context getContext() {
		return this.context;
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
