package com.ibsplc.amtsloginpage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibsplc.amtsloginpage.bo.LoginUser;
import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;
import com.ibsplc.amtsloginpage.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper logMapper;

	@Override
	public LoginUser loadUserByLoginName(String loginName) throws NoLoginNameException {
		LoginUser user = null;
		try {
			user =  logMapper.getByUserName(loginName);
		}
		catch (Exception ex) {
			throw new NoLoginNameException("Username not found in DB: ", ex.getCause());
		}
		return user;
	}

}
