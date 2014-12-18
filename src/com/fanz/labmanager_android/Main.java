package com.fanz.labmanager_android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Main extends Activity {

	boolean prepareExit=false;
	Fragment listFragment,myListFragment,settingFragment;
    ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listFragment=new ListFragment();
		myListFragment=new MyListFragment();
		settingFragment=new SettingFragment();
		this.getFragmentManager().beginTransaction().replace(R.id.container, listFragment).commit();

	}

	public void showList(View v){
		getFragmentManager().beginTransaction().replace(R.id.container, listFragment).commit();
	}
    public void showMyList(View v){
    	getFragmentManager().beginTransaction().replace(R.id.container, myListFragment).commit();
	}
    public void showConfiguration(View v){
    	getFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if(keyCode==KeyEvent.KEYCODE_BACK){
			if(!prepareExit){
				prepareExit=true;
				Toast.makeText(Main.this, "再按一次退出", Toast.LENGTH_SHORT).show();
				new Timer().schedule(new TimerTask(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						prepareExit=false;
					}}, 2000);
			}else{Main.this.finish();}
		}
		return true;
	}


}
