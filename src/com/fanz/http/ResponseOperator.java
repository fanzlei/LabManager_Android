package com.fanz.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.widget.Toast;
import com.fanz.api.ApiClientImpl;
import com.fanz.app.Detail;
import com.fanz.app.Login;
import com.fanz.app.Main;
import com.fanz.model.User;
import com.fanz.widget.fragment.ListFragment;
import com.fanz.widget.fragment.MyListFragment;

/**
 * 网络访问返回结果处理类 对不同的网络请求分别进行不同处理
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class ResponseOperator {
	/** 用户登录请求结果处理 */
	public static void doLogin(String jsonString) {

		Context context = ApiClientImpl.context;
		JSONObject jo;
		try {
			System.out.println(jsonString);
			jo = new JSONObject(jsonString);
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

	/** 用户注册请求结果处理 */
	public static void doRegister(String jsonString) {
		try {
			JSONObject jo = new JSONObject(jsonString);
			if (jo.getBoolean("register")) {
				Toast.makeText(ApiClientImpl.context, "注册成功",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ApiClientImpl.context, Login.class);
				ApiClientImpl.context.startActivity(intent);

			} else {
				Toast.makeText(ApiClientImpl.context, "注册失败",
						Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 修改用于信息请求结果处理 */
	public static void doUpdateUser(String jsonString) {
		try {
			JSONObject ujo = new JSONObject(jsonString);
			if (ujo.getBoolean("updateUser")) {
				Toast.makeText(ApiClientImpl.context, "更改用户信息成功",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ApiClientImpl.context, Main.class);
				ApiClientImpl.context.startActivity(intent);
			} else {
				Toast.makeText(ApiClientImpl.context, "操作失败",
						Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 获取我的列表请求结果处理 */
	public static void doGetMyList(String jsonString) {
		try {
			JSONArray ja = new JSONArray(jsonString);
			Message msg = new Message();
			msg.obj = ja;
			msg.what = 111;
			MyListFragment.handler.sendMessage(msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 获取实验室管理教师请求结果处理 */
	public static void doGetTeacherByLab_no(String jsonString) {
		try {
			JSONObject jo = new JSONObject(jsonString);
			Message msg = new Message();
			msg.what = 444;
			msg.obj = jo;
			Detail.handler.sendMessage(msg);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 实验室预约请求结果处理 */
	public static void doAdd(String jsonString) {
		try {
			JSONObject jo = new JSONObject(jsonString);
			if (jo.getBoolean("add")) {
				Toast.makeText(ApiClientImpl.context, "预定成功",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ApiClientImpl.context, Main.class);
				ApiClientImpl.context.startActivity(intent);
			} else {
				Toast.makeText(ApiClientImpl.context, "预定失败",
						Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 取消预约请求结果处理 */
	public static void doDelete(String jsonString) {
		try {
			JSONObject djo = new JSONObject(jsonString);
			if (djo.getBoolean("delete")) {
				Toast.makeText(ApiClientImpl.context, "删除成功",
						Toast.LENGTH_SHORT).show();
				SharedPreferences sp = ApiClientImpl.context
						.getSharedPreferences("localSave",
								ApiClientImpl.context.MODE_WORLD_READABLE);
				User user = new User();
				user.setName(sp.getString("name", ""));
				new ApiClientImpl(ApiClientImpl.context).getMyList(user);
			} else {
				Toast.makeText(ApiClientImpl.context, "删除失败",
						Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 获取实验室列表请求结果处理 */
	public static void doGetLabList(String jsonString) {
		try {
			JSONArray ja = new JSONArray(jsonString);
			Message msg = new Message();
			msg.what = 222;
			msg.obj = ja;
			ListFragment.handler.sendMessage(msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 获取某个实验室预约列表请求结果处理 */
	public static void doGetAppoListByLab_no(String jsonString) {
		JSONArray jaa;
		try {
			jaa = new JSONArray(jsonString);
			Message msg = new Message();
			msg.what = 333;
			msg.obj = jaa;
			Detail.handler.sendMessage(msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
