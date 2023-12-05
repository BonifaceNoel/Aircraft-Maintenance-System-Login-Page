package com.ibsplc.amtsloginpage.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.ibsplc.amtsloginpage.bo.LoginUser;
import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;
import com.ibsplc.amtsloginpage.repository.LoginRepository;

public class LoginServiceImpl implements LoginService{

	@Override
	public UserDetails loadUserByLoginName(String loginname) throws NoLoginNameException {
		LoginUser user = LoginRepository.findByLoginName(loginname);

		if(user == null) {
			throw new NoLoginNameException("Username not found: ", loginname);
		}
		return null;
	}

}
