package com.masai.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	public Optional<User> findByUserName(String userName);

}
