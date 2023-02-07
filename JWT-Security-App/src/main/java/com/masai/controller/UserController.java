package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.User;
import com.masai.exception.UserException;
import com.masai.service.UserServices;



@RestController
public class UserController {

	@Autowired
	private UserServices userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerAdmin(@RequestBody User user) throws UserException {
		
		User saveAdmin = userService.registerUser(user);
		
		return new ResponseEntity<User>(saveAdmin,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> findAdminById(@PathVariable("id") Integer id) throws UserException{
		User user = userService.findUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
}
