package com.hideaway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hideaway.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
}
