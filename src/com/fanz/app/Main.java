package com.fanz.app;

import java.util.Timer;
import java.util.TimerTask;
import com.fanz.app.R;
import com.fanz.widget.fragment.ListFragment;
import com.fanz.widget.fragment.MyListFragment;
import com.fanz.widget.fragment.SettingFragment;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 程序主界面 包含三个fragment 分别为实验室列表、我的预约列表、设置
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class Main extends Activity {

	private boolean prepareExit = false;
	private Fragment listFragment, myListFragment, settingFragment;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listFragment = new ListFragment();
		myListFragment = new MyListFragment();
		settingFragment = new SettingFragment();
		this.getFragmentManager().beginTransaction()
				.replace(R.id.container, listFragment).commit();

		// 启动service，定时获取预定是否被通过
		// setAlarm();
	}

	/** 显示实验室列表的fragment */
	public void showList(View v) {
		getFragmentManager().beginTransaction()
				.replace(R.id.container, listFragment).commit();
	}

	/** 显示我的预约列表的fragment */
	public void showMyList(View v) {
		getFragmentManager().beginTransaction()
				.replace(R.id.container, myListFragment).commit();
	}

	/** 显示设置的fragment */
	public void showConfiguration(View v) {
		getFragmentManager().beginTransaction()
				.replace(R.id.container, settingFragment).commit();
	}

	/** 监听用户按键，实现提示双击退出应用效果 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!prepareExit) {
				prepareExit = true;
				Toast.makeText(Main.this, "再按一次退出", Toast.LENGTH_SHORT).show();
				new Timer().schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						prepareExit = false;
					}
				}, 2000);
			} else {
				Main.this.finish();
			}
		}
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	private void setAlarm() {
		// 创建定时器，没15分钟执行一次本service
		AlarmManager alarm = (AlarmManager) this
				.getSystemService(Service.ALARM_SERVICE);
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
}
