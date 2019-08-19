package com.eros.demo.mapper;

import java.util.List;

import com.eros.demo.entity.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

	List<User> fetchUsers(User condition);
}
