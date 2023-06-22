package com.masai.main.service;

import java.util.List;

import com.masai.main.model.Student;

public interface StudentService {
	
	public Student registerStudent(Student student);
	
	public Student findStudentByEmail(String email);
	
	public List<Student> getAllStudent();

}
