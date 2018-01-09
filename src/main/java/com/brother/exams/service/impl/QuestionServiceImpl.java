package com.brother.exams.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brother.exams.model.Question;
import com.brother.exams.model.dto.QuestionDTO;
import com.brother.exams.repository.QuestionRepository;
import com.brother.exams.service.QuestionService;

@Service
public class QuestionServiceImpl extends MapperServiceImpl<QuestionDTO> implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public List<Question> getAllQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public Question saveQuestion(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public Question findQuestion(Integer questionId) {
		return questionRepository.findOne(questionId);
	}

	@Override
	public Question updateQuestion(Integer questionId, Question newQuestion) {
		Question question = findQuestion(questionId);
		if(question != null) {
			question.updateFields(newQuestion);
			questionRepository.save(question);
		}
		return question;
	}
	
}
