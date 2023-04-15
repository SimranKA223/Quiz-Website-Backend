package com.example.Training.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Training.entity.Result;
import com.example.Training.entity.User;
import com.example.Training.entity.exam.Question;
import com.example.Training.entity.exam.Quiz;
import com.example.Training.repo.ResultRepository;
import com.example.Training.repo.UserRepository;
import com.example.Training.service.QuestionService;
import com.example.Training.service.QuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class QuestionController {
	
	
	
	@Autowired
	private QuestionService service;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private ResultRepository resultRepo;
	
	@PostMapping("question/")
	public ResponseEntity<Question> add(@RequestBody Question question){
		return ResponseEntity.ok(this.service.addQuestion(question));
	}
	
	@PutMapping("question/")
	public ResponseEntity<Question> update(@RequestBody Question question){
		return ResponseEntity.ok(this.service.updateQuestion(question));
	}
	
	@GetMapping("question/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid){
		Quiz quiz =this.quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();
		List list=new ArrayList<>(questions);
		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())) {
			list=list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()+1));
		}
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("question/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid){
		Quiz quiz = new Quiz();
		quiz.setqId(qid);
		Set<Question> questionsOfQuiz=this.service.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questionsOfQuiz);
	}
	
	@GetMapping("question/{quesId}")
	public Question get(@PathVariable("quesId") Long quesId) {
		return this.service.getQuestion(quesId);
	}
	
	@DeleteMapping("question/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId) {
		this.service.deleteQuestion(quesId);
	}
	/*
	@GetMapping("question/res/quiz/{qid}")
	public ResponseEntity<Result> getResultfromQuiz(@PathVariable("qid") Long qid){
		Quiz quiz =this.quizService.getQuiz(qid);
		quiz.setqId(qid);
		Result result = new Result();
		result.setQuiz(quiz);
		return ResponseEntity.ok(result);
	}
	*/
	//quiz evaluation
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/eval-quiz/")
	public ResponseEntity<Result> evalQuiz( @RequestParam("questions") String questionsJson, @RequestParam("result") String resultJson) throws JsonMappingException, JsonProcessingException{
		
		System.out.println(questionsJson);
		System.out.println(resultJson);
		
		//JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		Gson gson = new Gson(); 
	 

		Question[] questions = gson.fromJson(questionsJson, Question[].class);  

		for(Question q : questions) {
			System.out.println(q);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		//List<Question> questions = objectMapper.readValue(questionsJson, new TypeReference<List<Question>>(){});
		Result result = objectMapper.readValue(resultJson, Result.class);
		System.out.println(result);
		  
		//return null;
		 
		
		int score=0;
		int correctAnswers=0;
		int num=0;
		int marks=0;
		int total=0;
		for(Question q:questions){
			
			Question question=this.service.get(q.getQuesId());
			if(question.getAnswer().equals(q.getGivenAnswer())) {
				
				correctAnswers++;
				
				//double marksSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
		        //score+=marksSingle;
				
				
			}
			System.out.println(correctAnswers);
			
			//if(q.getGivenAnswer()!=null) {
				//attempted++;
			//}
			
			//num++;
			
		}
		//total=num*2;
		//marks=correctAnswers*2;
		//score=(int)(marks/total)*100;
		
		
		result.setScore(correctAnswers);
		this.resultRepo.save(result);
		return new ResponseEntity<>(result, HttpStatus.OK);
		
        //result.setScore(correctAnswers);
        //return this.resultRepo.save(result);
        //Result savedResult = this.resultRepo.save(result);
        //return new ResponseEntity<>(savedResult, HttpStatus.OK);
	    
		
		/*
		Map<String,Object> map=Map.of("marksGot",score,"correctAnswers",correctAnswers,"attempted",attempted);
		
		return ResponseEntity.ok(map);
		*/
	}
	
	

}
