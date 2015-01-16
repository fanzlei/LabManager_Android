package com.fanz.util;

/**
 * Tag类
 * 
 * 用于区分不同的操作
 * 
 * @author fanz
 *
 */
public class Tag {
	public final static int LOGIN = 0x0001;
	public final static int REGISTER = 0x0002;
	public final static int UPDATE_USER = 0x0003;
	public final static int GET_MY_LIST = 0x0004;
	public final static int GET_TEACHER_BY_NO = 0x0005;
	public final static int GET_LAB_LIST = 0X0006;
	public final static int ADD_APPOINTMENT = 0x0007;
	public final static int REMOVE_APPOINTMENT = 0X0008;
	public final static int GET_LAB_APPOINTMENT_LIST = 0x0009;

}
