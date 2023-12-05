package com.ibsplc.amtsloginpage.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "login_credentials")
public class LoginUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loginId;

	@Column(name= "login_name")
	private String loginName;
	@Column(name= "login_password")
	private String loginPassword;
	@Column(name= "access_key")
	private String accessKey;
	@Column(name= "login_role")
	private String loginRole;

	public LoginUser() { }

	public LoginUser(Long loginId, String loginName, String loginPassword, String accessKey, String loginRole) {
		super();
		this.loginId = loginId;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.accessKey = accessKey;
		this.loginRole = loginRole;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getLoginRole() {
		return loginRole;
	}

	public void setLoginRole(String loginRole) {
		this.loginRole = loginRole;
	}

	@Override
	public String toString() {
		return "LoginUser [loginId=" + loginId + ", loginName=" + loginName + ", loginPassword=" + loginPassword
				+ ", loginRole=" + loginRole + "]";
	}



}
