package com.wht.zuul.bean;

import java.io.Serializable;

public class ResultData<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 业务状态 正常
	 */
	public static final int STATUS_NORMAL = 1;
	/**
	 * 业务状态 异常
	 */
	public static final int STATUS_ERROR = 0;
	/**
	 * 业务状态 默认正常
	 */
	private Integer status = STATUS_NORMAL;
	/**
	 * 错误代码
	 */
	private ErrorCode errorCode;

	private String message;
	private T data;

	/**
	 * 默认构造函数
	 */
	public ResultData() {

	}

	/**
	 * 带参构造函数
	 * @param status 状态
	 * @param message 信息
	 * @param errorCode 错误码
	 */
	public ResultData(final Integer status, final  String message, final ErrorCode errorCode) {
		super();
		this.status = status;
		this.message = message;
		this.errorCode = errorCode;
	}
	/**
	 * 带参构造函数(默认错误)
	 * @param message 信息
	 * @param errorCode 错误码
	 */
	public ResultData(final  String message, final ErrorCode errorCode) {
		super();
		this.status = ResultData.STATUS_ERROR;
		this.message = message;
		this.errorCode = errorCode;
	}
	/**
	 * 带参构造函数
	 * @param status 状态
	 * @param message 信息
	 * @param data 数据
	 * @param errorCode 错误码
	 */
	@Deprecated
	public ResultData(final Integer status, final String message, final T data, final ErrorCode errorCode) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.errorCode = errorCode;
	}
	/**
	 * 带参构造函数(默认正确)
	 * @param data 数据
	 */
	public ResultData(final T data) {
		super();
		this.data = data;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(final Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(final T data) {
		this.data = data;
	}
	@Deprecated
	public ErrorCode getErroCode() {
		return errorCode;
	}
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	@Deprecated
	public void setErroCode(final ErrorCode erroCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "ResultData [status=" + status + ", message=" + message
				+ ", data=" + data + "]";
	}
}
