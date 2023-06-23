package com.masai.main.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.main.exception.StudentException;
import com.masai.main.model.Student;
import com.masai.main.repository.StudentRepo;


@Service
public class StudentUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private StudentRepo sRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Student> opt = sRepo.findByEmail(username);
		
		if(opt.isPresent()) {
			
			Student student = opt.get();
			return new StudentUserDetails(student);
						
		}else throw new StudentException("User details not found with username "+ username);
	}

}
