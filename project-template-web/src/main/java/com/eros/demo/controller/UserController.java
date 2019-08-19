package com.eros.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eros.demo.common.constants.GenericConstants;
import com.eros.demo.common.support.JsonParam;
import com.eros.demo.common.support.JsonResult;
import com.eros.demo.entity.User;
import com.eros.demo.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping("")
	public JsonResult fetchUsers(@JsonParam(value = "condition", required = false) User condition,
	                             @JsonParam(value = "page", required = false, defaultValue = GenericConstants.DEFAULT_CURRENT_PAGE) Integer page,
	                             @JsonParam(value = "pageSize", required = false, defaultValue = GenericConstants.DEFAULT_PAGE_SIZE) Integer pageSize) {

		PageHelper.startPage(page, pageSize);
		Page<User> users = (Page<User>) userService.fetchUsers(condition);
		return retJson(users);
	}
}
