package com.fanz.api;

import android.content.Context;

/**
 * ApiClient工厂
 * 
 * @author fanz
 *
 */
public class ApiClientFactory {
	private ApiClientFactory() {
	}

	/**
	 * @param context
	 * @return ApiClientImpl对象
	 * */
	public static ApiClient createApiClient(final Context context) {
		return new ApiClientImpl(context);
	}

}
