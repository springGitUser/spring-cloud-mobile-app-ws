package com.springcloud.photoapp.userservice.repos;

import org.springframework.data.repository.CrudRepository;

import com.springcloud.photoapp.userservice.entity.UserEntity;


public interface UserRepository extends CrudRepository<UserEntity, Long>{

	
	UserEntity findByEmail(String email);
}
