package com.example.Training.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Training.entity.User;
import com.example.Training.entity.UserRole;
import com.example.Training.repo.RoleRepository;
import com.example.Training.repo.UserRepository;
import com.example.Training.service.UserService;
import java.lang.Exception;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User local=this.userRepository.findByUsername(user.getUsername());
		if(local!=null) {
			System.out.println("User already exists");
			throw new Exception("User already exists");
		}else {
			for(UserRole ur: userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local=this.userRepository.save(user);
		}
		
		return local;
	}

	@Override
	public User getUser(String username) {
		
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		
		this.userRepository.deleteById(userId);
		
	}

}
