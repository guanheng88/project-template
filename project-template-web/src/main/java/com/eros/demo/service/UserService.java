package com.eros.demo.service;

import java.util.List;

import com.eros.demo.entity.User;

public interface UserService {

	List<User> fetchUsers(User condition);
}
