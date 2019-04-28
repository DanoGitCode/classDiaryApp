package com.dano.classDiaryApplication.service;

import com.dano.classDiaryApplication.model.User;

public interface UserService {
	
	public void saveUser(User user);
	
	public boolean isUserAlreadyPresent(User user);
	
}
