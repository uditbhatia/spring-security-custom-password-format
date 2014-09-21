package com.uditbhatia.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uditbhatia.springsecurity.repository.UserRepository;

/**
 * UserDetailServiceImpl Implementing UserDetailService overrides the loadUserByUsername to serve password.
 * @author uditbhatia
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		/**
		 * Load User in UserDetails reference as per the given username.
		 */
		UserDetails userDetails = userRepo.findUserByUsername(username);
		
		/**
		 * User not found.
		 */
		if(userDetails==null)
			throw new UsernameNotFoundException(username);
		
		return userDetails;
	}

}
