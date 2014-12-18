package com.fanz.labmanager_android;

import android.app.Fragment;
import android.os.Bundle;
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

public class ListFragment extends Fragment{

    ListView listView ;
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
        listView= (ListView)this.getActivity().findViewById(R.id.list_fragment_list);

        String[] names={
                "实验室一",
                "实验室二",
                "实验室三",
                "实验室四",
                "实验室五",
                "实验室六",
                "实验室七",
                "实验室八",
                "实验室九",
                "实验室十",
        };
        int[] images={
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
        };
        String[] describe={
                "实验室功能简介",
                "实验室功能简介",
                "实验室功能简介",
                "实验室功能简介",
                "实验室功能简介",
                "实验室功能简介",
                "实验室功能简介",
                "实验室功能简介",
                "实验室功能简介",
                "实验室功能简介",
        };
        List<Map<String,Object>> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("name",names[i]);
            map.put("image",images[i]);
            map.put("describe",describe[i]);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this.getActivity(),list,R.layout.list_item,new String[]{"name","image","describe"},
                new int[]{R.id.list_item_name,R.id.list_item_image,R.id.list_item_describe});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
