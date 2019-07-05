package com.pactera.mapper;

import java.util.List;

import com.pactera.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	User selectById(String id);
	
	User selectByName(String name);
	
	int delectById(String id);
	
	List<User> selectAll();
	
	int insertUser(User user);
	
	int updateUser(User user);
	
}
