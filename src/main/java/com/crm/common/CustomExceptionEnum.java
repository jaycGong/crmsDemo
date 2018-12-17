package com.crm.common;
/***
 * 
 * @author jacy
 *
 */
public enum CustomExceptionEnum implements ExceptionEnums{
	
	UNKOW_ERROR(500, "未知异常，请联系管理员"),
	LOGIN_ERROR(501, "登录失败"),
	RSA_DECODE_ERROR(502, "RSA操作失败"),
	INPUT_ERROR(503, "无效字符"),
	
	;
	
	public int code;
	public String message;
	
	private CustomExceptionEnum(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	CustomExceptionEnum(String message){
		this.code = 500;
		this.message = message;
	}
 
	
	@Override
	public int getCode() {
		return code;
	}
 
	@Override
	public String getMessage() {
		return message;
	}
	
	public static String getMessage(int code) {
		for (CustomExceptionEnum c : CustomExceptionEnum.values()) {  
            if (c.getCode() == code) {  
                return c.message;  
            }  
        }  
		return null;
	}
	 

}
