package com.brother.exams.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "option")
@Getter
@Setter
@NoArgsConstructor
public class Option {

	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank
	private String value;

	public Option(String value) {
		this.value = value;
	}
}
