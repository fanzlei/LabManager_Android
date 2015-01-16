package com.fanz.http;

import org.apache.http.client.methods.HttpPost;

/**
 * 网络请求封装类 这里只用到了Android端发送POST请求
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class SimpleRequest {
	/** post请求 */
	private HttpPost post;
	private String URL = "http://192.168.0.104:8080/LabManager_server/Do_user";

	/**
	 * @param 网络请求参数
	 * */
	public SimpleRequest(SimpleRequestParam param) {
		post = new HttpPost(URL);
		post.setEntity(param.getEntity());

	}

	/**
	 * @return 向服务器发送的HttpPost请求
	 * */
	public HttpPost getHttpPost() {

		return post;
	}
}
