package com.example.Training.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Training.entity.exam.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

}
