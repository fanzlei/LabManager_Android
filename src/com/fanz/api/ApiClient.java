package com.fanz.api;

import com.fanz.model.Appointment;
import com.fanz.model.Lab;
import com.fanz.model.User;

/**
 * ApiClient接口类 定义了一些网络访问的方法
 * 
 * @author fanz
 * @version 1.0 2015.01.15
 */
public interface ApiClient {
	/** 用户登录 */
	void login(User user);

	/** 用户注册 */
	void register(User user);

	/** 更改用户信息 */
	void updateUser(User user);

	/** 获取用户预约列表 */
	void getUserAppointments(User user);

	/** 获取实验室的管理教师信息 */
	void getLabTeacher(Lab lab);

	/** 预约实验室 */
	void addAppointment(Appointment appointment);
	
	/** 取消某个预约 */
	void deleteAppointment(Appointment appointment);

	/** 获取实验室列表 */
	void getAllLabs();

	/** 获取某个实验室的预约列表 */
	void getLabAppointments(Lab lab);
}
