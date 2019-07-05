package com.pactera.service;

import java.util.List;

import com.pactera.entity.User;

public interface UserService {
	
	public User getUserById(String id);
	
	public User getUserByName(String name);
	
	public int DeleteUserById(String id);
	
	public List<User> getAllUser();
	
	public int addUser(User user);
	
	public int editUser(User user);

}
