package com.example.Training.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Training.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
