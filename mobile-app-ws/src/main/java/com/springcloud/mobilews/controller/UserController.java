package com.springcloud.mobilews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.mobilews.model.request.UserRequest;
import com.springcloud.mobilews.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/greet")
	private String greet() {
		return "Greeting";
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRequest> createUser(@RequestBody UserRequest userReq) {
		UserRequest createrUser = userService.createrUser(userReq);
		return new ResponseEntity<UserRequest>(createrUser, HttpStatus.CREATED);
	}

	// From postman Accept Header we can pass xml/json to return the desired
	// response we need.
	// Accept ==> application/json,application/xml
	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRequest> getUserById(@PathVariable String userId) {
		UserRequest createrUser = userService.getUserById(userId);
		return new ResponseEntity<UserRequest>(createrUser, HttpStatus.OK);
	}

	@PutMapping(path = "/{userId}")
	public ResponseEntity<UserRequest> updateUser(@RequestBody UserRequest userReq, @PathVariable String userId) {
		UserRequest createrUser = userService.updateUser(userReq, userId);
		return new ResponseEntity<UserRequest>(createrUser, HttpStatus.ACCEPTED);
	}
}
