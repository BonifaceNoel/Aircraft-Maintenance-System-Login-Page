package com.ibsplc.amtsloginpage.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ibsplc.amtsloginpage.bo.LoginUser;
import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;
import com.ibsplc.amtsloginpage.mapper.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public UserDetails loadUserByLoginName(String loginname) throws NoLoginNameException {
		LoginUser user =  null;

		if (user == null) {
			throw new NoLoginNameException("Username not found: ", loginname);
		}
		return new User(user.getLoginName(), user.getLoginPassword(), new ArrayList<>());
	}

}
