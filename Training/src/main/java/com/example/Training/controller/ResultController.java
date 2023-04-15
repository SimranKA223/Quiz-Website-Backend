package com.example.Training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Training.entity.User;
import com.example.Training.entity.exam.Category;
import com.example.Training.entity.exam.Quiz;
import com.example.Training.service.ResultService;

//@RestController
//@RequestMapping("/result")
//@CrossOrigin("*")

@RestController
@RequestMapping("/result")
@CrossOrigin("*")
public class ResultController {
	
	@Autowired
	private ResultService resultService;
	
	//get all
	@GetMapping("/")
	public ResponseEntity<?> getResult(){
		//return ResponseEntity.ok(this.resultService.getResult());
		return ResponseEntity.ok(this.resultService.getResult());
	}
	
	

}
