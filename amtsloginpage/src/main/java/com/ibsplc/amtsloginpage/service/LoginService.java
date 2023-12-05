package com.ibsplc.amtsloginpage.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;

@Service
public interface LoginService {

	public UserDetails loadUserByLoginName(String loginname) throws NoLoginNameException;
}
