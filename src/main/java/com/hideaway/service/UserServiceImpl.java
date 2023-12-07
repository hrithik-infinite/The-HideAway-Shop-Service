package com.hideaway.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hideaway.config.JwtProvider;
import com.hideaway.exception.UserException;
import com.hideaway.model.User;
import com.hideaway.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get();
		}
		throw new UserException("user not found with id " + userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		System.out.println("user service");
		String email = jwtProvider.getEmailFromJwtToken(jwt);

		System.out.println("email" + email);

		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UserException("user not exist with email " + email);
		}
		System.out.println("email user" + user.getEmail());
		return user;
	}

}
