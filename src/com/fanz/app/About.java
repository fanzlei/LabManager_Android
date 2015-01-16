package com.fanz.app;

import com.fanz.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * 软件信息显示页面
 * 
 * @author Fanz
 * 
 */
public class About extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.about);
	}

}
