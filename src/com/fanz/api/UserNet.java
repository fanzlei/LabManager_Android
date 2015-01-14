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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.fanz.api.MyTask.MyTaskListener;
import com.fanz.app.Login;
import com.fanz.app.Main;
import com.fanz.model.User;

public class UserNet implements MyTaskListener{

	String tag;
	Context context;
	Handler handler;
	private String url="http://192.168.0.110:8080/LabManager_server/Do_user";
	private int lab_no;
	public UserNet(){
		
	}
	public void login(Context context,User user){
		this.context=context;
		tag="login";
		String[] key=new String[]{"tag","name","pass"};
		String[] values=new String[]{tag,user.getName(),user.getPass()};
		new MyTask().execute(getHttpPost(key,values),this);
		}
	public void register(Context context,User user){
		tag="register";
		this.context=context;
		String[] key=new String[]{"tag","name","pass","phone","classes"};
		String[] values=new String[]{tag,user.getName(),user.getPass(),user.getPhone(),user.getClasses()};
		new MyTask().execute(getHttpPost(key,values),this);
		
		
	}
	public void updateUser(Context context,User user){
		tag="updateUser";
		this.context=context;
		String[] key=new String[]{"tag","name","pass","phone"};
		String[] values=new String[]{"updateUser",user.getName(),user.getPass(),user.getPhone()};
		new MyTask().execute(getHttpPost(key,values),this);
		
	}
	public void updateStatus(Context context,User user){
		tag="updateStatus";
		this.context=context;
		String[] key=new String[]{"tag","name","pass_status"};
		String[] values=new String[]{"updateStatus",user.getName(),String.valueOf(user.getPassStatus())};
		new MyTask().execute(getHttpPost(key,values),this);
		
	}
	public void getMyList(Context context,User user,Handler handler){
		tag="getMyList";
		this.context=context;
		this.handler=handler;
		String[] key=new String[]{"tag","name"};
		String[] values=new String[]{tag,user.getName()};
		new MyTask().execute(getHttpPost(key,values),this);
	}
	public void getTeacherByLab_no(Context context,int lab_no,Handler handler){
		this.context=context;
		this.handler=handler;
		this.lab_no=lab_no;
		tag="getTeacherByLab_no";
		String[] key=new String[]{"tag","lab_no"};
		String[] values=new String[]{tag,String.valueOf(lab_no)};
		new MyTask().execute(getHttpPost(key,values),this);
	}
	public HttpPost getHttpPost(String[] key,Object values[]){
		
		HttpPost post=new HttpPost(url);
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		for(int i=0;i<key.length;i++){
			BasicNameValuePair pair=new BasicNameValuePair(key[i], (String)values[i]);
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
	@Override
	public void backFromServer(Object joo) {
		// TODO Auto-generated method stub
		if(joo==null){
			Toast.makeText(context, "网络错误或服务器错误", Toast.LENGTH_SHORT).show();
			return;
		}
			switch(tag){
			case "login":
				
				try {
					JSONObject jo=new JSONObject((String)joo);
					if(jo.getBoolean("login")){
						Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
						//登录成功后保存用户信息到本地
						SharedPreferences sp=context.getSharedPreferences("localSave", context.MODE_WORLD_READABLE);
						SharedPreferences.Editor editor=sp.edit();
						editor.putString("name", jo.getString("name"));
						editor.putString("pass", jo.getString("pass"));
						editor.putString("phone",jo.getString("phone"));
						editor.putInt("id", jo.getInt("id"));
						editor.putString("classes", jo.getString("classes"));
						editor.putBoolean("pass_status", jo.getBoolean("pass_status"));
						editor.commit();
						Intent intent=new Intent(context,Main.class);
						context.startActivity(intent);
						
					}else{
						Toast.makeText(context, "用户名或密码错误", Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "register":
				
				try {
					JSONObject rjo=new JSONObject((String)joo);
					if(rjo.getBoolean("register")){
						Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
						Intent intent=new Intent(context,Login.class);
						context.startActivity(intent);
						
					}else{
						Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "updateUser":
				
				try {
					JSONObject ujo=new JSONObject((String)joo);
					if(ujo.getBoolean("updateUser")){
						Toast.makeText(context, "更改用户信息成功", Toast.LENGTH_SHORT).show();
						Intent intent=new Intent(context,Main.class);
						context.startActivity(intent);
					}else{
						Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "getMyList":
				try {
					JSONArray ja=new JSONArray((String)joo);
					Message msg=new Message();
					msg.obj=ja;
					msg.what=111;
					handler.sendMessage(msg);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "getTeacherByLab_no":
				try {
					JSONObject jo=new JSONObject((String)joo);
					Message msg=new Message();
					msg.what=444;
					msg.obj=jo;
					handler.sendMessage(msg);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	
}
