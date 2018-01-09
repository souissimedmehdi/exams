package com.brother.exams.annotation;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.brother.exams.exception.TechnicalException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DTOModelMapper extends RequestResponseBodyMethodProcessor {

	private static final ModelMapper modelMapper = new ModelMapper();

	private EntityManager entityManager;

	public DTOModelMapper(ObjectMapper objectMapper, EntityManager entityManager) {
		super(Collections.singletonList(new MappingJackson2HttpMessageConverter(objectMapper)));
		this.entityManager = entityManager;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(DTO.class);
	}

	@Override
	protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
		binder.validate();
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Object dto = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
		Object id = getEntityId(dto);
		if (id == null) {
			return modelMapper.map(dto, parameter.getParameterType());
		} else {
			Object persistedObject = entityManager.find(parameter.getParameterType(), id);
			modelMapper.map(dto, persistedObject);
			return persistedObject;
		}
	}

	@Override
	protected Object readWithMessageConverters(HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType) throws IOException, HttpMediaTypeNotSupportedException {
		DTO dto = Stream.of(parameter.getParameterAnnotations())
				.map(ann -> AnnotationUtils.getAnnotation(ann, DTO.class)).filter(Objects::nonNull).findFirst()
				.orElseThrow(RuntimeException::new);
		return super.readWithMessageConverters(inputMessage, parameter, dto.value());
	}

	private Object getEntityId(@NotNull Object dto) throws TechnicalException {
		Field field = Stream.of(dto.getClass().getDeclaredFields())
				.filter(f -> Objects.nonNull(f.getAnnotation(Id.class))).findFirst().orElse(null);
		if (field != null) {
			try {
				field.setAccessible(true);
				return field.get(dto);
			} catch (IllegalAccessException e) {
				throw new TechnicalException(e);
			}
		}
		return null;
	}
}
