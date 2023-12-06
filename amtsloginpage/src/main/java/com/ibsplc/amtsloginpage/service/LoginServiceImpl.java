package com.ibsplc.amtsloginpage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibsplc.amtsloginpage.bo.LoginUser;
import com.ibsplc.amtsloginpage.exceptions.NewUserInvalidException;
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
			user = logMapper.getByUserName(loginName);
		}
		catch (Exception ec) {
			throw new NoLoginNameException("UserName Not found Exception: ", ec.getCause());
		}
		return user;
	}

	@Override
	public boolean loadNewUser(LoginUser newUser) throws NewUserInvalidException {
		boolean res = false;
		try {
			logMapper.newUserRegist(newUser.getLogin_name(), newUser.getLogin_password(), newUser.getLogin_role(), newUser.getAccess_key());
			res = true;
		}
		catch (Exception ec) {
			res = false;
			throw new NewUserInvalidException("User name Store Exception: ", ec.getCause());
		}
		return res;
	}

}
