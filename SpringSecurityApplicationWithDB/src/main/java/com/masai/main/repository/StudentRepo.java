package com.masai.main.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.main.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	public Optional<Student> findByEmail(String email);

}
