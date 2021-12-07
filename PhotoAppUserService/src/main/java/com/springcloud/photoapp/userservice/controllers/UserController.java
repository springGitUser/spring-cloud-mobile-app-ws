package com.springcloud.photoapp.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private Environment env;
	@GetMapping("/status/check")
	public String greet() {
		return "User Service is Working..."+env.getProperty("local.server.port");
	}

}
