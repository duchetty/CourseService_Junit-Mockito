package com.cs.CourseService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.CourseService.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{

}
