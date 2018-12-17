package com.crm.common;
/***
 * 
 * @author jacy
 *
 */
public class MyResEntity{
    private String retcode;
    private Object body;
    private String errMsg;

    public MyResEntity() {
    	this.retcode = "200";
    }

    public MyResEntity(Object body) {
    	this.retcode = "200";
        this.body = body;
    }
    
    public MyResEntity(String retcode,Object body) {
        this.retcode=retcode;
        this.body = body;
    }

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
