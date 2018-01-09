package com.brother.exams.service;

import java.util.List;

public interface MapperService<T> {

	T mapToDTO(Object source);

	List<T> mapToDTO(List<?> sourceList);
}
