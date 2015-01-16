package com.fanz.app;

import java.sql.Date;

import com.fanz.app.R;
import com.fanz.api.ApiClientFactory;
import com.fanz.api.ApiClientImpl;
import com.fanz.model.Appointment;
import com.fanz.util.MessageUtil;

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
 * 实验室预约操作页面
 * 
 * @author Fanz
 * 
 */
public class AppointmentActivity extends BaseActivity {

	TextView tvLabName, tvAppointPhone, tvAppointName;
	Spinner spinDatePart, spinNumber;
	DatePicker datepicker;

	int labNo;
	Date dd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_appointment);

		initView();

		labNo = this.getIntent().getExtras().getInt("lab_no");
		tvLabName.setText(this.getIntent().getExtras().getString("name"));
		SharedPreferences sp = this.getSharedPreferences("localSave",
				this.MODE_WORLD_READABLE);

		tvAppointName.setText(sp.getString("name", ""));
		tvAppointPhone.setText(sp.getString("phone", ""));
	}

	private void initView() {
		tvLabName = (TextView) this.findViewById(R.id.appointment_labname);
		tvAppointPhone = (TextView) this.findViewById(R.id.appoint_phone);
		tvAppointName = (TextView) this.findViewById(R.id.appoint_name);
		spinDatePart = (Spinner) this.findViewById(R.id.appointment_interval);
		spinNumber = (Spinner) this.findViewById(R.id.appointment_number);
		datepicker = (DatePicker) this.findViewById(R.id.appointment_date);
	}

	@Override
	protected void onResume() {
		super.onResume();
		dd = new Date(System.currentTimeMillis());
		datepicker.init(2013, 12, 24, null);

	}

	public void cancle(View v) {
		this.startActivity(new Intent(this, Main.class));

	}

	public void make(View v) {
		SharedPreferences sp = this.getSharedPreferences("localSave",
				this.MODE_WORLD_READABLE);
		if (sp.getBoolean("pass_status", false)) {
			Appointment appo = new Appointment();
			appo.setLab_no(labNo);
			appo.setDate_part(spinDatePart.getSelectedItemPosition() + 1);
			appo.setNumber(Integer.valueOf((String) spinNumber
					.getSelectedItem()));
			appo.setName(tvAppointName.getText().toString());
			appo.setDate(new Date(datepicker.getYear() - 1900, datepicker
					.getMonth(), datepicker.getDayOfMonth()));
			App.apiClient(this).addAppo(appo);
		} else {
			MessageUtil.shortMessage(this, "您的账号尚未被审核通过，请联系管理员");
		}

	}
}
