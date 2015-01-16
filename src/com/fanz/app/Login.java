package com.fanz.app;

import com.fanz.app.R;
import com.fanz.api.ApiClientFactory;
import com.fanz.api.ApiClientImpl;
import com.fanz.model.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * 用户登录界面
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class Login extends Activity {

	private String name = "", pass = "";
	private EditText ed1, ed2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.login);
		ed1 = (EditText) findViewById(R.id.login_name);
		ed2 = (EditText) findViewById(R.id.login_pass);

	}

	/** 点击登录按钮，根据用户输入信息进行登录操作 */
	public void login(View v) {
		name = ed1.getText().toString().trim();
		pass = ed2.getText().toString().trim();
		User user = new User();
		user.setName(name);
		user.setPass(pass);
		ApiClientFactory.createApiClient(this).login(user);
	}

	/** 点击注册按钮，进入用户注册界面 */
	public void register(View v) {
		Intent intent = new Intent(this, Register.class);
		this.startActivity(intent);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.finish();
	}

}
