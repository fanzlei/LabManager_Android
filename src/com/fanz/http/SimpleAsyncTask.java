package com.fanz.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import com.fanz.util.NetUtils;
import com.fanz.util.Tag;

import android.os.AsyncTask;

/**
 * 简单异步任务网络访问
 * 
 * @author fanz
 * 
 */
public class SimpleAsyncTask extends AsyncTask<Object, java.lang.Void, Object> {

	SimpleClient simpleClient;
	int tag;

	public SimpleAsyncTask(SimpleClient client, int tag) {
		this.simpleClient = client;
		this.tag = tag;
	}

	@Override
	protected Object doInBackground(Object... params) {
		String result = "";
		try {
			// 发送http post请求
			HttpClient http = simpleClient.getHttpClient();
			HttpPost request = simpleClient.getSimpleRequest().getPost();
			HttpResponse response = http.execute(request);

			// 处理返回结果
			result = NetUtils.parseString(response.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		dispachResponse((String) result);
	}

	/**
	 * 把服务器返回结果分派给后续处理方法
	 * 
	 * @param result
	 */
	protected void dispachResponse(String result) {
		if (tag > 0) {
			// 用tag标签区分不同的网络操作，对返回结果进行不同处理
			switch (tag) {
			case Tag.LOGIN:
				ResponseHandler.doLogin(result);
				break;
			case Tag.REGISTER:
				ResponseHandler.doRegister(result);
				break;
			case Tag.UPDATE_USER:
				ResponseHandler.doUpdateUser(result);
				break;
			case Tag.GET_MY_LIST:
				ResponseHandler.doGetMyList(result);
				break;
			case Tag.GET_TEACHER_BY_NO:
				ResponseHandler.doGetTeacherByLab_no(result);
				break;
			case Tag.ADD_APPOINTMENT:
				ResponseHandler.doAddAppointment(result);
				break;
			case Tag.REMOVE_APPOINTMENT:
				ResponseHandler.doDeleteAppointment(result);
				break;
			case Tag.GET_LAB_LIST:
				ResponseHandler.doGetLabList(result);
				break;
			case Tag.GET_LAB_APPOINTMENT_LIST:
				ResponseHandler.doGetAppoListByLab_no(result);
				break;
			}
		}
	}

}
