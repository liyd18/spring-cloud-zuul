package com.wht.zuul.bean;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * <p>TODO</br>
	 * @author liyd
	 * @version 2017年11月9日
	 */
	private static final long serialVersionUID = 1L;
	private String mobile;
	private String role;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User() {
		super();
	}
	public User(String mobile, String role) {
		super();
		this.mobile = mobile;
		this.role = role;
	}
	
	
	
}
