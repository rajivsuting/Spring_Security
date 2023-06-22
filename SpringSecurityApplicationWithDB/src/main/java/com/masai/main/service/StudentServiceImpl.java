package com.masai.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.main.exception.StudentException;
import com.masai.main.model.Student;
import com.masai.main.repository.StudentRepo;


@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepo srepo;

	@Override
	public Student registerStudent(Student student) {
		
		return srepo.save(student);
	}

	@Override
	public Student findStudentByEmail(String email) {

		return srepo.findByEmail(email).orElseThrow(() -> new StudentException("No Student found with email "+email));
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> students = srepo.findAll();
		
		if(!students.isEmpty()) return students;
		
		throw new StudentException("No students found in DB");
	}

}
