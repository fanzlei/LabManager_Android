package com.fanz.app;

import com.fanz.app.R;
import com.fanz.api.ApiClientImpl;
import com.fanz.model.User;
import com.fanz.util.MessageUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends BaseActivity {

	EditText name, pass, phone, classes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.register);
		name = (EditText) findViewById(R.id.register_name);
		pass = (EditText) findViewById(R.id.register_pass);
		phone = (EditText) findViewById(R.id.register_phone);
		classes = (EditText) findViewById(R.id.register_classes);

	}

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
			
			new ApiClientImpl(this).register(user);
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
