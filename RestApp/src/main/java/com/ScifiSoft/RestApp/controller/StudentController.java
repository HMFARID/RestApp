package com.ScifiSoft.RestApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ScifiSoft.RestApp.Service.StudentService;
import com.ScifiSoft.RestApp.dto.StudentDTO;

@RestController
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("createOrUpdate")
	StudentDTO createOrUpdate(@RequestBody StudentDTO studentDto) {
		StudentDTO dto = studentService.createOrUpdate(studentDto);
		return dto;
	}

	@GetMapping("getById")
	StudentDTO getById(@RequestHeader(value = "studentId", defaultValue = "") String StudentId) {
		StudentDTO dto = studentService.getById(StudentId);
		return dto;
	}

	@GetMapping("getAll")
	List<StudentDTO> getAll() {
		List<StudentDTO> studentDTOList = studentService.getAll();
		return studentDTOList;
	}

	@DeleteMapping("deleteById")
	boolean deleteById(@RequestHeader(value = "studentId", defaultValue = "") String StudentId) {
		boolean flag = studentService.deleteById(StudentId);
		return flag;
	}
}
