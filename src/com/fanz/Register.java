package com.fanz;

import com.net.UserNet;
import com.utils.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity{

	EditText name,pass,phone,classes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.register);
		name=(EditText) findViewById(R.id.register_name);
		pass=(EditText) findViewById(R.id.register_pass);
		phone=(EditText) findViewById(R.id.register_phone);
		classes=(EditText) findViewById(R.id.register_classes);
		
	}

	public void register(View v){
		if(name.getText().toString().trim().length()>0&&
		   pass.getText().toString().trim().length()>0&&
		   phone.getText().toString().trim().length()>2&&
		   classes.getText().toString().trim().length()>2){
			User user=new User();
			user.setName(name.getText().toString().trim());
			user.setPass(pass.getText().toString().trim());
			user.setPhone(phone.getText().toString().trim());
			user.setClasses(classes.getText().toString().trim());
			new UserNet().register(this, user);
		}else{
			Toast.makeText(this, "请输入正确格式信息", Toast.LENGTH_SHORT).show();
		}
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.finish();
	}

	
}
