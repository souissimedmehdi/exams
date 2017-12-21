package com.brother.exams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brother.exams.model.dto.QuestionDTO;
import com.brother.exams.service.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/questions")
	public List<QuestionDTO> getAllQuestions() {
		return questionService.getAllQuestions();
	}

}
