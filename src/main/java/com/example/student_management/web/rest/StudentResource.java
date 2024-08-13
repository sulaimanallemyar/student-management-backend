package com.example.student_management.web.rest;

import java.util.List;
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
import org.modelmapper.ModelMapper;

import com.example.student_management.domain.Student;
import com.example.student_management.service.StudentService;
import com.example.student_management.service.dto.StudentDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for managing {@link com.example.student_management.domain.Student}.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class StudentResource {

	private final StudentService studentService;
	
	private final ModelMapper modelMapper;
	
	public StudentResource(StudentService studentService, ModelMapper modelMapper) {
		this.studentService = studentService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/students")
	public ResponseEntity<List<StudentDTO>> getStudents(Pageable pageable) {
		log.info("REST request to get floors by branch id: {}");

		
		Page<Student> page = studentService.findAll(pageable);
		List<StudentDTO> result = page.stream().map(obj -> modelMapper.map(obj, StudentDTO.class))
				.collect(Collectors.toList());
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
		
		return ResponseEntity.ok().headers(headers).body(result);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
		log.info("REST request to get floor by id: {}", id);

		if (studentService.notExistsById(id)) {
			return ResponseEntity.notFound().build();
		}

		Student obj = studentService.findById(id);
		StudentDTO result = this.modelMapper.map(obj, StudentDTO.class);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("/students")
	public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDto) {
		log.info("REST request to save floor: {}", studentDto);

		Student obj = studentService.save(studentDto);
		StudentDTO result = this.modelMapper.map(obj, StudentDTO.class);
		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping("/students")
	public ResponseEntity<StudentDTO> updateStudent(@Valid @RequestBody StudentDTO studentDto) {
		log.info("REST request to update floor: {}", studentDto);

		Student obj = studentService.save(studentDto);
		StudentDTO result = this.modelMapper.map(obj, StudentDTO.class);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		log.info("REST request to delete floor by id: {}", id);

		if (studentService.notExistsById(id)) {
			return ResponseEntity.notFound().build();
		}

		studentService.delete(id);
		return ResponseEntity.ok().build();
	}
	
//	private ResponseEntity<String> BadRequestAlertException(String message) {
//		
////        JSONObject response = new JSONObject();
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
////        
////        response.put("data", new JSONArray());
////        response.put("status", 400);
////        response.put("message", message);
////        response.put("timestamp", Instant.now());
//        
//        return new ResponseEntity<>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
//	}
	
}
