package com.ibsplc.amtsloginpage.service;

import org.springframework.stereotype.Service;

import com.ibsplc.amtsloginpage.bo.LoginUser;
import com.ibsplc.amtsloginpage.exceptions.NewUserInvalidException;
import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;

@Service
public interface LoginService {

	public boolean loadUserByLoginName(String loginname, String password) throws NoLoginNameException;
	public boolean loadNewUser(LoginUser newUser) throws Exception;

}
