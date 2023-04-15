package com.example.Training.entity;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.Training.entity.exam.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="result")
public class Result {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long result_id;
	private int Score;
	
	@Column(name = "date_time", nullable = false)
	private String dateTime;
	
	

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Quiz quiz;

	public Long getResult_id() {
		return result_id;
	}

	public void setResult_id(Long result_id) {
		this.result_id = result_id;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@Override
	public String toString() {
		return "Result [result_id=" + result_id + ", Score=" + Score + ", user=" + user + ", quiz=" + quiz + "]";
	}

	
	

}
