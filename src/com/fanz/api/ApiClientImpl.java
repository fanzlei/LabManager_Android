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

	/** 用户登录实现 */
	@Override
	public void login(User user) {
		// TODO Auto-generated method stub
		SimpleRequestParam param = new SimpleRequestParam();
		param.add2Entity("tag", "login");
		param.add2Entity("name", user.getName());
		param.add2Entity("pass", user.getPass());
		SimpleRequest request = new SimpleRequest(param);
		SimpleClient client = new SimpleClient(request, "login");
		client.executePost();
	}

	/** 注册用户 */
	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		SimpleRequestParam param = new SimpleRequestParam();
		param.add2Entity("tag", "register");
		param.add2Entity("name", user.getName());
		param.add2Entity("pass", user.getPass());
		param.add2Entity("phone", user.getPhone());
		param.add2Entity("classes", user.getClasses());
		SimpleRequest request = new SimpleRequest(param);
		SimpleClient client = new SimpleClient(request, "register");
		client.executePost();
	}

	/** 修改用户密码 */
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		SimpleRequestParam param = new SimpleRequestParam();
		param.add2Entity("tag", "updateUser");
		param.add2Entity("name", user.getName());
		param.add2Entity("pass", user.getPass());
		param.add2Entity("phone", user.getPhone());
		SimpleRequest request = new SimpleRequest(param);
		SimpleClient client = new SimpleClient(request, "updateUser");
		client.executePost();
	}

	/** 获取我的预约列表 */
	@Override
	public void getMyList(User user) {
		// TODO Auto-generated method stub
		SimpleRequestParam param = new SimpleRequestParam();
		param.add2Entity("tag", "getMyList");
		param.add2Entity("name", user.getName());
		SimpleRequest request = new SimpleRequest(param);
		SimpleClient client = new SimpleClient(request, "getMyList");
		client.executePost();
	}

	/** 通过实验室编号获取该实验室的管理员 */
	@Override
	public void getTeacherByLab_no(Lab lab) {
		// TODO Auto-generated method stub
		SimpleRequestParam param = new SimpleRequestParam();
		param.add2Entity("tag", "getTeacherByLab_no");
		param.add2Entity("name", String.valueOf(lab.getLab_no()));
		SimpleRequest request = new SimpleRequest(param);
		SimpleClient client = new SimpleClient(request, "getTeacherByLab_no");
		client.executePost();
	}

	/** 添加预约 */
	@Override
	public void addAppo(Appo appo) {
		// TODO Auto-generated method stub
		SimpleRequestParam param = new SimpleRequestParam();
		param.add2Entity("tag", "add");
		param.add2Entity("name", appo.getName());
		param.add2Entity("date", appo.getDate().toString());
		param.add2Entity("date_part", String.valueOf(appo.getDate_part()));
		param.add2Entity("lab_no", String.valueOf(appo.getLab_no()));
		param.add2Entity("number", String.valueOf(appo.getNumber()));
		SimpleRequest request = new SimpleRequest(param);
		SimpleClient client = new SimpleClient(request, "getTeacherByLab_no");
		client.executePost();
	}

	/** 删除预约 */
	@Override
	public void deleteAppo(Appo appo) {
		// TODO Auto-generated method stub
		SimpleRequestParam param = new SimpleRequestParam();
		param.add2Entity("tag", "delete");
		param.add2Entity("id", String.valueOf(appo.getId()));
		SimpleRequest request = new SimpleRequest(param);
		SimpleClient client = new SimpleClient(request, "delete");
		client.executePost();
	}

	/** 获取实验室列表 */
	@Override
	public void getLabList() {
		// TODO Auto-generated method stub
		SimpleRequestParam param = new SimpleRequestParam();
		param.add2Entity("tag", "getLabList");
		SimpleRequest request = new SimpleRequest(param);
		SimpleClient client = new SimpleClient(request, "getLabList");
		client.executePost();
	}

	/** 通过实验室编号获取该实验的预约列表 */
	@Override
	public void getAppoListByLab_no(Lab lab) {
		// TODO Auto-generated method stub
		SimpleRequestParam param = new SimpleRequestParam();
		param.add2Entity("tag", "getAppoListByLab_no");
		param.add2Entity("lab_no", String.valueOf(lab.getLab_no()));
		SimpleRequest request = new SimpleRequest(param);
		SimpleClient client = new SimpleClient(request, "getAppoListByLab_no");
		client.executePost();
	}

}
