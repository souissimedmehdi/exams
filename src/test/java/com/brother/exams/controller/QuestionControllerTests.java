package com.brother.exams.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.brother.exams.model.Option;
import com.brother.exams.model.Question;
import com.brother.exams.model.dto.QuestionDTO;
import com.brother.exams.service.QuestionService;
import com.brother.exams.utils.Constants;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
@WithMockUser
public class QuestionControllerTests {

	private static final int QUESTION_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	private ModelMapper mapper;

	@MockBean
	private QuestionService questionService;

	private Question question;
	private QuestionDTO questionDTO;

	@Before
	public void setup() {
		mapper = new ModelMapper();

		question = prepareQuestion();
		questionDTO = mapper.map(question, QuestionDTO.class);

		given(questionService.findQuestion(QUESTION_ID)).willReturn(question);
		given(questionService.mapToDTO(question)).willReturn(questionDTO);

		List<Question> response = Collections.singletonList(question);
		given(questionService.getAllQuestions()).willReturn(response);
		given(questionService.mapToDTO(response)).willReturn(Collections.singletonList(questionDTO));
	}

	@Test
	public void testGetAllQuestions() throws Exception {
		mockMvc.perform(get("/questions")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(QUESTION_ID)))
				.andExpect(jsonPath("$[0].content", is(Constants.JAVA_EST_UN_LANGAGE_COMPILE_QUESTION)))
				.andExpect(jsonPath("$[0].optionList", hasSize(2))).andExpect(jsonPath("$[0].multiAnswer", is(false)))
				.andExpect(jsonPath("$[0].correctOptionList", hasSize(1)))
				.andExpect(jsonPath("$[0].correctOptionList[0].value", is(Constants.FALSE)));
	}

	@Test
	public void testFindQuestions() throws Exception {
		mockMvc.perform(get("/questions/" + QUESTION_ID)).andExpect(status().isOk())
				.andExpect(jsonPath("id", is(QUESTION_ID)))
				.andExpect(jsonPath("content", is(Constants.JAVA_EST_UN_LANGAGE_COMPILE_QUESTION)))
				.andExpect(jsonPath("optionList", hasSize(2))).andExpect(jsonPath("multiAnswer", is(false)))
				.andExpect(jsonPath("correctOptionList", hasSize(1)))
				.andExpect(jsonPath("correctOptionList[0].value", is(Constants.FALSE)));
	}

	private Question prepareQuestion() {
		Option optionTrue = new Option(Constants.TRUE);
		Option optionFalse = new Option(Constants.FALSE);

		question = new Question();
		question.setId(QUESTION_ID);
		question.setContent(Constants.JAVA_EST_UN_LANGAGE_COMPILE_QUESTION);
		question.setMultiAnswer(false);
		question.setOptionList(Arrays.asList(optionFalse, optionTrue));
		question.setCorrectOptionList(Collections.singletonList(optionFalse));

		return question;
	}
}
