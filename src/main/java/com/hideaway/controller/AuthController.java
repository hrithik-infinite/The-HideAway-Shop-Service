package com.hideaway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hideaway.config.JwtProvider;
import com.hideaway.exception.UserException;
import com.hideaway.model.User;
import com.hideaway.repository.UserRepository;
import com.hideaway.response.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	public AuthController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user)throws UserException{
		String email = user.getEmail();
        String password = user.getPassword();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();

        User isEmailExist=userRepository.findByEmail(email);

        if (isEmailExist!=null) {
        	
            throw new UserException("Email Is Already Used With Another Account");
        }
        User createdUser= new User();
		createdUser.setEmail(email);
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
        createdUser.setPassword(passwordEncoder.encode(password));
        
        User savedUser= userRepository.save(createdUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse(token,true);
		
        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
	}

//	public ResponseEntity<AuthResponse>loginUserHandler()
}
