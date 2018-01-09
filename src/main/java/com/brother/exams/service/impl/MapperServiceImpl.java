package com.brother.exams.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brother.exams.service.MapperService;

@Service
public class MapperServiceImpl<T> implements MapperService<T>{

	@Autowired
	private ModelMapper mapper;
	
	private Class<T> destinationType;

	@Override
	public T mapToDTO(Object source) {
		if(Objects.nonNull(source)) {
			return mapper.map(source, destinationType);			
		}
		return null;
	}

	@Override
	public List<T> mapToDTO(List<?> sourceList) {
		return sourceList.stream().map(this::mapToDTO).collect(Collectors.toList());
	}

}