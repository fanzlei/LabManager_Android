package com.fanz.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fanz.util.NetUtils;

/**
 * 网络响应封装类
 * */
public class SimpleResponse {

	private HttpResponse response;

	public SimpleResponse(final HttpResponse response) {
		// TODO Auto-generated constructor stub
		this.response = response;
	}

	/**
	 * @return 返回从响应中获取的JSONObject对象
	 **/
	public JSONObject getJSONObject() {
		JSONObject jo = null;
		try {
			String str = NetUtils.getFromInputStream(response.getEntity()
					.getContent());
			jo = new JSONObject(str);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo;
	}

	public JSONArray getJSONArray() {

		return null;
	}

	public String toString() {

		return "";
	}
}
