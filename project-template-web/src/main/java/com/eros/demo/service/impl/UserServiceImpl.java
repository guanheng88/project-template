package com.eros.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eros.demo.entity.User;
import com.eros.demo.mapper.UserMapper;
import com.eros.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> fetchUsers(User condition) {
		return userMapper.fetchUsers(condition);
	}

}
