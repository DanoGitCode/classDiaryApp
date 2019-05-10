package com.dano.classDiaryApplication.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dano.classDiaryApplication.model.Role;
import com.dano.classDiaryApplication.model.User;
import com.dano.classDiaryApplication.repository.RoleRepository;
import com.dano.classDiaryApplication.repository.UserRepository;


@Service
public class UserServiceImp implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository  userRepository;

	Role userRole;
	
	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		
		
		if (user.getuRole() == 1) {
			userRole = roleRepository.findByRole("DIRECTOR_USER");
		}
		else if (user.getuRole() == 2) {
			 userRole = roleRepository.findByRole("TEACHER_USER");
		}
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		
		boolean isUserAlreadyExists = false;
		User existingUser = userRepository.findByEmail(user.getEmail());
		
		if(existingUser != null) {
			isUserAlreadyExists = true;
		}
		
		return isUserAlreadyExists;
	}

}
