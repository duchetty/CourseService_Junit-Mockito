package com.cs.CourseService.service;

import java.util.List;

import com.cs.CourseService.dto.CourseDto;
import com.cs.CourseService.entity.Course;

public interface CourseService {
	public Course saveCourse(CourseDto courseDto);
	public Course getCourseById(Long id);
	public List<Course> getAllCourse();
	public Course updateCourseDetails(Long id, CourseDto courseDto);
	
}
