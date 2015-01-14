package com.fanz.api;

import android.content.Context;

/**
 * ApiClient接口实现类
 * 
 * @author fanz
 *
 */
public class ApiClientImpl implements ApiClient {
	private Context context;

	public ApiClientImpl(Context context) {
		this.context = context;
	}
}
