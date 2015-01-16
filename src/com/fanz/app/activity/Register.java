package com.fanz.app.activity;

import com.fanz.app.R;
import com.fanz.app.base.App;
import com.fanz.app.base.BaseActivity;
import com.fanz.model.User;
import com.fanz.util.MessageUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * 用户注册界面
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class Register extends BaseActivity {

	private EditText name, pass, phone, classes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.register);
		name = (EditText) findViewById(R.id.register_name);
		pass = (EditText) findViewById(R.id.register_pass);
		phone = (EditText) findViewById(R.id.register_phone);
		classes = (EditText) findViewById(R.id.register_classes);

	}

	/** 点击注册按钮，根据用户输入信息进行账户注册 */
	public void register(View v) {
		if (name.getText().toString().trim().length() > 0
				&& pass.getText().toString().trim().length() > 0
				&& phone.getText().toString().trim().length() > 2
				&& classes.getText().toString().trim().length() > 2) {
			
			User user = new User();
			user.setName(name.getText().toString().trim());
			user.setPass(pass.getText().toString().trim());
			user.setPhone(phone.getText().toString().trim());
			user.setClasses(classes.getText().toString().trim());
			
			// 注册用户信息
			App.apiClient(this).register(user);
		} else {
			MessageUtil.shortMessage(Register.this, "请输入正确格式信息");
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		this.finish();
	}

}
