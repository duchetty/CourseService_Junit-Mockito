package com.cs.CourseService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.CourseService.dto.CourseDto;
import com.cs.CourseService.entity.Course;
import com.cs.CourseService.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping("/insert")
	public ResponseEntity<Course> save(@RequestBody CourseDto courseDto)
	{
		Course course=courseService.saveCourse(courseDto);
		return new ResponseEntity<>(course, HttpStatus.CREATED);
	}
	
	@GetMapping("/load")
	public ResponseEntity<Course> getCourse(@RequestParam("id") Long id)
	{
		Course course=courseService.getCourseById(id);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@GetMapping("/loadAll")
	public List<Course> getAllCourses()
	{
		return courseService.getAllCourse();
		
	}
	
	@PutMapping("/modify/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto)
	{
		Course course=courseService.updateCourseDetails(id, courseDto);
		return new ResponseEntity<>(course, HttpStatus.CREATED);
	}
	
	
}
