package com.fanz.api;

import com.fanz.http.SimpleClient;
import com.fanz.http.SimpleRequest;
import com.fanz.http.SimpleRequestParam;
import com.fanz.model.Appointment;
import com.fanz.model.Lab;
import com.fanz.model.User;
import com.fanz.util.Tag;

import android.content.Context;

/**
 * ApiClient接口实现类
 * 
 * 负责android客户端与服务器之间的交互
 * 
 * @author fanz
<<<<<<< HEAD
 * @version 1.0 2015.01.15
=======
 * 
>>>>>>> FETCH_HEAD
 */
public class ApiClientImpl implements ApiClient {
	public static Context context;

	public ApiClientImpl(Context context) {
		this.context = context;
	}

	/** 用户登录实现 */
	@Override
	public void login(User user) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "login");
		params.add("name", user.getName());
		params.add("pass", user.getPass());
		new SimpleClient(new SimpleRequest(params), Tag.LOGIN).executePost();
	}

	/** 注册用户 */
	@Override
	public void register(User user) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "register");
		params.add("name", user.getName());
		params.add("pass", user.getPass());
		params.add("phone", user.getPhone());
		params.add("classes", user.getClasses());
		
		new SimpleClient(new SimpleRequest(params), Tag.REGISTER).executePost();
	}

	/** 修改用户密码 */
	@Override
	public void updateUser(User user) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "updateUser");
		params.add("name", user.getName());
		params.add("pass", user.getPass());
		params.add("phone", user.getPhone());
		
		new SimpleClient(new SimpleRequest(params), Tag.UPDATE_USER).executePost();
	}

	/** 获取我的预约列表 */
	@Override
	public void getMyList(User user) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "getMyList");
		params.add("name", user.getName());
		
		new SimpleClient(new SimpleRequest(params), Tag.GET_MY_LIST).executePost();
	}

	/** 通过实验室编号获取该实验室的管理员 */
	@Override
	public void getTeacherByLab_no(Lab lab) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "getTeacherByLab_no");
		params.add("name", String.valueOf(lab.getLab_no()));
		
		new SimpleClient(new SimpleRequest(params), Tag.GET_TEACHER_BY_NO).executePost();
	}

	/** 添加预约 */
	@Override
	public void addAppo(Appointment appo) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "add");
		params.add("name", appo.getName());
		params.add("date", appo.getDate().toString());
		params.add("date_part", String.valueOf(appo.getDate_part()));
		params.add("lab_no", String.valueOf(appo.getLab_no()));
		params.add("number", String.valueOf(appo.getNumber()));
		
		new SimpleClient(new SimpleRequest(params), Tag.ADD_APPOINTMENT).executePost();
	}

	/** 删除预约 */
	@Override
	public void deleteAppo(Appointment appo) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "delete");
		params.add("id", String.valueOf(appo.getId()));
		
		new SimpleClient(new SimpleRequest(params), Tag.REMOVE_APPOINTMENT).executePost();
	}

	/** 获取实验室列表 */
	@Override
	public void getLabList() {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "getLabList");
		
		new SimpleClient(new SimpleRequest(params), Tag.GET_LAB_LIST).executePost();
	}

	/** 通过实验室编号获取该实验的预约列表 */
	@Override
	public void getAppoListByLab_no(Lab lab) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "getAppoListByLab_no");
		params.add("lab_no", String.valueOf(lab.getLab_no()));
		
		new SimpleClient(new SimpleRequest(params), Tag.GET_LAB_APPOINTMENT_LIST).executePost();
	}

}
