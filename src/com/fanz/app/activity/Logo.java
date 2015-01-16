package com.fanz.app.activity;

import com.fanz.app.R;
import com.fanz.app.base.BaseActivity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * 打开应用的Splash界面 显示一张logo的透明度渐变效果
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class Logo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.logo);
		final ImageView iv = (ImageView) findViewById(R.id.logo_imageView);
		ValueAnimator anim = new ValueAnimator();
		anim.setDuration(2000);
		anim.setFloatValues(0.0f, 1.0f);
		anim.addListener(new AnimatorListener() {
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Logo.this, Login.class);
				Logo.this.startActivity(intent);
				Logo.this.finish();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}
		});
		anim.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				float values = (Float) animation.getAnimatedValue();
				iv.setAlpha(values);
			}
		});
		anim.start();

	}

}
