package com.epam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.entities.User;
import com.epam.repository.UserRepo;

@Service
public class UserAuthenticationImpl implements UserAuthentication {

	@Autowired
	UserRepo userRepo;

	@Override
	public boolean logIn(User user) {
		boolean isUserPresent = false;
		Optional<User> existingUser = userRepo.findByIdAndPasswordAndType(user.getId(), user.getPassword(),
				user.getType());
		if (existingUser.isPresent()) {
			isUserPresent = true;
		}  
		return isUserPresent;
	}

	@Override
	public boolean signUp(User user) {
		boolean isSignedUp = true;
		userRepo.save(user);
		return isSignedUp;
	}
}
 