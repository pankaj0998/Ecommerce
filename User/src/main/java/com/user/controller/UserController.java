package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;



@RestController

public class UserController {

	@Autowired
	private UserService userservice;

	@GetMapping("/users")
	
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = userservice.getAllUsers();

		if (!users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/users/{id}")
	
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		User user = userservice.getUserById(id);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	

	@PostMapping("/users")
	
	public ResponseEntity<User> addUser(@RequestBody User user) {
		try {
			userservice.saveUser(user);
			return new ResponseEntity<User>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/users", params="name")
	public ResponseEntity<User> findByUserName(@RequestParam("name")  String name ){
		User user=userservice.findByUsername(name);
		if(user != null) {
    		return new ResponseEntity<User>(
    				user,
    				HttpStatus.OK);
    	}
        return new ResponseEntity<User>(
        		HttpStatus.NOT_FOUND);
	}
}
