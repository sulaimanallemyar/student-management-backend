package com.example.student_management.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
	@SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 1)
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(length = 50)
	private String surname;
}
