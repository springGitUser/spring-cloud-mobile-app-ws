package com.springcloud.photoapp.userservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@GetMapping("/status/check")
	public String greet() {
		return "Working...";
	}

}
