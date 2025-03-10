package com.cs.CourseService.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cs.CourseService.controller.CourseController;
import com.cs.CourseService.dto.CourseDto;
import com.cs.CourseService.entity.Course;
import com.cs.CourseService.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

@WebMvcTest(value=CourseController.class)
public class CourseControllerTest {
	@MockBean
	private CourseService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	private CourseDto dto;
	private Course c1,c2,updateCourse;
	
	@BeforeEach
	public void setUp()
	{
		dto=new CourseDto("oracle","3 Months",3000,"mahesh");
		c1=new Course(101L,dto.getName(),dto.getDuration(),dto.getFee(),dto.getTrainerName());
		c2=new Course(102L,"Microservice","1 Months",3000,"sriman");
		updateCourse=new Course(101L,"oracle","4 Months",3000,"mahesh");
	}
	
	@Test
	public void testSave() throws Exception
	{
		when(service.saveCourse(any(CourseDto.class))).thenReturn(c1);
		
		 String jsonCustomer=mapper.writeValueAsString(c1);
		 
		 MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.post("/course/insert").contentType(MediaType.APPLICATION_JSON).content(jsonCustomer);
		 
		ResultActions perform= mockMvc.perform(requestBuilder);
		MvcResult result=perform.andReturn();
		HttpServletResponse response=result.getResponse();
		int status=response.getStatus();
		assertEquals(101,c1.getCourseId());
		assertEquals(201,status);
	
	}
	
	@Test
	public void testGetCourse() throws Exception
	{
		when(service.getCourseById(102L)).thenReturn(c2);
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get("/course/load?id=101");
		int status=mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus();
		assertEquals("Microservice",c2.getName());
		assertEquals(200,status);
	}
	
	@Test
	public void testGetAllCourses() throws Exception
	{
		when(service.getAllCourse()).thenReturn(Arrays.asList(c1,c2));
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get("/course/loadAll");
		int status=mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus();
		assertEquals("Microservice",c2.getName());
		assertEquals("oracle",c1.getName());
		assertEquals(200,status);
	}
	@Test
	public void testUpdateCourse() throws Exception
	{
		when(service.updateCourseDetails(eq(101L), any(CourseDto.class))).thenReturn(updateCourse);
		String jsonUpdatedCustomer=mapper.writeValueAsString(updateCourse);
		 
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.put("/course/modify/{id}",101).contentType(MediaType.APPLICATION_ATOM_XML.APPLICATION_JSON).content(jsonUpdatedCustomer);
		int status=mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus();
		assertEquals("4 Months",updateCourse.getDuration());
		assertEquals(201,status);
	
	}
}
