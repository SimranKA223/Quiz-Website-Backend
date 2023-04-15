package com.example.Training.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Training.entity.exam.Category;
import com.example.Training.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
	public List<Quiz> findBycategory(Category category);
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category c, Boolean b);

}