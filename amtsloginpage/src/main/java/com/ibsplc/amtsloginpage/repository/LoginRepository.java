package com.ibsplc.amtsloginpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibsplc.amtsloginpage.bo.LoginUser;

public interface LoginRepository extends JpaRepository<LoginUser, Long>{

	public LoginUser findByLoginName(String loginName);
}
