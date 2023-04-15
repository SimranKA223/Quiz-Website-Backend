package com.example.Training;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Training.entity.Role;
import com.example.Training.entity.User;
import com.example.Training.entity.UserRole;
import com.example.Training.service.UserService;

@SpringBootApplication
public class TrainingApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting...");
		/*
		User user = new User();
		
		user.setFirstName("Simran");
		user.setLastName("Attri");
		user.setUsername("sim223");
		user.setPassword(this.bCryptPasswordEncoder.encode("111"));
		user.setEmail("sim223@gmail.com");
		user.setProfile("default.png");
		
		Role role1=new Role();
		role1.setRoleId(44L);
		role1.setRoleName("ADMIN");
		
		Set<UserRole> userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);
		
		userRoleSet.add(userRole);
		
		User user1 = this.userService.createUser(user, userRoleSet);
		System.out.println(user1.getUsername());
		*/
		
	}

}
