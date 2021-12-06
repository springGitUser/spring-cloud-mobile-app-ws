package com.springcloud.mobilews.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcloud.mobilews.model.request.UserRequest;
import com.springcloud.mobilews.service.UserService;
import com.springcloud.mobilews.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	Map<String, UserRequest> users = new HashMap<>();

	Utils utils;

	@Autowired
	public UserServiceImpl(Utils utilss) {
		this.utils = utilss;
	}

	@Override
	public UserRequest createrUser(UserRequest userRequest) {
		String userId = utils.generateUserId();
		users.put(userId, userRequest);
		userRequest.setUserId(userId);
		return userRequest;
	}

	@Override
	public UserRequest getUserById(String userId) {
		return users.get(userId);
	}

	@Override
	public UserRequest updateUser(UserRequest userReq,String userId) {
		UserRequest userRequestOld = users.get(userId);
		userRequestOld.setFirstName(userReq.getFirstName());
		userRequestOld.setLastName(userReq.getLastName());
		users.put(userId, userRequestOld);
		return userRequestOld;
	}

}
