package com.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.entites.User;

@FeignClient(name = "User", url = "http://localhost:8081/")
public interface UserClient {

	@GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable("id") Long id);
}
