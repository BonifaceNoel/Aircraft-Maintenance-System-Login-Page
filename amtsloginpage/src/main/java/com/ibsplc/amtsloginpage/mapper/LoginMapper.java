package com.ibsplc.amtsloginpage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ibsplc.amtsloginpage.bo.LoginUser;

@Mapper
public interface LoginMapper{

	@Select("SELECT * FROM `aircraft_maintenance_system`.`login_credentials` WHERE login_name = #{name}")
	LoginUser getByUserName(@Param("name")String loginName);
}
