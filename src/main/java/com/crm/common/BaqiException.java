package com.crm.common;
/***
 * 
 * @author jacy
 *
 */
public class BaqiException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int code;

	private String message;
	
	public BaqiException(int code) {
		this.code = code;
	}

	public BaqiException(String message) {
		this.code = 500;
		this.message=message;
	}
//	
	public BaqiException(int code,String message) {
		this.code = code;
		this.message=message;
	}

	public BaqiException(CustomExceptionEnum customEnum) {
		this.code = customEnum.getCode();
		this.message=customEnum.getMessage();
	}

	
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}

}
