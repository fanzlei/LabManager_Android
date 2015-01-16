package com.fanz.app.activity;

import com.fanz.app.R;
import com.fanz.app.base.App;
import com.fanz.app.base.BaseActivity;
import com.fanz.api.ApiClientFactory;
import com.fanz.model.User;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 修改用户信息界面，用于修改用户密码和手机号
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class ChangeMessage extends BaseActivity {

	EditText oldPass, newPass, newPhone;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.change_message);
		oldPass = (EditText) findViewById(R.id.oldPassword);
		newPass = (EditText) this.findViewById(R.id.newPassword);
		newPhone = (EditText) this.findViewById(R.id.newPhone);
		sp = this.getSharedPreferences("localSave", this.MODE_WORLD_READABLE);
		newPhone.setText(sp.getString("phone", ""));

	}

	/** 点击确定按钮，根据用户输入情况进行信息修改操作 */
	public void change(View v) {
		String op = oldPass.getText().toString().trim();
		String np = newPass.getText().toString().trim();
		String nph = newPhone.getText().toString().trim();
		if (op != null && op != "") {
			// 输入当前密码不为空
			if (op.equals(sp.getString("pass", ""))) {
				// 当前密码输入正确
				if (np != null && np != "") {
					// 输入新密码不为空
					User user = new User();
					user.setName(sp.getString("name", ""));
					user.setPass(np);
					user.setPhone(nph);
					ApiClientFactory.createApiClient(this).updateUser(user);
				} else {
					// 输入新密码为空，此时只修改用户手机号
					User user = new User();
					user.setName(sp.getString("name", ""));
					user.setPass(sp.getString("pass", ""));
					user.setPhone(nph);
					
					// 更新用户
					App.apiClient(this).updateUser(user);
				}
			} else {
				Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "请输入当前密码", Toast.LENGTH_SHORT).show();
		}
	}
}
