package com.springcloud.photoapp.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.photoapp.userservice.dto.UserRequestDTO;
import com.springcloud.photoapp.userservice.entity.UserEntity;
import com.springcloud.photoapp.userservice.service.UserService;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@GetMapping("/status/check")
	public String greet() {
		return "User Service is Working..." + env.getProperty("local.server.port");
	}

	@PostMapping("/createUser")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserRequestDTO userDTO) {
		System.out.println(userDTO.toString());
		UserEntity createUser = userService.createUser(userDTO);
		return new ResponseEntity<UserEntity>(createUser, HttpStatus.CREATED);
	}

}
