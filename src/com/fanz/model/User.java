package com.fanz.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户信息
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class User {

	private int id = -1;
	private String name = null;
	private String pass = null;
	private String phone = null;
	private boolean passStatus;
	private String classes = null;

	public User() {
	}

	public User(JSONObject jo) throws JSONException {
		name = jo.getString("name");
		pass = jo.getString("pass");
		phone = jo.getString("phone");
		passStatus = jo.getBoolean("pass_status");
		classes = jo.getString("classes");
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
