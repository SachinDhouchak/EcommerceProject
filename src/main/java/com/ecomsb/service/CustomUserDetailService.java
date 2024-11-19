package com.ecomsb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecomsb.model.CustomUserDetail;
import com.ecomsb.model.User;
import com.ecomsb.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		 Optional<User> user = userRepository.findUserByEmail(email);
		 user.orElseThrow( () -> new UsernameNotFoundException("User doesn't exist") );    // R&D on it
		 
		 return user.map(CustomUserDetail::new).get();		
	}

	
}
