package com.example.Training.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.Training.entity.User;
import com.example.Training.entity.UserRole;

@Service
public interface UserService {
	
	//create user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;

	//get user by usernname
	public User getUser(String username);
	
	//delete user by id
	public void deleteUser(Long userId);
	
}
