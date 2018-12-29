package com.sampleReport.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	private int user_id;
	private String userName;
	private String email;
	public int getId() {
		return user_id;
	}
	public void setId(int user_id) {
		this.user_id = user_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return email;
	}
	public void setEmailId(String email) {
		this.email = email;
	}
	
	
}
