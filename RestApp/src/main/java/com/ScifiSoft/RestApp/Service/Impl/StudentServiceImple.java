package com.ScifiSoft.RestApp.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ScifiSoft.RestApp.Service.StudentService;
import com.ScifiSoft.RestApp.dao.StudentDAO;
import com.ScifiSoft.RestApp.dto.StudentDTO;
import com.ScifiSoft.RestApp.entites.Student;

@Service
@Transactional(readOnly = true)
public class StudentServiceImple implements StudentService {
	@Autowired
	private StudentDAO studentdao;

	@Override
	@Transactional(readOnly = false)
	public StudentDTO createOrUpdate(StudentDTO studentDto) {
		Student student = new Student();
		try {
			String studentId = studentDto.getStudent_id();
			int size = studentdao.findAll().size();
			size = size + 1;
			if (studentId == null) {
				student.setStudent_id(" " + size);

			} else {

			}
			student.setName(studentDto.getName());
			student.setAddress(studentDto.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentDto;
	}

	@Override
	public StudentDTO getById(String studentId) {

		StudentDTO dto = new StudentDTO();

		try {
			Student st = studentdao.findById(studentId);
			dto.setStudent_id(st.getStudent_id());
			dto.setName(st.getName());
			dto.setAddress(st.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public List<StudentDTO> getAll() {
		List<StudentDTO> dtoList = new ArrayList<>();
		try {
			List<Student> studentList = studentdao.findAll();
			for (Student st : studentList) {
				StudentDTO dto = new StudentDTO();
				dto.setStudent_id(st.getStudent_id());
				dto.setName(st.getName());
				dto.setAddress(st.getAddress());
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(String studentId) {
		try {
			studentdao.deleteById(Student.class, studentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
