package com.fanz.app;

import android.content.Context;

import com.fanz.api.ApiClient;
import com.fanz.api.ApiClientFactory;

public class App {
	/**
	 * 获取ApiClient与后台交互
	 * 
	 * @param context
	 * @return
	 */
	public static ApiClient apiClient(Context context) {
		return ApiClientFactory.createApiClient(context);
	}
}
