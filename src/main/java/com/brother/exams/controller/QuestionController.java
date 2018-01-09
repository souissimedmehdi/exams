package com.brother.exams.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brother.exams.annotation.DTO;
import com.brother.exams.model.Question;
import com.brother.exams.model.dto.QuestionDTO;
import com.brother.exams.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping
	public List<QuestionDTO> getAllQuestions() {
		return questionService.mapToDTO(questionService.getAllQuestions());
	}
	
	@PostMapping
	public Question addQuestion(@Valid @DTO(QuestionDTO.class) Question question) {
		return questionService.saveQuestion(question);
	}

	@GetMapping("/{id}")
	public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable(value = "id") Integer id) {
		Question question = questionService.findQuestion(id);
		if(question == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(questionService.mapToDTO(question));		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable(value = "id") Integer questionId, @Valid @DTO(QuestionDTO.class) Question newQuestion ) {
		Question question = questionService.updateQuestion(questionId, newQuestion);
		if(question == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(questionService.mapToDTO(question));
	}

}
