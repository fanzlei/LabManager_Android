package com.fanz.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户模型
 * 
 * @author fanz
 * @since 2014-01-13
 * 
 */
public class User {

	private int id = -1;
	private String name = null;
	private String pass = null;
	private String phone = null;
	private boolean passStatus;
	private String classes = null;

	public User() {
	}

	public User(JSONObject json) throws JSONException {
		name = json.getString("name");
		pass = json.getString("pass");
		phone = json.getString("phone");
		passStatus = json.getBoolean("pass_status");
		classes = json.getString("classes");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean getPassStatus() {
		return passStatus;
	}

	public void setPassStatus(boolean pass_status) {
		this.passStatus = pass_status;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

}
