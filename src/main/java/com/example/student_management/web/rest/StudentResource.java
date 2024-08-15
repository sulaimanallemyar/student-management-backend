package com.example.student_management.web.rest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_management.service.StudentService;
import com.example.student_management.service.dto.StudentDTO;
import com.example.student_management.web.rest.errors.BadRequestAlertException;
import com.example.student_management.web.rest.errors.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for managing
 * {@link com.example.student_management.domain.Student}.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class StudentResource {

	private final StudentService studentService;

	public StudentResource(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/students")
	public ResponseEntity<List<StudentDTO>> getStudents(Pageable pageable) {
		log.info("REST request to get students");

		Page<StudentDTO> page = studentService.findAll(pageable);
		List<StudentDTO> result = page.stream().collect(Collectors.toList());
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));

		return ResponseEntity.ok().headers(headers).body(result);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
		log.info("REST request to get student by id: {}", id);

		if (studentService.notExistsById(id)) {
			throw new ResourceNotFoundException("student", "id", id);
		}

		StudentDTO result = studentService.findById(id);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("/students")
	public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDto) {
		log.info("REST request to save student: {}", studentDto);

		if (studentDto.getId() != null) {
			throw new BadRequestAlertException("A new student cannot already have an ID");
		}

		StudentDTO result = studentService.save(studentDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable(value = "id", required = false) final Long id,
			@Valid @RequestBody StudentDTO studentDto) {
		log.info("REST request to update student: {}", studentDto);

		if (studentDto.getId() == null) {
			throw new BadRequestAlertException("ID cannot be null");
		}

		if (!Objects.equals(id, studentDto.getId())) {
			throw new BadRequestAlertException("Invalid ID");
		}

		if (studentService.notExistsById(id)) {
			throw new ResourceNotFoundException("student", "id", id);
		}

		StudentDTO result = studentService.save(studentDto);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		log.info("REST request to delete student by id: {}", id);

		if (studentService.notExistsById(id)) {
			throw new ResourceNotFoundException("student", "id", id);
		}

		studentService.delete(id);
		return ResponseEntity.ok().build();
	}
}
