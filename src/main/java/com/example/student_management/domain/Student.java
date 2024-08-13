package com.example.student_management.domain;

import com.example.student_management.domain.enumeration.Gender;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
	@SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq")
	private Long id;

	private String name;

	private String surname;

	private Integer age;
	
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
