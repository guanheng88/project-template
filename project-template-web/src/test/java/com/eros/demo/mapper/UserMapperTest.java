package com.eros.demo.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eros.demo.BaseTest;
import com.eros.demo.entity.User;

public class UserMapperTest extends BaseTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void insertUser() {
		User user = new User();
		user.setName("mary")
		    .setAge(25);

		userMapper.insert(user);
		System.out.println(user.getId());
	}

}
