package com.ibsplc.amtsloginpage.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;

public interface LoginService {

	public UserDetails loadUserByLoginName(String loginname) throws NoLoginNameException;
}
