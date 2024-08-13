package com.example.student_management.service.dto;

import com.example.student_management.domain.enumeration.Gender;

import lombok.Data;

@Data
public class StudentDTO {

	private Long id;

	private String name;

	private String surname;

	private Integer age;
	
    private Gender gender;
}
