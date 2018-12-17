package com.crm.demo.model;
/***
 * 
 * @author jacy
 *
 */
public class UserModel {
    private Integer id;
    private String name;
    private String pwd;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
