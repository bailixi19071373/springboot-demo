package com.sc.mapper;

import org.apache.ibatis.annotations.Select;

import com.sc.pojo.User;

public interface UserMapper {

	@Select("select count(1) from userinfo where username = #{username}")
	int selectCountByUsername(String username);
	
	@Select("select count(1) from userinfo where username = #{username} "
			+ "and password = #{password}")
	int selectCountByUsernameAndPassword(User user); 
	
	
}
