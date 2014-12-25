package com.fragment;

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

import com.fanz.Detail;
import com.fanz.R;
import com.net.AppoNet;
import com.utils.Lab;

public class ListFragment extends Fragment{

    ListView listView ;
    JSONArray ja;
    Context context;
    Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==222){
				ja=(JSONArray) msg.obj;
				setAdapter();
			}
		}

		private void setAdapter() {
			// TODO Auto-generated method stub
			 
			    SharedPreferences sp=context.getSharedPreferences("localSave",context.MODE_WORLD_READABLE);
			    SharedPreferences.Editor ed=sp.edit();
		        List<Map<String,Object>> list=new ArrayList<>();
		        for(int i=0;i<ja.length();i++){
		        	Map<String,Object> map=new HashMap<String,Object>();
		        	try {
						JSONObject jo=(JSONObject) ja.get(i);
						map.put("name", jo.getString("name"));
						map.put("id", jo.getInt("id"));
						map.put("lab_no", jo.getInt("lab_no"));
						map.put("describe", jo.getString("describe"));
						//把Lab_no对应的Lab_name存入SharedPreference中，
						//用于MyListFragment中将lab_no转换为lab_name
						ed.putString(String.valueOf(jo.getInt("lab_no")), jo.getString("name"));
						list.add(map);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	ed.commit();
		        }
		        SimpleAdapter adapter = new SimpleAdapter(context,list,R.layout.list_item,new String[]{"name","describe","lab_no"},
		                new int[]{R.id.list_item_name,R.id.list_item_describe,R.id.list_item_lab_no});
		        listView.setAdapter(adapter);
		}
    };
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		return inflater.inflate(R.layout.list_fragment,container,false);
	}

   

	@Override
    public void onStart() {
        super.onStart();
        context=this.getActivity();
        new AppoNet().getLabList(context, handler);
        listView= (ListView)this.getActivity().findViewById(R.id.list_fragment_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            	JSONObject jo;
            	
            	try {
            		jo=(JSONObject) ja.get(i);
					
					
					Intent intent=new Intent(context,Detail.class);
					intent.putExtra("lab_no",jo.getInt("lab_no"));
					intent.putExtra("name", jo.getString("name"));
	            	context.startActivity(intent);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
}
