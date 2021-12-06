package com.springcloud.mobilews.service;

import com.springcloud.mobilews.model.request.UserRequest;


public interface UserService {

	public UserRequest createrUser(UserRequest userRequest);

	public UserRequest getUserById(String userId);

	public UserRequest updateUser(UserRequest userReq, String userId);
}
