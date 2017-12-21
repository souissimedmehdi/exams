package com.brother.exams.service;

import java.util.List;

import com.brother.exams.model.dto.QuestionDTO;

public interface QuestionService {
	List<QuestionDTO> getAllQuestions();
}
