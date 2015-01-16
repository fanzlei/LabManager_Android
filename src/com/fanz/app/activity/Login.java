package com.fanz.app.activity;

import com.fanz.app.R;
import com.fanz.app.base.App;
import com.fanz.app.base.BaseActivity;
import com.fanz.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * 用户登录界面
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class Login extends BaseActivity {

	EditText editUsername;
	EditText editPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);
		findView();
	}

	private void findView() {
		editUsername = (EditText) findViewById(R.id.login_name);
		editPassword = (EditText) findViewById(R.id.login_pass);
	}

	/**
	 * 点击登录按钮，根据用户输入信息进行登录操作
	 * 
	 * @param v
	 */
	public void login(View v) {
		String username = editUsername.getText().toString().trim();
		String password = editPassword.getText().toString().trim();

		App.apiClient(this).login(new User(username, password));
	}

	/**
	 * 点击注册按钮，进入用户注册界面
	 * 
	 * @param v
	 */
	public void register(View v) {
		Intent intent = new Intent(this, Register.class);
		this.startActivity(intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.finish();
	}

}
