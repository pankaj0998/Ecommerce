package com.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repository.UserRepository;

@Service

public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repo;

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return repo.getById(id);
	}

	@Override
	public User saveUser(User user) {
		return repo.save(user);
	}
	
	@Override
	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}

}
