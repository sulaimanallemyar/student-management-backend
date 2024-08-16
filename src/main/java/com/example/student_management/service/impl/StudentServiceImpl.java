package com.example.student_management.service.impl;

import java.util.stream.Collectors;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.student_management.domain.Student;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.service.StudentService;
import com.example.student_management.service.dto.StudentDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	private final ModelMapper modelMapper;

	@Override
	public StudentDTO save(StudentDTO studentDto) {

		Student student = this.modelMapper.map(studentDto, Student.class);

		studentRepository.save(student);

		StudentDTO result = this.modelMapper.map(student, StudentDTO.class);

		return result;
	}

	@Override
	public Page<StudentDTO> findAll(Pageable pageable) {

		Page<Student> page = studentRepository.findAll(pageable);
		List<StudentDTO> list = page.stream().map(obj -> modelMapper.map(obj, StudentDTO.class))
				.collect(Collectors.toList());
		Page<StudentDTO> pageDto = new PageImpl<>(list, page.getPageable(), page.getTotalElements());

		return pageDto;
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
	public StudentDTO findById(Long id) {

		Student student = studentRepository.getReferenceById(id);

		StudentDTO result = this.modelMapper.map(student, StudentDTO.class);

		return result;
	}
}
