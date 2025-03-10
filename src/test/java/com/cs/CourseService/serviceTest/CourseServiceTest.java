package com.cs.CourseService.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cs.CourseService.dto.CourseDto;
import com.cs.CourseService.entity.Course;
import com.cs.CourseService.repository.CourseRepository;
import com.cs.CourseService.service.CourseService;

@SpringBootTest
public class CourseServiceTest {
	
	@MockBean
	private CourseRepository repository;
	
	@Autowired
	private CourseService service;
	
	private CourseDto dto,updateCourseDto;
	private Course c1,c2,updateCourse;
	
	@BeforeEach
	public void setUp()
	{
		dto=new CourseDto("oracle","3 Months",3000,"mahesh");
		c1=new Course(101L,dto.getName(),dto.getDuration(),dto.getFee(),dto.getTrainerName());
		c2=new Course(102L,"Microservice","1 Months",8000,"sriman");
		updateCourseDto=new CourseDto("oracle","4 Months",3000,"giri");
		updateCourse=new Course(101L,updateCourseDto.getName(),updateCourseDto.getDuration(),updateCourseDto.getFee(),updateCourseDto.getTrainerName());
	}
	
	@Test
	public void testSaveCourse()
	{
		when(repository.save(any(Course.class))).thenReturn(c1);
		Course  course =service.saveCourse(dto);
		assertNotNull(course);
		assertEquals("mahesh",course.getTrainerName());
		verify(repository,times(1)).save(any(Course.class));
	}
	
	@Test
	public void testGetCourseById_Found()
	{
		when(repository.findById(101L)).thenReturn(Optional.of(c1));
		Course  course =service.getCourseById(101L);
		assertNotNull(course);
		assertEquals(3000,course.getFee());
		verify(repository,times(1)).findById(101L);
	}
	
	@Test
	public void testGetCourseById_NotFound()
	{
		when(repository.findById(105L)).thenReturn(Optional.empty());
		Exception exception=assertThrows(RuntimeException.class, ()->{ service.getCourseById(105L);});
		 
		assertEquals("Course does not exist",exception.getMessage());
		verify(repository,times(1)).findById(105L);
	}
	
	@Test
	public void testGetAllCourse()
	{
		when(repository.findAll()).thenReturn(Arrays.asList(c1,c2));
		List<Course>  courses=service.getAllCourse();
		assertNotNull(courses);
		verify(repository,times(1)).findAll();
	}
	
	@Test
	public void testUpdateCourseDetails_Found()
	{
		when(repository.findById(101L)).thenReturn(Optional.of(c1));
		when(repository.save(any(Course.class))).thenReturn(c1);
		Course updateCourse=service.updateCourseDetails(101L, updateCourseDto);
		
		assertNotNull(updateCourse);
		assertEquals("4 Months",updateCourse.getDuration());
		assertEquals("giri",updateCourse.getTrainerName());
		verify(repository,times(1)).findById(101l);
		verify(repository,times(1)).save(updateCourse);	
		
	}
	
	@Test
	public void testUpdateCourseDetails_NotFound()
	{
		when(repository.findById(105L)).thenReturn(Optional.empty());
	
		Exception exception=assertThrows(RuntimeException.class, ()->service.updateCourseDetails(105L, updateCourseDto));
		assertEquals("No value present",exception.getMessage());
		verify(repository,times(1)).findById(105L);
		verify(repository,never()).save(any(Course.class));
		
		
	}
	
	
}


