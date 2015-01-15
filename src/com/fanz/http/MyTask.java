package com.fanz.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import com.fanz.util.NetUtils;

import android.os.AsyncTask;

/**
 * 异步任务网络访问
 * */
public class MyTask extends AsyncTask<Object, java.lang.Void, Object> {

	SimpleClient simpleClient;
	String tag = "";

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		simpleClient = (SimpleClient) params[0];
		tag = (String) params[1];
		String jsonString = "";
		HttpResponse response = null;
		try {
			response = simpleClient.getHttpClient().execute(
					simpleClient.getSimpleRequest().getHttpPost());
			jsonString = NetUtils.getFromInputStream(response.getEntity()
					.getContent());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		String jsonString = (String) result;
		if (tag != null && !tag.equals("")) {
			// 用tag标签区分不同的网络操作，对返回结果进行不同处理
			switch (tag) {
			case "login":
				ResponseOperator.doLogin(jsonString);
				break;
			case "register":
				ResponseOperator.doRegister(jsonString);
				break;
			case "updateUser":
				ResponseOperator.doUpdateUser(jsonString);
				break;
			case "getMyList":
				ResponseOperator.doGetMyList(jsonString);
				break;
			case "getTeacherByLab_no":
				ResponseOperator.doGetTeacherByLab_no(jsonString);
				break;
			case "add":
				ResponseOperator.doAdd(jsonString);
				break;
			case "delete":
				ResponseOperator.doDelete(jsonString);
				break;
			case "getLabList":
				ResponseOperator.doGetLabList(jsonString);
				break;
			case "getAppoListByLab_no":
				ResponseOperator.doGetAppoListByLab_no(jsonString);
				break;
			}
		}
	}

}
