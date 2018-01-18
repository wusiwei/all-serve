package com.wusw.model;

public class ResultResponseDto<E> {

	public final static String CODE_PARAMS_ERROR = "100";
	public final static String CODE_SUCCESS = "200";
	public final static String CODE_ERROR = "500";

	private String code;
	
	private E data;
	
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
