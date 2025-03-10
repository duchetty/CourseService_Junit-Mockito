package com.cs.CourseService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.CourseService.dto.CourseDto;
import com.cs.CourseService.entity.Course;
import com.cs.CourseService.exception.NotFoundException;
import com.cs.CourseService.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public Course saveCourse(CourseDto courseDto) {
		Course course=Course.builder().name(courseDto.getName()).duration(courseDto.getDuration())
						.trainerName(courseDto.getTrainerName()).fee(courseDto.getFee()).build();
	
			courseRepo.save(course);
		return course;
	}

	@Override
	public Course getCourseById(Long id) {
		return courseRepo.findById(id).orElseThrow(()->new NotFoundException("Course does not exist"));
	}

	@Override
	public List<Course> getAllCourse() {
		return courseRepo.findAll();
	}

	@Override
	public Course updateCourseDetails(Long id, CourseDto courseDto) {
		Course cs=courseRepo.findById(id).get();
		cs.setDuration(courseDto.getDuration());
		cs.setFee(courseDto.getFee());
		cs.setTrainerName(courseDto.getTrainerName());
		return courseRepo.save(cs);
	}

}
