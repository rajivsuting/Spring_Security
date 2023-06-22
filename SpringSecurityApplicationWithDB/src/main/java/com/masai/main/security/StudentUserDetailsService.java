package com.masai.main.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			return new User(student.getEmail(), student.getPassword(), authorities);
			
		}else throw new StudentException("User details not found with username "+ username);
	}

}
