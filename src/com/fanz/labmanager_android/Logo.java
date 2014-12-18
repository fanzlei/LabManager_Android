package com.fanz.labmanager_android;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Logo extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		final ImageView iv=(ImageView) findViewById(R.id.logo_imageView);
		ValueAnimator anim=new ValueAnimator();
		anim.setDuration(2000);
		anim.setFloatValues(0.0f,1.0f);
		anim.addListener(new AnimatorListener(){
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Logo.this,Main.class);
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
				
			}});
		anim.addUpdateListener(new AnimatorUpdateListener(){

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				float values= (Float) animation.getAnimatedValue();
				iv.setAlpha(values);
			}});
		anim.start();
		
	}

	

}
