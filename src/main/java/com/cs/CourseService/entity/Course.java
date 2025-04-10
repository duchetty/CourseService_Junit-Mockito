package com.cs.CourseService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long courseId;
	private String name;
	private String duration;
	private double fee;
	private String trainerName;
}
