package com.brother.exams.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {

	private Integer id;
	
	private String content;
	
	private List<OptionDTO> optionList;

	private List<OptionDTO> correctOptionList;
	
	private boolean multiAnswer;
}
