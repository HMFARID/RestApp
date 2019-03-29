package com.ScifiSoft.RestApp.Service;

import java.util.List;

import com.ScifiSoft.RestApp.dto.StudentDTO;

public interface StudentService {
	StudentDTO createOrUpdate(StudentDTO studentDto);

	StudentDTO getById(String studentId);

	List<StudentDTO> getAll();

	boolean deleteById(String studentId);
}
