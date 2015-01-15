package com.fanz.app;

import com.fanz.app.R;
import com.fanz.api.ApiClientImpl;
import com.fanz.model.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class Login extends BaseActivity {

	String name = "", pass = "";
	EditText ed1, ed2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.login);
		ed1 = (EditText) findViewById(R.id.login_name);
		ed2 = (EditText) findViewById(R.id.login_pass);

	}

	public void login(View v) {
		name = ed1.getText().toString().trim();
		pass = ed2.getText().toString().trim();
		User user = new User();
		user.setName(name);
		user.setPass(pass);
		
		new ApiClientImpl(this).login(user);
	}

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
