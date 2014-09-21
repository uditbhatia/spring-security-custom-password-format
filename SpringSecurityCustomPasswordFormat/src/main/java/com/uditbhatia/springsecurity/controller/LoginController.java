package com.uditbhatia.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uditbhatia.springsecurity.models.User;
import com.uditbhatia.springsecurity.repository.UserRepository;

/**
 * Main Controller
 * @author uditbhatia
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Login View Mapping.
	 * 
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		
		return "login";
	}
	
	/**
	 * Success View Mapping.
	 * 
	 * @return
	 */
	@RequestMapping(value="/success",method=RequestMethod.GET)
	public String success(Model model){
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = (User) auth.getPrincipal(); 
		model.addAttribute("message","Welcome !.");
		model.addAttribute("user",user);
		
		return "success";
	}

	/**
	 * Sign up Method,
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String signup(@ModelAttribute User user,Model model){
		
		/**
		 * Persist User.
		 */
		userRepository.save(user);
		model.addAttribute("user",user);
		model.addAttribute("message","Succesfully Created !.");
		
		return "success";
	}
	
	/**
	 * Sign up View Method,
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String signupView(){
		
		return "signup";
	}
}
