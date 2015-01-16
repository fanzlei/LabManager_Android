package com.fanz.api;

import android.content.Context;

/**
 * ApiClient工厂
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class ApiClientFactory {
	// 单例模式
	private ApiClientFactory() {

	}

	/**
	 * 返回接口的实例对象,工厂方法
	 * 
	 * @param context
	 * @return ApiClientImpl对象
	 * */
	public static ApiClient createApiClient(final Context context) {
		return new ApiClientImpl(context);
	}

}
