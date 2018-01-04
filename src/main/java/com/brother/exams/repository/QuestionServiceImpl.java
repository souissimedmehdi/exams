package com.brother.exams.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brother.exams.model.dto.QuestionDTO;
import com.brother.exams.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<QuestionDTO> getAllQuestions() {
		return questionRepository.findAll().stream().map(question -> convert(question, QuestionDTO.class)).collect(Collectors.toList());
	}
	
	private <S, T> T convert (S source, Class<T> target) {
		return mapper.map(source, target); 
	}

}
