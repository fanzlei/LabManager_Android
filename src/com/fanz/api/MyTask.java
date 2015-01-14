package com.fanz.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class MyTask extends AsyncTask<Object,java.lang.Void,Object>{

	HttpClient client=new DefaultHttpClient();
	MyTaskListener listener;
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	
	}

	@Override
	protected Object doInBackground(Object... params) {
		
		// TODO Auto-generated method stub
		try {
			//获取Usernet对象为接口实现类
			listener=(MyTaskListener)params[1];
			HttpResponse response=client.execute((HttpPost)params[0]);
			InputStream is=response.getEntity().getContent();
			ByteArrayOutputStream bo=new ByteArrayOutputStream();
			int len=0;
			byte[] buff=new byte[1024];
			while((len=is.read(buff))!=-1){
				bo.write(buff, 0, len);
			}
			String jsonString=new String(bo.toByteArray(),"UTF-8");
			return jsonString;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		listener.backFromServer(result);
	}

	public interface MyTaskListener{
		public void backFromServer(Object jo);
		
	};

	

}
