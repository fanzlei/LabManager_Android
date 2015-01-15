package com.fanz.api;

import com.fanz.http.SimpleClient;
import com.fanz.http.SimpleRequest;
import com.fanz.http.SimpleRequestParam;
import com.fanz.model.Appo;
import com.fanz.model.Lab;
import com.fanz.model.User;

import android.content.Context;

/**
 * ApiClient接口实现类
 * 
 * @author fanz
 *
 */
public class ApiClientImpl implements ApiClient {
	public static Context context;

	public ApiClientImpl(Context context) {
		this.context = context;
	}

	/**用户登录实现*/
	@Override
	public void login(User user) {
		// TODO Auto-generated method stub
		SimpleRequestParam param=new SimpleRequestParam();
		param.add2Entity("name", user.getName());
		param.add2Entity("pass", user.getPass());
		SimpleRequest request=new SimpleRequest(param);
		SimpleClient client=new SimpleClient(request,"login");
		client.executePost();
	}
	
	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getMyList(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getTeacherByLab_no(Lab lab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAppo(Appo appo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAppo(Appo appo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLabList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAppoListByLab_no(Lab lab) {
		// TODO Auto-generated method stub
		
	}

	

	
}
