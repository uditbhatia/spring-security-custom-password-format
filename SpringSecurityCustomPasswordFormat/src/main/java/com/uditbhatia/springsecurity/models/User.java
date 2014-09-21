package com.uditbhatia.springsecurity.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uditbhatia.springsecurity.utils.CustomPasswordEncoder;
import com.uditbhatia.springsecurity.utils.CustomSaltSource;
import com.uditbhatia.springsecurity.utils.PasswordGenerationLogic;

/**
 * User Model implementing {@link UserDetails} to give its reference to Spring Security.
 * 
 * @author uditbhatia
 *
 */
@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = -3130027959838946969L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * Username for the user.
	 */
	private String username;
	
	/**
	 * Encrypted Password.
	 */
	private String password;
	
	@PostPersist
	public void onPersist(){

		String rawPswd = PasswordGenerationLogic.generatePassword(this.getId(),this.getUsername(),this.getPassword());
		this.password = new CustomPasswordEncoder().encodePassword(rawPswd, new CustomSaltSource().getSalt(this));
	}
	
	/**
	 * Return Authorities granted to the user.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	/**
	 * Return Password (Should be encrypted.)
	 */
	@Override
	public String getPassword() {
		return this.password;
	}

	/**
	 * Returns Username
	 */
	@Override
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * @return True, if account is not expired, Else False.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * @return True, if account is not Locked, Else False.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * @return True, if credentials are not expired, Else False.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * @return True, if is Enabled, Else False.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Encrypt Password.
	 * @param password
	 */
	public void setPassword(String password) {
		
		this.password = password;
	}
	
	
}
