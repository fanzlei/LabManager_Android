/*
 * Copyright (C) 2015 Fanz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fanz.app;

import java.sql.Date;

import com.fanz.app.R;
import com.fanz.api.ApiClientFactory;
import com.fanz.api.ApiClientImpl;
import com.fanz.model.Appo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 进行实验室预约界面 用户可选择日期，实验人数等进行实验室预约
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class Appointment extends Activity {

	private TextView labName, appoint_phone, appoint_name;
	private Spinner date_part, number;
	private DatePicker date;
	private int lab_no;
	private Date dd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.activity_appointment);
		labName = (TextView) this.findViewById(R.id.appointment_labname);
		appoint_phone = (TextView) this.findViewById(R.id.appoint_phone);
		appoint_name = (TextView) this.findViewById(R.id.appoint_name);
		date_part = (Spinner) this.findViewById(R.id.appointment_interval);
		number = (Spinner) this.findViewById(R.id.appointment_number);
		date = (DatePicker) this.findViewById(R.id.appointment_date);
		lab_no = this.getIntent().getExtras().getInt("lab_no");
		labName.setText(this.getIntent().getExtras().getString("name"));
		SharedPreferences sp = this.getSharedPreferences("localSave",
				this.MODE_WORLD_READABLE);
		appoint_name.setText(sp.getString("name", ""));
		appoint_phone.setText(sp.getString("phone", ""));

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		dd = new Date(System.currentTimeMillis());
		date.init(2013, 12, 24, null);

	}

	/** 点击取消按钮，取消预约并跳转到主界面 */
	public void cancle(View v) {
		this.startActivity(new Intent(this, Main.class));

	}

	/** 点击确定按钮，实验室预约 */
	public void make(View v) {
		SharedPreferences sp = this.getSharedPreferences("localSave",
				this.MODE_WORLD_READABLE);
		if (sp.getBoolean("pass_status", false)) {
			Appo appo = new Appo();
			appo.setLab_no(lab_no);
			appo.setDate_part(date_part.getSelectedItemPosition() + 1);
			appo.setNumber(Integer.valueOf((String) number.getSelectedItem()));
			appo.setName(appoint_name.getText().toString());
			appo.setDate(new Date(date.getYear() - 1900, date.getMonth(), date
					.getDayOfMonth()));
			ApiClientFactory.createApiClient(this).addAppo(appo);
		} else {
			Toast.makeText(this, "您的账号尚未被审核通过，请联系管理员", Toast.LENGTH_SHORT)
					.show();
		}

	}
}
