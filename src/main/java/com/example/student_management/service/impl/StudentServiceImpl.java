package com.example.student_management.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.student_management.domain.Student;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.service.StudentService;
import com.example.student_management.service.dto.StudentDTO;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	
	private final ModelMapper modelMapper;
	
	public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper){
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;	
	}
	
	@Override
	public Student save(StudentDTO studentDto) {
		
		Student student = this.modelMapper.map(studentDto, Student.class);
		
		studentRepository.save(student);
		
		return student;
	}

	@Override
	public Page<Student> findAll(Pageable page) {

		return studentRepository.findAll(page);
	}

	@Override
	public void delete(Long id) {
	
		studentRepository.deleteById(id);
	}

	@Override
	public Boolean notExistsById(Long id) {
		
		if (studentRepository.existsById(id)) {
			return false;
		}

		return true;
	}

	@Override
	public Student findById(Long id) {
		
		return studentRepository.getReferenceById(id);
	}
}
