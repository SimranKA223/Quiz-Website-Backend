package com.example.Training.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Training.entity.exam.Question;
import com.example.Training.entity.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question,Long>{

	Set<Question> findByQuiz(Quiz quiz);

}
