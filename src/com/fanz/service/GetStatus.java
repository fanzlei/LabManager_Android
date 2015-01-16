package com.fanz.service;

import java.util.Timer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fanz.api.ApiClientImpl;
import com.fanz.app.R;
import com.fanz.app.activity.Main;
import com.fanz.model.User;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class GetStatus extends Service {

	JSONArray jsons;
	SharedPreferences sp;
	AlarmManager alarm;
	Timer timer;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 111) {
				jsons = (JSONArray) msg.obj;
				sendNotification();
			}
		}
	};

	private void sendNotification() {
		JSONObject json;
		Intent inn = new Intent(this, Main.class);
		PendingIntent ppi = PendingIntent.getActivity(this, 0, inn, 0);
		for (int i = 0; i < jsons.length(); i++) {
			try {
				json = (JSONObject) jsons.get(i);
				if (json.getInt("pass_status") != sp.getInt(
						String.valueOf(json.getInt("id")), 1)) {
					NotificationManager nm = (NotificationManager) this
							.getSystemService(Service.NOTIFICATION_SERVICE);
					if (json.getInt("pass_status") == 2) {
						Notification nf = new Notification.Builder(
								GetStatus.this)
								.setTicker("实验室预定已通过")
								.setContentTitle(
										lab_noToLab_name(json.getInt("lab_no"))
												+ "已通过")
								.setDefaults(
										Notification.DEFAULT_SOUND
												| Notification.DEFAULT_LIGHTS)
								.setWhen(System.currentTimeMillis())
								.setContentText(
										"实验日期："
												+ json.getString("date")
												+ '\t'
												+ date_partToString(json
														.getInt("date_part")))
								.setContentIntent(ppi).setAutoCancel(true)
								.setSmallIcon(R.drawable.ic_launcher)
								.getNotification();
						nm.notify(12345, nf);
						SharedPreferences.Editor ed = sp.edit();
						ed.putString(String.valueOf(json.getInt("id")),
								String.valueOf(json.getInt("pass_status")));
						ed.commit();
					} else if (json.getInt("pass_status") == 3) {
						Notification nf = new Notification.Builder(
								GetStatus.this)
								.setTicker("实验室预定被拒绝")
								.setContentTitle(
										lab_noToLab_name(json.getInt("lab_no"))
												+ "被拒绝")
								.setDefaults(
										Notification.DEFAULT_SOUND
												| Notification.DEFAULT_LIGHTS)
								.setWhen(System.currentTimeMillis())
								.setContentText(
										"实验日期："
												+ json.getString("date")
												+ '\t'
												+ date_partToString(json
														.getInt("date_part")))
								.setContentIntent(ppi).setAutoCancel(true)
								.setSmallIcon(R.drawable.ic_launcher)
								.getNotification();
						nm.notify(12346, nf);
						SharedPreferences.Editor ed = sp.edit();
						ed.putString(String.valueOf(json.getInt("id")),
								String.valueOf(json.getInt("pass_status")));
						ed.commit();
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
				System.out.println("serviece stoped");
				this.stopSelf();
			}
		}
		this.stopSelf();
		System.out.println("serviece stoped");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private String lab_noToLab_name(int i) {
		SharedPreferences sp = this.getSharedPreferences("localSave",
				this.MODE_WORLD_READABLE);
		return sp.getString(String.valueOf(i), "error");
	}

	private String date_partToString(int i) {
		switch (i) {
		case 1:
			return "上午一二节";
		case 2:
			return "上午三四节";
		case 3:
			return "下午五六节";
		case 4:
			return "下午七八节";
		case 5:
			return "晚上九十节";
		default:
			return "error";
		}

	}

	@Override
	public void onCreate() {
		super.onCreate();
		sp = this.getSharedPreferences("localSave", this.MODE_WORLD_READABLE);

		// setAlarm();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		User user = new User();
		user.setName(sp.getString("name", ""));
		System.out.println("serviece started");
		new ApiClientImpl(this).getUserAppointments(user);

		return super.onStartCommand(intent, flags, startId);
	}

	private void setAlarm() {
		// 创建定时器，没15分钟执行一次本service
		alarm = (AlarmManager) this.getSystemService(Service.ALARM_SERVICE);
		Intent in = new Intent();
		in.setAction("com.service.getPassStatus");
		PendingIntent pi = PendingIntent.getService(this, 0, in, 0);
		alarm.setRepeating(AlarmManager.ELAPSED_REALTIME, 60, 60, pi);
		/*
		 * timer=new Timer(); timer.schedule(new TimerTask(){
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * GetStatus.this.startService(new Intent("com.service.getPassStatus"));
		 * }}, 20000,20000);
		 */
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		/*
		 * Intent in=new Intent(); in.setAction("com.service.getPassStatus");
		 * PendingIntent pi=PendingIntent.getService(this, 0, in, 0);
		 * alarm.cancel(pi);
		 */
		// timer.cancel();
	}

}
