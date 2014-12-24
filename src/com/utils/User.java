package com.utils;

import org.json.JSONException;
import org.json.JSONObject;



public class User {

	private int id =-1;
	private String name=null;
	private String pass =null;
	private String phone =null; 
	private boolean pass_status;
	private String classes=null;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(JSONObject jo) throws JSONException{
		name=jo.getString("name");
		pass=jo.getString("pass");
		phone=jo.getString("phone");
		classes=jo.getString("pass_status");
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
	
	public boolean getPass_status() {
		return pass_status;
	}
	public void setPass_status(boolean pass_status) {
		this.pass_status = pass_status;
	}
	
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}

}
