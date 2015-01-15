package com.fanz.api;

import com.fanz.model.Appointment;
import com.fanz.model.Lab;
import com.fanz.model.User;

/**
 * ApiClient接口类
 * 
 * @author fanz
 *
 */
public interface ApiClient {
	void login(User user);

	void register(User user);

	void updateUser(User user);

	void getMyList(User user);

	void getTeacherByLab_no(Lab lab);

	void addAppo(Appointment appo);

	void deleteAppo(Appointment appo);

	void getLabList();

	void getAppoListByLab_no(Lab lab);
}
