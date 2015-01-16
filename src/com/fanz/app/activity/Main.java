package com.fanz.app.activity;

import java.util.Timer;
import java.util.TimerTask;
import com.fanz.app.R;
import com.fanz.app.base.BaseActivity;
import com.fanz.util.MessageUtil;
import com.fanz.widget.fragment.ListFragment;
import com.fanz.widget.fragment.MyListFragment;
import com.fanz.widget.fragment.SettingFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

/**
 * 程序主界面 包含三个fragment 分别为实验室列表、我的预约列表、设置
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class Main extends BaseActivity {

	private boolean prepareExit = false;
	private Fragment listFragment, myListFragment, settingFragment;
	private ListView listView;

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

	/** 显示实验室列表的fragment */
	public void showList(View v) {
		showFragment(v, listFragment);
	}

	/** 显示我的预约列表的fragment */
	public void showMyList(View v) {
		showFragment(v, myListFragment);
	}

	/** 显示设置的fragment */
	public void showConfiguration(View v) {
		showFragment(v, settingFragment);
	}

	private void showFragment(View v, Fragment fragment) {
		getFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();
	}

	/** 监听用户按键，实现提示双击退出应用效果 */
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
