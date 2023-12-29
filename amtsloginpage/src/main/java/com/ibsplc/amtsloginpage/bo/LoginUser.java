package com.ibsplc.amtsloginpage.bo;

public class LoginUser {

	private Long login_id;

	private String login_name;
	private String login_password;
	private String access_key;
	private String login_role;

	public LoginUser() { }

	public LoginUser(Long login_id, String login_name, String login_password, String access_key, String login_role) {
		super();
		this.login_id = login_id;
		this.login_name = login_name;
		this.login_password = login_password;
		this.access_key = access_key;
		this.login_role = login_role;
	}

	public Long getLogin_id() {
		return login_id;
	}

	public void setLogin_id(Long login_id) {
		this.login_id = login_id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

	public String getAccess_key() {
		return access_key;
	}

	public void setAccess_key(String access_key) {
		this.access_key = access_key;
	}

	public String getLogin_role() {
		return login_role;
	}

	public void setLogin_role(String login_role) {
		this.login_role = login_role;
	}

	@Override
	public String toString() {
		return "LoginUser [login_id=" + login_id + ", login_name=" + login_name + ", login_password=" + login_password
				+ ", login_role=" + login_role + "]";
	}

}