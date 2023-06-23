package com.masai.main.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.main.model.Student;
import com.masai.main.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService sSer;
	
	@Autowired
	private PasswordEncoder pEncoder;
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Student> registerStudent(@RequestBody Student student){
		
		student.setPassword(pEncoder.encode(student.getPassword()));
		
		Student savedStudent = sSer.registerStudent(student);
		
		return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<Student> getStudentByEmail(@PathVariable("email") String email){
		
		return new ResponseEntity<Student>(sSer.findStudentByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents(){
		
		return new ResponseEntity<List<Student>>(sSer.getAllStudent(), HttpStatus.OK);
	}
	
	@GetMapping("/signin")
	public ResponseEntity<String> getLoggedInStudentDetailsHandler(Authentication auth){
		
		Student student = sSer.findStudentByEmail(auth.getName());
		return new ResponseEntity<String>(student.getName()+"is Logged in successfully", HttpStatus.ACCEPTED);
	}

}
