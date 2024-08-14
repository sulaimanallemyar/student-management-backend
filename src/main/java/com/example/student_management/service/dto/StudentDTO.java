package com.example.student_management.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDTO {

	private Long id;

	@NotBlank(message = "Name cannot be empty")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Name can only contain alphabetic characters and spaces")
	@Size(min = 3, max = 50, message = "The length of name must be between 3 and 50 characters.")
	private String name;

	@Size(min = 3, max = 50, message = "The length of surname must be between 3 and 50 characters.")
	@NotBlank(message = "Surname cannot be empty")
	private String surname;
}
