package com.user.service;

import java.util.List;

import com.user.entity.User;

public interface UserService {

	public List<User> getAllUsers();
	public User getUserById(Long id);
	public User saveUser(User user);
	//User getUserByName(String userName);
	public User findByUsername(String username );
}
