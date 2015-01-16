package com.fanz.app;

import com.fanz.app.R;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * 打开应用的Splash界面 显示一张logo的透明度渐变效果
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class Logo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
