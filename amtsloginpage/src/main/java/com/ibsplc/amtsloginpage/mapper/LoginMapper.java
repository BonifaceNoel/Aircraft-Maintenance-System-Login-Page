package com.ibsplc.amtsloginpage.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ibsplc.amtsloginpage.bo.LoginUser;

@Mapper
public interface LoginMapper{

	@Select("SELECT * FROM `aircraft_maintenance_system`.`login_credentials` WHERE login_name = #{name};")
	LoginUser getByUserName(@Param("name") String loginName);

	@Insert("INSERT INTO `aircraft_maintenance_system`.`login_credentials` \r\n"
			+ "  (`login_name`, `login_role`, `login_password`, `access_key`) \r\n"
			+ "VALUES \r\n"
			+ "  (#{logname}, #{logrole}, #{logpass}, #{acckey})")
	void newUserRegist(@Param("logname") String logName, @Param("logpass") String logPass, @Param("logrole") String logRole, @Param("acckey") String accKey);
}
