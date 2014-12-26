package com.fragment;

import com.fanz.About;
import com.fanz.ChangeMessage;
import com.fanz.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SettingFragment extends PreferenceFragment{

	Context context;
	public SettingFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.setting);
		context=this.getActivity();
		SharedPreferences sp=context.getSharedPreferences("localSave", context.MODE_WORLD_READABLE);
		Preference pp=this.findPreference("acount");
		pp.setTitle(sp.getString("name", ""));
		pp.setSummary(sp.getString("classes", "")+'\t'+sp.getString("phone", "")+'\t'+
				(sp.getBoolean("pass_status", false)==true?"已通过":"未通过"));
		this.findPreference("change").setOnPreferenceClickListener(new OnPreferenceClickListener(){

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,ChangeMessage.class);
				context.startActivity(intent);
				return true;
			}});
		this.findPreference("about").setOnPreferenceClickListener(new OnPreferenceClickListener(){

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,About.class);
				context.startActivity(intent);
				return true;
			}});
		/*this.getPreferenceScreen().setOnPreferenceClickListener(new OnPreferenceClickListener(){

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "按键生效", Toast.LENGTH_SHORT).show();
				if(preference.getTitle().equals("修改信息")){
					Intent intent=new Intent(context,ChangeMessage.class);
					context.startActivity(intent);
				}else if(preference.getTitle().equals("检查更新")){
					
				}else if(preference.getTitle().equals("关于我们")){
					Intent intent=new Intent(context,About.class);
					context.startActivity(intent);
				}
				return true;
			}});*/
	}

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.setting_fragment, container,false);
	}*/

}