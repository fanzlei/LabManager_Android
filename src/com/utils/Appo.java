package com.utils;

import java.sql.Date;

import org.json.JSONException;
import org.json.JSONObject;


public class Appo {

	private int id;
	private Date date;
	private int date_part;
	private String name;
	private int number;
	private int lab_no;
	private int pass_status;
	
	public Appo(){}
	public Appo(JSONObject jo) throws JSONException{
		date=(Date)jo.get("date");
		date_part=jo.getInt("date_part");
		name=jo.getString("name");
		number=jo.getInt("number");
		lab_no=jo.getInt("lab_no");
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getDate_part() {
		return date_part;
	}
	public void setDate_part(int date_part) {
		this.date_part = date_part;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getLab_no() {
		return lab_no;
	}
	public void setLab_no(int lab_no) {
		this.lab_no = lab_no;
	}
	public int getPass_status() {
		return pass_status;
	}
	public void setPass_status(int pass_status) {
		this.pass_status = pass_status;
	}
	
}
