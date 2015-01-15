package com.fanz.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import android.os.AsyncTask;
/**
 * 异步任务网络访问
 * */
public class MyTask extends AsyncTask<Object,java.lang.Void,Object>{

	SimpleClient simpleClient;
	String tag="";
	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		simpleClient=(SimpleClient) params[0];
		tag=(String) params[1];
		HttpResponse response=null;
		try {
			response=simpleClient.getHttpClient()
			.execute(simpleClient.getSimpleRequest().getHttpPost());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SimpleResponse(response);
	}

	

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		SimpleResponse response=(SimpleResponse) result;
		if(tag!=null&&!tag.equals("")){
		//用tag标签区分不同的网络操作，对返回结果进行不同处理
			switch(tag){
			case "login":
				ResponseOperator.doLogin(response);
				break;
			case "register":
				
				break;
			case "updateUser":
				
				break;
			case "getMyList":
				
				break;
			case "getTeacherByLab_no":
				
				break;
			case "add":
				
				break;
			case "delete":
				
				break;
			case "getLabList":
				
				break;
			case "getAppoListByLab_no":
				
				break;
			}
		}
	}

}
