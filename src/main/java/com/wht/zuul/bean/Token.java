package com.wht.zuul.bean;

import java.io.Serializable;

public class Token implements Serializable{

	/**
	 * <p>TODO</br>
	 * @author liyd
	 * @version 2017年11月8日
	 */
	private static final long serialVersionUID = 1L;

	private String accessToken;
	private String refreshToken;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
	
}
