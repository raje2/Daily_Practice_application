package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.User;
import com.masai.exception.UserException;
import com.masai.repositary.UserRepo;

@Service
public class UserSerImpl implements UserServices{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public User registerUser(User user) throws UserException {
		return userRepo.save(user);
	}

	@Override
	public User findUserById(Integer id) throws UserException {
		return userRepo.findById(id).orElseThrow(()->new UserException("Account does not found with id: "+id));
	}

}
