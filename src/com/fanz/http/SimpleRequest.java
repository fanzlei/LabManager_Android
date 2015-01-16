package com.fanz.http;

import org.apache.http.client.methods.HttpPost;

import com.fanz.util.Constant;

/**
 * 网络请求封装类 这里只用到了Android端发送POST请求
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class SimpleRequest {
	private HttpPost post;

	// 默认后台服务器URL地址
	private String URL = Constant.SERVER_URL;

	public SimpleRequest(SimpleRequestParam param) {
		post = new HttpPost(URL);
		post.setEntity(param.getEntity());
	}

	/**
	 * 向服务器发送的HttpPost请求
	 * 
	 * @return post请求
	 * */
	public HttpPost getPost() {
		return post;
	}
}
