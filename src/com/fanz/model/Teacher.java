package com.fanz.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 教师信息
 * 
 * @author Fanz
 * @version 1.0 2015.01.15
 * */
public class Teacher {

	private int id = -1;
	private String name = null;
	private String pass = null;
	private int lab_no = -1;
	private String phone = null;
	private boolean pass_status;

	public Teacher() {

	}

	public Teacher(JSONObject jo) throws JSONException {
		name = jo.getString("name");
		pass = jo.getString("pass");
		lab_no = jo.getInt("lab_no");
		phone = jo.getString("phone");
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

	public int getLab_no() {
		return lab_no;
	}

	public void setLab_no(int lab_no) {
		this.lab_no = lab_no;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean getPass_status() {
		return pass_status;
	}

	public void setPass_status(boolean pass_status) {
		this.pass_status = pass_status;
	}

}
