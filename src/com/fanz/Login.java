package com.fanz;

import com.fanz.R;
import com.net.UserNet;
import com.utils.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class Login extends Activity{

	String name="",pass="";
	EditText ed1,ed2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.login);
		ed1= (EditText) findViewById(R.id.login_name);
		ed2=(EditText) findViewById(R.id.login_pass);
		
	}

	public void login(View v){
		name=ed1.getText().toString().trim();
		pass=ed2.getText().toString().trim();
		User user=new User();
		user.setName(name);
		user.setPass(pass);
		new UserNet().login(this,user);
	}
	public void register(View v){
		Intent intent=new Intent(this,Register.class);
		this.startActivity(intent);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.finish();
	}

	
}
