package com.ibsplc.amtsloginpage.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibsplc.amtsloginpage.bo.LoginUser;
import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;
import com.ibsplc.amtsloginpage.mapper.LoginMapper;
import com.ibsplc.amtsloginpage.security.JWTKeyGenerator;

import io.jsonwebtoken.Jwts;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper logMapper;

	@Autowired
	JWTKeyGenerator jwtGenerator;

	private static final Logger logger = LoggerFactory.getLogger(JWTKeyGenerator.class);

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
	public boolean loadNewUser(LoginUser newUser) throws Exception {
		boolean res = false;
		String accKey = jwtGenerator.generateRS256Token(newUser.getLogin_name(), newUser.getLogin_password(), newUser.getLogin_role());
		System.out.println(accKey);
		jwtGenerator.decodeRS256Token(accKey, null);
		newUser.setAccess_key(accKey);
		try {
			//logMapper.newUserRegist(newUser.getLogin_name(), newUser.getLogin_password(), newUser.getLogin_role(), newUser.getAccess_key());
			res = true;
		}
		catch (Exception ec) {
			res = false;
			logger.error("User name Store Exception: " + "" + ec.getCause());
		}
		return res;
	}
}
