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

	public void login(View v) {
		String username = editUsername.getText().toString().trim();
		String password = editPassword.getText().toString().trim();

		App.apiClient(this).login(new User(username, password));
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
