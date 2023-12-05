package com.hideaway.service;

import com.hideaway.exception.UserException;
import com.hideaway.model.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
