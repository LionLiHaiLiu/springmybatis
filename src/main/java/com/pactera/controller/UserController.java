package com.pactera.controller;

import java.util.List;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pactera.entity.User;
import com.pactera.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/user")
@Log
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping("/getUser")
	public User searchUserById(String id) {
		User user = userService.getUserById(id);
		log.info("User: " + user);
		return user;
	}
	
	@RequestMapping("/getAllUser")
	public List<User> searchAllUser() {
		List<User> list = userService.getAllUser();
		log.info("user list: " + list.toString());
		return list;
	}
	
	@RequestMapping("/getUserByName")
	public User searchUserByName(String name) {
		return userService.getUserByName(name);
	}

	@RequestMapping("/deleteUser")
	public int deleteUserById(String id) {
		return userService.DeleteUserById(id);
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public int addUser(User user) {
		return userService.addUser(user);
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public int updateUser(User user) {
		return userService.editUser(user);
	}
	

}
