package com.fanz.http;

import org.apache.http.client.methods.HttpPost;
/**
 * 网络请求封装类
 * 这里只用到了Android端发送POST请求
 * */
public class SimpleRequest {
	/** post请求 */
	private HttpPost post;
	private String URL = "http://192.168.0.110:8080/LabManager_server/Do";

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
