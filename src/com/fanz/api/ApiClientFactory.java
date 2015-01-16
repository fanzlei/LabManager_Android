/*
 * Copyright (C) 2015 Fanz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

	/** 工厂方法，返回接口的实例对象 */
	public static ApiClient createApiClient(final Context context) {
		return new ApiClientImpl(context);
	}

}
