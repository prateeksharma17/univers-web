package com.prateek.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prateek.model.entity.User;
import com.prateek.repository.security.UserRepository;

@Service
public class RegisterUserServiceImpl implements RegisterUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void save(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		
		userRepository.save(user);		
	}

}
