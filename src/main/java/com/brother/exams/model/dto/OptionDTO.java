package com.brother.exams.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OptionDTO {
	private int id;
	private String value;
	
	public OptionDTO(String value) {
		this.value = value;
	}
	
}
