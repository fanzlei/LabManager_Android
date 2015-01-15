package com.fanz.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fanz.app.R;
import com.fanz.api.ApiClientImpl;
import com.fanz.model.Lab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Detail extends Activity {

	static ListView listView;
	static JSONArray ja;
	static JSONObject jo;
	static Activity activity;
	public static Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
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
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
		new ApiClientImpl(this).getAppoListByLab_no(lab);
		new ApiClientImpl(this).getTeacherByLab_no(lab);
	}

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

	public void appointment(View v) {
		Intent intent = new Intent(this, Appointment.class);
		Bundle bundle = this.getIntent().getExtras();
		intent.putExtras(bundle);
		this.startActivity(intent);

	}

	public void back(View v) {
		Intent intent = new Intent(this, Main.class);
		this.startActivity(intent);
		this.finish();
	}

}
