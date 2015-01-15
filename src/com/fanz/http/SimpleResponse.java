package com.fanz.http;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fanz.util.NetUtils;

/**
 * 网络响应封装类
 * 
 * @author fanz
 * */
public class SimpleResponse {

	private HttpResponse response;

	public SimpleResponse(final HttpResponse response) {
		this.response = response;
	}

	/**
	 * 返回从响应中获取的JSONObject对象
	 * 
	 * @return JSON结果
	 **/
	public JSONObject getJSONObject() {
		JSONObject json = null;
		try {
			String str = NetUtils
					.parseString(response.getEntity().getContent());
			json = new JSONObject(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 返回从响应中获取的JSONArray对象
	 * 
	 * @return JSONArray结果
	 * */
	public JSONArray getJSONArray() {
		JSONArray ja = null;
		try {
			String str = NetUtils
					.parseString(response.getEntity().getContent());
			ja = new JSONArray(str);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ja;
	}

}
