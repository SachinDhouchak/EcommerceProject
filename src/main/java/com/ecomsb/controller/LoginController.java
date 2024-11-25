package com.ecomsb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecomsb.model.Role;
import com.ecomsb.model.User;
import com.ecomsb.repository.RoleRepository;
import com.ecomsb.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	public RoleRepository roleRepository;
	
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserRepository userRepository;
	
//	@GetMapping("/login")
//	public String login() {
//		GlobalData.cart.clear();
//		return "login";
//	}
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") User user,HttpServletRequest request) throws ServletException {
		
		String password=user.getPassword();		
		user.setPassword(bCryptPasswordEncoder.encode(password));
		
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findById(2).get());           // NEED TO UNDERSTAND
		user.setRoles(roles);
		userRepository.save(user); 
		
		request.login(user.getEmail(), password);
		
		return "redirect:/";
	}
}
