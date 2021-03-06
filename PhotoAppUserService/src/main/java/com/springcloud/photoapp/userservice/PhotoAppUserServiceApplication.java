package com.springcloud.photoapp.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import feign.Logger;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class PhotoAppUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppUserServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	@LoadBalanced
	//enables client-side load-balancing for calling albums from user-service
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Logger.Level feignLoggerLover(){
		return Logger.Level.FULL;
	}
}
