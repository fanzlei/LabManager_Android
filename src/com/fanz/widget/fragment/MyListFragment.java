package com.fanz.widget.fragment;

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
import com.fanz.model.Appointment;
import com.fanz.model.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * 显示我的预约列表的fragment
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class MyListFragment extends Fragment {

	private static ListView listView;
	private static JSONArray ja;
	private static Activity activity;
	private static int deletedId = -1;
	public static Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 111) {
				ja = (JSONArray) msg.obj;
				setAdapter();
			}

		}

	};

	/** 设置显示我的预约列表的适配器 */
	public static void setAdapter() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SharedPreferences sp = activity.getSharedPreferences("localSave",
				activity.MODE_WORLD_READABLE);
		SharedPreferences.Editor ed = sp.edit();
		for (int i = 0; i < ja.length(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				JSONObject jo = (JSONObject) ja.get(ja.length() - i - 1);
				// 用户名不用显示了，把实验室编号转换为实验室名称
				map.put("name", lab_noToLab_name(jo.getInt("lab_no")));
				map.put("date", jo.getString("date"));
				map.put("date_part", date_partToString(jo.getInt("date_part")));
				map.put("number", jo.getInt("number"));
				map.put("pass_status", jo.getInt("pass_status") == 1 ? "未审核"
						: jo.getInt("pass_status") == 2 ? "已通过" : "被拒绝");
				map.put("id", jo.getInt("id"));
				// 把我的预约ID对应的pass_status保存到本地。
				// 在后台service查询pass_status的时候比较本地的pass_status是否一致，不一致则发送通知。
				ed.putInt(String.valueOf(jo.getInt("id")),
						jo.getInt("pass_status"));
				list.add(map);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ed.commit();
		SimpleAdapter adapter = new SimpleAdapter(activity, list,
				R.layout.mylist_item, new String[] { "name", "date",
						"date_part", "number", "pass_status" }, new int[] {
						R.id.mylist_item_name, R.id.mylist_item_date,
						R.id.mylist_item_date_part, R.id.mylist_item_number,
						R.id.mylist_item_status });

		listView.setAdapter(adapter);
	}

	/** 把实验室编号转换为实验室名称，用于我的预约列表中显示 */
	private static String lab_noToLab_name(int i) {
		SharedPreferences sp = activity.getSharedPreferences("localSave",
				activity.MODE_WORLD_READABLE);
		return sp.getString(String.valueOf(i), "error");
	}

	/** 实验室预约时段转换为字符串 */
	private static String date_partToString(int i) {
		switch (i) {
		case 1:
			return "上午一二节";
		case 2:
			return "上午三四节";
		case 3:
			return "下午五六节";
		case 4:
			return "下午七八节";
		case 5:
			return "晚上九十节";
		default:
			return "error";
		}

	}

	public MyListFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		activity = this.getActivity();
		listView = (ListView) this.getActivity().findViewById(
				R.id.mylist_fragment_list);
		User user = new User();
		SharedPreferences sp = activity.getSharedPreferences("localSave",
				activity.MODE_WORLD_READABLE);
		user.setName(sp.getString("name", ""));
		ApiClientFactory.createApiClient(activity).getMyList(user);
		// 我的预约列表中列表项用户点击事件监听
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(activity)
						.setTitle("取消预定？")
						.setNegativeButton("取消", null)
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										Appointment appo = new Appointment();
										HashMap<String, Object> map = (HashMap<String, Object>) parent
												.getItemAtPosition(position);
										System.out.println("id="
												+ map.get("id"));
										appo.setId((int) map.get("id"));
										deletedId = (int) map.get("id");
										new ApiClientImpl(activity)
												.deleteAppo(appo);
									}
								}).show();

			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		return inflater.inflate(R.layout.mylist_fragment, container, false);
	}

	/*
	 * public static class MyAdapter extends BaseAdapter{
	 * 
	 * Activity ac; public MyAdapter(Activity a){ ac=a; }
	 * 
	 * @Override public int getCount() { // TODO Auto-generated method stub
	 * return ja.length(); }
	 * 
	 * @Override public Object getItem(int position) { // TODO Auto-generated
	 * method stub JSONObject jo=null; try { jo=ja.getJSONObject(position); }
	 * catch (JSONException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return jo; }
	 * 
	 * @Override public long getItemId(int position) { // TODO Auto-generated
	 * method stub JSONObject jo=null; int id=-1; try {
	 * jo=ja.getJSONObject(position); id=jo.getInt("id"); } catch (JSONException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } return id;
	 * }
	 * 
	 * @Override public View getView(int position, View convertView, ViewGroup
	 * parent) { // TODO Auto-generated method stub LayoutInflater inflater=
	 * LayoutInflater.from(ac); View v= inflater.inflate(R.layout.mylist_item,
	 * parent); TextView date,date_part,lab_name,number,pass_status;
	 * 
	 * date=(TextView) v.findViewById(R.id.mylist_item_date);
	 * date_part=(TextView) v.findViewById(R.id.mylist_item_date_part);
	 * lab_name=(TextView) v.findViewById(R.id.mylist_item_name);
	 * number=(TextView) v.findViewById(R.id.mylist_item_number);
	 * pass_status=(TextView) v.findViewById(R.id.mylist_item_status);
	 * JSONObject jo=(JSONObject) getItem(position); try {
	 * System.out.println(date.getText().toString());
	 * System.out.println(jo.getString("date"));
	 * date.setText(jo.getString("date"));
	 * date_part.setText(jo.getInt("date_part"));
	 * lab_name.setText(jo.getInt("lab_no"));
	 * number.setText(jo.getInt("number")); if(jo.getBoolean("pass_status")){
	 * pass_status.setText("已通过"); }else{pass_status.setText("未通过");} } catch
	 * (JSONException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return v; }
	 * 
	 * }
	 */
}
