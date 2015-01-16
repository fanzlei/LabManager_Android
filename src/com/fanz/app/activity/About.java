package com.fanz.app.activity;

import com.fanz.app.R;
import com.fanz.app.base.BaseActivity;

import android.os.Bundle;

/**
 * 软件信息和作者信息显示界面
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class About extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.about);
	}

}
