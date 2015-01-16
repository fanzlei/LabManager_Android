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
 * @version 1.0 2015.01.15
 */
public class ApiClientImpl implements ApiClient {
	private Context context;

	public ApiClientImpl(Context context) {
		this.context = context;
	}

	/**
	 * 发送post请求到服务器
	 */
	private void postRequest(SimpleRequestParam params, int tag) {
		SimpleRequest request = new SimpleRequest(params);

		new SimpleClient(this.context, request, tag).executePost();
	}

	/** 用户登录实现 */
	@Override
	public void login(User user) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "login");
		params.add("name", user.getName());
		params.add("pass", user.getPass());

		postRequest(params, Tag.LOGIN);
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

		postRequest(params, Tag.REGISTER);
	}

	/** 修改用户密码 */
	@Override
	public void updateUser(User user) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "updateUser");
		params.add("name", user.getName());
		params.add("pass", user.getPass());
		params.add("phone", user.getPhone());

		postRequest(params, Tag.UPDATE_USER);
	}

	/** 获取我的预约列表 */
	@Override
	public void getUserAppointments(User user) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "getMyList");
		params.add("name", user.getName());

		postRequest(params, Tag.GET_MY_LIST);
	}

	/** 通过实验室编号获取该实验室的管理员 */
	@Override
	public void getLabTeacher(Lab lab) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "getTeacherByLab_no");
		params.add("lab_no", String.valueOf(lab.getLab_no()));

		postRequest(params, Tag.GET_TEACHER_BY_NO);
	}

	/** 添加预约 */
	@Override
	public void addAppointment(Appointment appo) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "add");
		params.add("name", appo.getName());
		params.add("date", appo.getDate().toString());
		params.add("date_part", String.valueOf(appo.getDate_part()));
		params.add("lab_no", String.valueOf(appo.getLab_no()));
		params.add("number", String.valueOf(appo.getNumber()));

		postRequest(params, Tag.ADD_APPOINTMENT);
	}

	/** 删除预约 */
	@Override
	public void deleteAppointment(Appointment appo) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "delete");
		params.add("id", String.valueOf(appo.getId()));

		postRequest(params, Tag.REMOVE_APPOINTMENT);
	}

	/** 获取实验室列表 */
	@Override
	public void getAllLabs() {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "getLabList");

		postRequest(params, Tag.GET_LAB_LIST);
	}

	/** 通过实验室编号获取该实验的预约列表 */
	@Override
	public void getLabAppointments(Lab lab) {
		SimpleRequestParam params = new SimpleRequestParam();
		params.add("tag", "getAppoListByLab_no");
		params.add("lab_no", String.valueOf(lab.getLab_no()));

		postRequest(params, Tag.GET_LAB_APPOINTMENT_LIST);
	}

}
