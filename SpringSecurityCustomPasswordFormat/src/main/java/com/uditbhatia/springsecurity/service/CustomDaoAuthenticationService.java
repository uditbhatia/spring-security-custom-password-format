package com.uditbhatia.springsecurity.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.uditbhatia.springsecurity.models.User;
import com.uditbhatia.springsecurity.utils.PasswordGenerationLogic;

/**
 * Overrides {@link DaoAuthenticationProvider} to override the password combination logic, and implements {@link AuthenticationProvider} to regoster as the Authentication Provider.
 * 
 * @author uditbhatia
 *
 */
@Service
public class CustomDaoAuthenticationService extends DaoAuthenticationProvider implements AuthenticationProvider{
	
	/**
	 * Overriding to give custom combination for password.
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;

        if (this.getSaltSource() != null) {
            salt = this.getSaltSource().getSalt(userDetails);
        }

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }

        String presentedPassword = authentication.getCredentials().toString();
        
        User user = (User) userDetails;
        if (!this.getPasswordEncoder().isPasswordValid(userDetails.getPassword(),PasswordGenerationLogic.generatePassword(user.getId(),user.getUsername(),presentedPassword), salt)) {
            logger.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }
    }
	
	/**
	 * Set {@link UserDetailsService} Implementation to be used.
	 */
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		
		super.setUserDetailsService(userDetailsService);
	}
	
	@Override
	protected void doAfterPropertiesSet() throws Exception {
		
	}
}
