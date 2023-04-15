package com.example.Training.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Training.entity.Result;
import com.example.Training.entity.User;
import com.example.Training.entity.exam.Category;
import com.example.Training.repo.ResultRepository;
import com.example.Training.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private ResultRepository resultRepo;

	@Override
	public Set<Result> getResult() {
		// TODO Auto-generated method stub
		//return new LinkedHashSet<>(this.resultRepo.findAll());
		
		return new LinkedHashSet<>(this.resultRepo.findAll());
	}

	
	
	

}
