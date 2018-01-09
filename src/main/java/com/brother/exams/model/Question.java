package com.brother.exams.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {

	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank
	private String content;

	@OneToMany
	private List<Option> optionList;

	@OneToMany
	private List<Option> correctOptionList;

	private Boolean multiAnswer;

	public void updateFields(Question question) {
		content = question.content;
		optionList = question.getOptionList();
		correctOptionList = question.getCorrectOptionList();
		multiAnswer = question.getMultiAnswer();
	}
}
