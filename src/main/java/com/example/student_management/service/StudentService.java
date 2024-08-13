package com.example.student_management.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.student_management.domain.Student;
import com.example.student_management.service.dto.StudentDTO;

@Service
public interface StudentService {

	Student save(StudentDTO studentDto);
	
	Page<Student> findAll(Pageable page);
	
	void delete(Long id);
	
	Boolean notExistsById(Long id);

	Student findById(Long id);
}
