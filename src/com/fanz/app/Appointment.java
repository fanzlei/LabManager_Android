package com.fanz.app;

import java.sql.Date;

import com.fanz.app.R;

import com.fanz.api.AppoNet;
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

public class Appointment extends Activity {

	TextView labName, appoint_phone, appoint_name;
	Spinner date_part, number;
	DatePicker date;
	int lab_no;
	Date dd;

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

	public void cancle(View v) {
		this.startActivity(new Intent(this, Main.class));

	}

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
			new AppoNet().add(this, appo);
		} else {
			Toast.makeText(this, "您的账号尚未被审核通过，请联系管理员", Toast.LENGTH_SHORT)
					.show();
		}

	}
}