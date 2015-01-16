package com.fanz.app.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fanz.app.R;
import com.fanz.app.base.App;
import com.fanz.app.base.BaseActivity;
import com.fanz.model.Lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * 实验室预约列表显示界面
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public class Detail extends BaseActivity {

	private static ListView listView;
	private static JSONArray ja;
	private static JSONObject jo;
	private static Activity activity;
	/** 处理服务器返回信息的Handler */
	public static Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 333) {
				ja = (JSONArray) msg.obj;
				setAdapter();
			}
			if (msg.what == 444) {
				jo = (JSONObject) msg.obj;
				setTeacherMessage();
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_detail);
		activity = this;
		Bundle bundle = this.getIntent().getExtras();
		String name = bundle.getString("name");
		int lab_no = bundle.getInt("lab_no");
		Lab lab = new Lab();
		lab.setLab_no(lab_no);
		TextView Tname = (TextView) this.findViewById(R.id.detail_name);
		Tname.setText(name);
		listView = (ListView) this.findViewById(R.id.detail_list);
		
		App.apiClient(this).getAppoListByLab_no(lab);
		App.apiClient(this).getTeacherByLab_no(lab);
	}

	/** 设置显示实验室管理教师信息 */
	private static void setTeacherMessage() {
		// TODO Auto-generated method stub
		TextView tv = (TextView) activity
				.findViewById(R.id.detail_teacher_message);
		try {
			tv.setText("管理教师：" + jo.getString("name") + '\t' + "手机："
					+ jo.getString("phone"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 设置实验室预约列表适配器 */
	private static void setAdapter() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < ja.length(); i++) {
			try {
				JSONObject jo = (JSONObject) ja.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("date", jo.getString("date"));
				map.put("date_part", date_partToString(jo.getInt("date_part")));
				map.put("name", jo.getString("name"));
				map.put("number", jo.getInt("number"));
				map.put("pass_status", jo.getInt("pass_status") == 1 ? "未审核"
						: jo.getInt("pass_status") == 2 ? "已通过" : "被拒绝");
				map.put("classes", jo.getString("classes"));
				map.put("phone", jo.getString("phone"));
				list.add(map);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		SimpleAdapter adapter = new SimpleAdapter(activity, list,
				R.layout.detail_list_item, new String[] { "date", "date_part",
						"name", "number", "pass_status", "classes", "phone" },
				new int[] { R.id.detail_item_date, R.id.detail_item_date_part,
						R.id.detail_name, R.id.detail_item_number,
						R.id.detail_item_status, R.id.detail_item_classes,
						R.id.detail_item_date_phone });
		listView.setAdapter(adapter);
	}

	/** 实验室预约时间段转换为文字形式 */
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

	/** 点击我要预约按钮，进入实验室预约界面 */
	public void appointment(View v) {
		Intent intent = new Intent(this, AppointmentActivity.class);
		Bundle bundle = this.getIntent().getExtras();
		intent.putExtras(bundle);
		this.startActivity(intent);

	}

	/** 点击返回按钮，返回主界面 */
	public void back(View v) {
		Intent intent = new Intent(this, Main.class);
		this.startActivity(intent);
		this.finish();
	}

}
