package com.fanz.app;

import java.util.Timer;
import java.util.TimerTask;
import com.fanz.app.R;
import com.fanz.util.MessageUtil;
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
 * 软件主页面
 * 
 * @author Fanz
 * 
 */
public class Main extends BaseActivity {

	boolean prepareExit = false;
	Fragment listFragment, myListFragment, settingFragment;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		// 初始化fragments
		listFragment = new ListFragment();
		myListFragment = new MyListFragment();
		settingFragment = new SettingFragment();
		this.getFragmentManager().beginTransaction()
				.replace(R.id.container, listFragment).commit();

	}

	public void showList(View v) {
		showFragment(v, listFragment);
	}

	public void showMyList(View v) {
		showFragment(v, myListFragment);
	}

	public void showConfiguration(View v) {
		showFragment(v, settingFragment);
	}

	private void showFragment(View v, Fragment fragment) {
		getFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();
	}

	// 双击提示退出应用
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!prepareExit) {
				prepareExit = true;
				MessageUtil.shortMessage(Main.this, "再按一次退出");
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						prepareExit = false;
					}
				}, 2000);
			} else {
				// 退出应用
				Main.this.finish();
			}
		}
		return true;
	}

}
