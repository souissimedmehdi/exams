package com.brother.exams.service;

import java.util.List;

import com.brother.exams.model.Question;
import com.brother.exams.model.dto.QuestionDTO;

public interface QuestionService extends MapperService<QuestionDTO>{
	
	List<Question> getAllQuestions();

	Question saveQuestion(Question question);

	Question findQuestion(Integer questionId);

	Question updateQuestion(Integer questionId, Question newQuestion);
}
