package com.uditbhatia.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.uditbhatia.springsecurity.models.User;

/**
 * User Defained Repository.
 * @author uditbhatia
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find User with the given username;
	 * @return 
	 */
	public UserDetails findUserByUsername(String username);
}
