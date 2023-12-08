package com.ibsplc.amtsloginpage.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibsplc.amtsloginpage.bo.LoginUser;
import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;
import com.ibsplc.amtsloginpage.mapper.LoginMapper;
import com.ibsplc.amtsloginpage.security.JWTKeyGenerator;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper logMapper;

	@Autowired
	JWTKeyGenerator jwtGenerator;

	private static final Logger logger = LoggerFactory.getLogger(JWTKeyGenerator.class);

	@Override
	public boolean loadUserByLoginName(String loginName, String password) throws NoLoginNameException {
		LoginUser user = null;

		try {
			String genKey = logMapper.getByAccessKey(loginName);
			Map<String, Object> decodedMap = jwtGenerator.decodeRS256Token(genKey);

			user = logMapper.getByUserName(loginName);
		}
		catch (Exception ec) {
			throw new NoLoginNameException("UserName Not found Exception: ", ec.getCause());
		}
		return false;
	}

	@Override
	public boolean loadNewUser(LoginUser newUser) throws Exception {
		boolean res = false;
		String accKey = jwtGenerator.generateRS256Token(newUser.getLogin_name(), newUser.getLogin_password(), newUser.getLogin_role());
		newUser.setAccess_key(accKey);
		try {
			logMapper.newUserRegist(newUser.getLogin_name(), newUser.getLogin_role(), newUser.getAccess_key());
			res = true;
		}
		catch (Exception ec) {
			res = false;
			logger.error("User name Store Exception: " + "" + ec.getCause());
		}
		return res;
	}
}
