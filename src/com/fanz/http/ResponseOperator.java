package com.fanz.http;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.fanz.api.ApiClientImpl;
import com.fanz.app.Main;
/**
 * 网络访问返回结果处理类
 * 对不同的网络请求分别进行不同处理
 * @author Fanz
 * 
 * */
public class ResponseOperator {
	public static void doLogin(SimpleResponse response) {

		Context context = ApiClientImpl.context;
		JSONObject jo = response.getJSONObject();
		try {
			if (jo.getBoolean("login")) {
				Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
				// 登录成功后保存用户信息到本地
				SharedPreferences sp = context.getSharedPreferences(
						"localSave", context.MODE_WORLD_READABLE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putString("name", jo.getString("name"));
				editor.putString("pass", jo.getString("pass"));
				editor.putString("phone", jo.getString("phone"));
				editor.putInt("id", jo.getInt("id"));
				editor.putString("classes", jo.getString("classes"));
				editor.putBoolean("pass_status", jo.getBoolean("pass_status"));
				editor.commit();
				Intent intent = new Intent(context, Main.class);
				context.startActivity(intent);

			} else {
				Toast.makeText(context, "用户名或密码错误", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void doRegister(SimpleResponse response) {

	}

	public static void doUpdateUser(SimpleResponse response) {

	}

	public static void doGetMyList(SimpleResponse response) {

	}

	public static void doGetTeacherByLab_no(SimpleResponse response) {

	}

	public static void doAdd(SimpleResponse response) {

	}

	public static void doDelete(SimpleResponse response) {

	}

	public static void doGetLabList(SimpleResponse response) {

	}

	public static void doGetAppoListByLab_no(SimpleResponse response) {

	}
}
