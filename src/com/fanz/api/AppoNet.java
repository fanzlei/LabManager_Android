package com.fanz.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fanz.api.MyTask.MyTaskListener;
import com.fanz.app.Main;
import com.fanz.model.Appo;
import com.fanz.model.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class AppoNet implements MyTaskListener {

	String tag;
	Context context;
	private String url = "http://192.168.0.110:8080/LabManager_server/Do_appointment";
	private Handler handler;

	public AppoNet() {
	}

	public HttpPost getHttpPost(String[] key, Object values[]) {

		HttpPost post = new HttpPost(url);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (int i = 0; i < key.length; i++) {
			BasicNameValuePair pair = new BasicNameValuePair(key[i],
					(String) values[i]);
			list.add(pair);
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
	}

	public void add(Context context, Appo ap) {
		this.context = context;
		tag = "add";
		String[] key = new String[] { "tag", "date", "date_part", "lab_no",
				"name", "number" };
		String[] values = new String[] { "add", ap.getDate().toString(),
				String.valueOf(ap.getDate_part()),
				String.valueOf(ap.getLab_no()), ap.getName(),
				String.valueOf(ap.getNumber()) };
		new MyTask().execute(getHttpPost(key, values), this);
	}

	public void delete(Context context, Appo ap, Handler handler) {
		this.context = context;
		tag = "delete";
		this.handler = handler;
		String[] key = new String[] { "tag", "id" };
		String[] values = new String[] { "delete", String.valueOf(ap.getId()) };
		new MyTask().execute(getHttpPost(key, values), this);
	}

	public void getLabList(Context context, Handler handler) {
		this.context = context;
		this.handler = handler;
		tag = "getLabList";
		String[] key = new String[] { "tag" };
		String[] values = new String[] { tag };
		new MyTask().execute(getHttpPost(key, values), this);
	}

	public void getAppoListByLab_no(Context context, Handler handler, int lab_no) {
		this.context = context;
		this.handler = handler;
		tag = "getAppoListByLab_no";
		String[] key = new String[] { "tag", "lab_no" };
		String[] values = new String[] { tag, String.valueOf(lab_no) };
		new MyTask().execute(getHttpPost(key, values), this);
	}

	@Override
	public void backFromServer(Object joo) {
		// TODO Auto-generated method stub
		if (joo == null) {
			Toast.makeText(context, "网络错误或服务器错误", Toast.LENGTH_SHORT).show();
			return;
		}
		switch (tag) {
		case "add":

			try {
				JSONObject jo = new JSONObject((String) joo);
				if (jo.getBoolean("add")) {
					Toast.makeText(context, "预定成功", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(context, Main.class);
					context.startActivity(intent);
				} else {
					Toast.makeText(context, "预定失败", Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "delete":

			try {
				JSONObject djo = new JSONObject((String) joo);
				if (djo.getBoolean("delete")) {
					Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
					SharedPreferences sp = context.getSharedPreferences(
							"localSave", context.MODE_WORLD_READABLE);
					User user = new User();
					user.setName(sp.getString("name", ""));
					new UserNet().getMyList(context, user, handler);
				} else {
					Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "getLabList":
			JSONArray ja;
			try {
				ja = new JSONArray((String) joo);
				Message msg = new Message();
				msg.what = 222;
				msg.obj = ja;
				handler.sendMessage(msg);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "getAppoListByLab_no":
			JSONArray jaa;
			try {
				jaa = new JSONArray((String) joo);
				Message msg = new Message();
				msg.what = 333;
				msg.obj = jaa;
				handler.sendMessage(msg);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
}
