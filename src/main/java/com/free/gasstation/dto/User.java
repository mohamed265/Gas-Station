package com.free.gasstation.dto;

import com.free.gasstation.dto.base.BaseEntity;

/**
 * @author Mohamed265
 */
public class User extends BaseEntity {

	private int id;

	private String username;
	private String email;
	private String password;
	private String siteName;
	private String siteNumber;
	private String securityQuestion;
	private String securityAnswer;
	private String status;

	public User() {
	}

	public User(int id, String username, String email, String password, String siteName, String siteNumber,
			String securityQuestion, String securityAnswer, String status) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.siteName = siteName;
		this.siteNumber = siteNumber;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteNumber() {
		return siteNumber;
	}

	public void setSiteNumber(String siteNumber) {
		this.siteNumber = siteNumber;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
