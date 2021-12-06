package com.springcloud.photoapp.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppUserServiceApplication.class, args);
	}

}
