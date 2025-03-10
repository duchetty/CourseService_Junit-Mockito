package com.cs.CourseService.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestExceptionHandler {
	
	public ResponseEntity<ApiError> handleNoCourseException()
	{
		ApiError error=ApiError.builder().code(400).description("Course does Not exists").errorDate(new Date()).build();
	
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
