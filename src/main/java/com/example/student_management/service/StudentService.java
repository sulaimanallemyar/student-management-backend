package com.example.student_management.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.student_management.service.dto.StudentDTO;

@Service
public interface StudentService {

	StudentDTO save(StudentDTO studentDto);

	Page<StudentDTO> findAll(Pageable page);

	void delete(Long id);

	Boolean notExistsById(Long id);

	StudentDTO findById(Long id);
}
