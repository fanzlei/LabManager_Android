package com.fanz;

import com.net.UserNet;
import com.utils.User;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeMessage extends Activity{

	EditText oldPass,newPass,newPhone;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.change_message);
		oldPass=(EditText) findViewById(R.id.oldPassword);
		newPass=(EditText) this.findViewById(R.id.newPassword);
		newPhone=(EditText) this.findViewById(R.id.newPhone);
		sp=this.getSharedPreferences("localSave", this.MODE_WORLD_READABLE);
		newPhone.setText(sp.getString("phone", ""));
		
	}

	public void change(View v){
		String op=oldPass.getText().toString().trim();
		String np=newPass.getText().toString().trim();
		String nph=newPhone.getText().toString().trim();
		if(op!=null&&op!=""){
			if(op.equals(sp.getString("pass", ""))){
				if(np!=null&&np!=""){
					User user=new User();
					user.setName(sp.getString("name", ""));
					user.setPass(np);
					user.setPhone(nph);
					new UserNet().updateUser(this, user);
				}else{
					User user=new User();
					user.setName(sp.getString("name", ""));
					user.setPass(sp.getString("pass", ""));
					user.setPhone(nph);
					new UserNet().updateUser(this, user);
				}
			}else{Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();}
		}else{ Toast.makeText(this, "请输入当前密码", Toast.LENGTH_SHORT).show();}
	}
}
