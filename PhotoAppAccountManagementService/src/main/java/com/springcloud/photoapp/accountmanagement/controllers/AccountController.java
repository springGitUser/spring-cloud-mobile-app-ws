package com.springcloud.photoapp.accountmanagement.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
	
	@GetMapping("/status/check")
	public String greet() {
		return "Working...";
	}

}
