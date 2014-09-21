package com.uditbhatia.springsecurity.utils;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Custom Salt Source.
 * 
 * @author uditbhatia
 *
 */
public class CustomSaltSource implements SaltSource {

	@Override
	public Object getSalt(UserDetails user) {
		
		return "Dark1@#Knight1@#Rises";
	}

}
