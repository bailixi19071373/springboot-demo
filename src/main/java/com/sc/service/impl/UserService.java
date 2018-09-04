package com.sc.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sc.common.Code;
import com.sc.mapper.UserMapper;
import com.sc.pojo.User;
import com.sc.service.UserServiceIn;
/**
 * 校验登录
 * @author Administrator
 *
 */
@Service
public class UserService implements UserServiceIn{

	@Inject
	private UserMapper userMapper;
	
	public String checkLogin(User user) {
		int count = userMapper.selectCountByUsername(user.getUsername());
		if(count == 0)
		return Code.ERROR_USERNAME_NOTEXIST;
		count = userMapper.selectCountByUsernameAndPassword(user);
		if(count == 0)
			return Code.ERROR_PASSWORD_WRONG;
		return Code.SUCCESS;
	}
	
}
