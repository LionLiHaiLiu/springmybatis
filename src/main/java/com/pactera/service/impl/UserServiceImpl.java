package com.pactera.service.impl;

import java.util.List;

import com.pactera.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.entity.User;
import com.pactera.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper  userMapper;
	
	@Override
	public User getUserById(String id) {
		User user = userMapper.selectById(id);
		return user;
	}

	@Override
	public int DeleteUserById(String id) {
		return userMapper.delectById(id);
	}

	@Override
	public List<User> getAllUser() {
		List<User> list = userMapper.selectAll();
		return list;
	}

	@Override
	public User getUserByName(String name) {
		User user = userMapper.selectByName(name);
		return user;
	}

	@Override
	public int addUser(User user) {
		return userMapper.insertUser(user);
	}

	@Override
	public int editUser(User user) {
		return userMapper.updateUser(user);
	}

}
