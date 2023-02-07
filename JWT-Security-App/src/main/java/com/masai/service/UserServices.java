package com.masai.service;

import com.masai.entity.User;
import com.masai.exception.UserException;

public interface UserServices {

	public User registerUser(User user) throws UserException;
	public User findUserById(Integer id) throws UserException;
}
