package com.fanz.widget.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fanz.app.R;
import com.fanz.api.ApiClientFactory;
import com.fanz.api.ApiClientImpl;
import com.fanz.app.Detail;

/**
<<<<<<< HEAD
 * 显示实验室列表的fragment
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class ListFragment extends Fragment {

	private static ListView listView;
	private static JSONArray ja;
	private static Context context;
=======
 * 列表fragment的简单封装
 * 
 * @author fanz
 * 
 */
public class ListFragment extends Fragment {

	static ListView listView;
	static JSONArray jsons;
	static Context context;

>>>>>>> FETCH_HEAD
	public static Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 222) {
				jsons = (JSONArray) msg.obj;
				setAdapter();
			}
		}

		/** 设置显示实验室列表的适配器 */
		private void setAdapter() {

			SharedPreferences sp = context.getSharedPreferences("localSave",
					context.MODE_WORLD_READABLE);
			SharedPreferences.Editor ed = sp.edit();

			List<Map<String, Object>> list = new ArrayList<>();
			for (int i = 0; i < jsons.length(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				try {
					JSONObject json = (JSONObject) jsons.get(i);
					map.put("name", json.getString("name"));
					map.put("id", json.getInt("id"));
					map.put("lab_no", json.getInt("lab_no"));
					map.put("describe", json.getString("describe"));

					// 把Lab_no对应的Lab_name存入SharedPreference中，
					// 用于MyListFragment中将lab_no转换为lab_name
					ed.putString(String.valueOf(json.getInt("lab_no")),
							json.getString("name"));

					list.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				ed.commit();
			}
			SimpleAdapter adapter = new SimpleAdapter(context, list,
					R.layout.list_item, new String[] { "name", "describe",
							"lab_no" }, new int[] { R.id.list_item_name,
							R.id.list_item_describe, R.id.list_item_lab_no });
			listView.setAdapter(adapter);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.list_fragment, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		context = this.getActivity();
		ApiClientFactory.createApiClient(context).getLabList();
		listView = (ListView) this.getActivity().findViewById(
				R.id.list_fragment_list);
		//实验室列表列表项用户点击事件监听
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int i, long l) {

				JSONObject json;

				try {
					json = (JSONObject) jsons.get(i);

					Intent intent = new Intent(context, Detail.class);
					intent.putExtra("lab_no", json.getInt("lab_no"));
					intent.putExtra("name", json.getString("name"));
					context.startActivity(intent);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
