package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.JWTResponse;
import com.masai.entity.UserLoginDTO;
import com.masai.helper.JwtUtil;
import com.masai.service.CustomUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private CustomUserDetailsService cUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<JWTResponse> generateToken(@RequestBody UserLoginDTO userDTO) throws Exception{
		
		System.out.println(userDTO);
		
		try {
			//for authentication 
			authManager
			.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("invaild username/password");
		}
		
		UserDetails userDetails =  cUserDetailsService.loadUserByUsername(userDTO.getUserName());
		
		//generate token
		String token  = jwtUtil.generateToken(userDetails);
		System.out.println("JWT"+token);
		
		//{"token":"value"} return json format
		JWTResponse t = new JWTResponse(token);
		return new ResponseEntity<JWTResponse>(t,HttpStatus.OK);
		
	}
	
}
