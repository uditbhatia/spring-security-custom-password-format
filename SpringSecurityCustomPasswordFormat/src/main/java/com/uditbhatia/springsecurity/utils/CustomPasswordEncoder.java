package com.uditbhatia.springsecurity.utils;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * Custom Password Encoder.
 * 
 * @author uditbhatia
 *
 */
public class CustomPasswordEncoder implements PasswordEncoder{

	/**
	 * Validates weather the password is valid or not.
	 */
	@Override
	public boolean isPasswordValid(String ecPass, String rawPass, Object salt) {
		
		if(ecPass==null || ecPass.isEmpty()){
			return false;
		}
		String saltStr = (String) salt;
		String encPresentedPassword = DigestUtils.md5DigestAsHex((rawPass+ saltStr).getBytes());
		
		
		if(!ecPass.equals(encPresentedPassword)){
			
			/**
			 * Match failed,therefore return false;
			 */
			return false;
		}
		
		/**
		 * Successfully matched.
		 */
		return true;
	}
	
	/**
	 * Password Encoding Logic.
	 */
	@Override
	public String encodePassword(String rawPass, Object salt) {
		return DigestUtils.md5DigestAsHex((rawPass+ salt).getBytes());
	}
}
