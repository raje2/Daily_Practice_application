package com.masai.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.repositary.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//step-1 Fist here we needs to check user is exist with this username or not
		
		   Optional<com.masai.entity.User> findUser = userRepo.findByUserName(username);
		   com.masai.entity.User getUser = findUser.get();
		   
		   if(username.equals(getUser.getUserName())) {
			   
			   
			   //step 2 here we needs to import UserDeatails User
			  return new User(getUser.getUserName(), getUser.getPassword(), new ArrayList<>());  
		   }
		   else
			   //here we throw predefined username not found exception
		     throw new UsernameNotFoundException("Invalid username");
	}

}
