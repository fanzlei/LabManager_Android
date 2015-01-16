package com.fanz.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import com.fanz.api.ApiClientImpl;
import com.fanz.app.Detail;
import com.fanz.app.Login;
import com.fanz.app.Main;
import com.fanz.model.User;
import com.fanz.util.MessageUtil;
import com.fanz.widget.fragment.ListFragment;
import com.fanz.widget.fragment.MyListFragment;

/**
 * 网络访问返回结果处理类 对不同的网络请求分别进行不同处理
 * 
 * @author Fanz
 * 
 * */
public class ResponseHandler {
	public static Context context() {
		return ApiClientImpl.context;
	}

	/**
	 * 用户登录
	 * 
	 * @param jsonString
	 */
	public static void doLogin(String jsonString) {

		Context context = context();
		JSONObject json;
		try {
			System.out.println(jsonString);
			json = new JSONObject(jsonString);
			if (json.getBoolean("login")) {
				MessageUtil.shortMessage(context(), "登录成功");

				// 登录成功后保存用户信息到本地
				SharedPreferences sp = context.getSharedPreferences(
						"localSave", context.MODE_WORLD_READABLE);
				SharedPreferences.Editor editor = sp.edit();

				editor.putString("name", json.getString("name"));
				editor.putString("pass", json.getString("pass"));
				editor.putString("phone", json.getString("phone"));
				editor.putInt("id", json.getInt("id"));
				editor.putString("classes", json.getString("classes"));
				editor.putBoolean("pass_status", json.getBoolean("pass_status"));
				editor.commit();

				Intent intent = new Intent(context, Main.class);
				context.startActivity(intent);

			} else {
				MessageUtil.shortMessage(context(), "用户名或密码错误");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户注册
	 * 
	 * @param jsonString
	 */
	public static void doRegister(String jsonString) {
		try {
			JSONObject json = new JSONObject(jsonString);
			if (json.getBoolean("register")) {
				MessageUtil.shortMessage(context(), "注册成功");

				Intent intent = new Intent(context(), Login.class);
				context().startActivity(intent);
			} else {
				MessageUtil.shortMessage(context(), "注册失败");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新用户信息
	 * 
	 * @param jsonString
	 */
	public static void doUpdateUser(String jsonString) {
		try {
			JSONObject ujo = new JSONObject(jsonString);
			if (ujo.getBoolean("updateUser")) {
				MessageUtil.shortMessage(context(), "更改用户信息成功");

				Intent intent = new Intent(context(), Main.class);
				context().startActivity(intent);
			} else {
				MessageUtil.shortMessage(context(), "操作失败");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取我的所有预约
	 * 
	 * @param jsonString
	 */
	public static void doGetMyList(String jsonString) {
		try {
			JSONArray jsons = new JSONArray(jsonString);
			Message msg = new Message();
			msg.obj = jsons;
			msg.what = 111;
			MyListFragment.handler.sendMessage(msg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过实验室获取教师
	 * 
	 * @param jsonString
	 */
	public static void doGetTeacherByLab_no(String jsonString) {
		try {
			JSONObject json = new JSONObject(jsonString);
			Message msg = new Message();
			msg.what = 444;
			msg.obj = json;
			Detail.handler.sendMessage(msg);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加预定
	 * 
	 * @param jsonString
	 */
	public static void doAddAppointment(String jsonString) {
		try {
			JSONObject json = new JSONObject(jsonString);
			if (json.getBoolean("add")) {
				MessageUtil.shortMessage(context(), "预定成功");
				Intent intent = new Intent(context(), Main.class);
				context().startActivity(intent);
			} else {
				MessageUtil.shortMessage(context(), "预定失败");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除预定
	 * 
	 * @param jsonString
	 */
	public static void doDeleteAppointment(String jsonString) {
		try {
			JSONObject json = new JSONObject(jsonString);
			if (json.getBoolean("delete")) {
				MessageUtil.shortMessage(context(), "删除成功");

				SharedPreferences sp = context().getSharedPreferences(
						"localSave", context().MODE_WORLD_READABLE);

				User user = new User();
				user.setName(sp.getString("name", ""));
				new ApiClientImpl(context()).getMyList(user);
			} else {
				MessageUtil.shortMessage(context(), "删除成功");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取实验室列表
	 * 
	 * @param jsonString
	 */
	public static void doGetLabList(String jsonString) {
		try {
			JSONArray jsons = new JSONArray(jsonString);
			Message msg = new Message();
			msg.what = 222;
			msg.obj = jsons;
			ListFragment.handler.sendMessage(msg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过实验室获取该实验室预约列表
	 * 
	 * @param jsonString
	 */
	public static void doGetAppoListByLab_no(String jsonString) {
		try {
			JSONArray jsons = new JSONArray(jsonString);
			Message msg = new Message();
			msg.what = 333;
			msg.obj = jsons;
			Detail.handler.sendMessage(msg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
