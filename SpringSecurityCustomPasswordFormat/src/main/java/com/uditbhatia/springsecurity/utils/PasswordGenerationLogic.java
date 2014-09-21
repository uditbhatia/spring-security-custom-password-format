package com.uditbhatia.springsecurity.utils;


/**
 * Custom Password Generation Logic.
 * 
 * @author uditbhatia
 *
 */
public class PasswordGenerationLogic {

	/**
	 * Password generation Logic.
	 * @param user
	 * @return
	 */
	public static String generatePassword(Long id, String username,
			String password) {
		return id+username+password;
	}
}
