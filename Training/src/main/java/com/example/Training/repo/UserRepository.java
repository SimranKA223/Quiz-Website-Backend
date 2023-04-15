package com.example.Training.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Training.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
