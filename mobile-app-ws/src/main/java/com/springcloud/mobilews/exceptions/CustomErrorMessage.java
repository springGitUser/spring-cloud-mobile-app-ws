package com.springcloud.mobilews.exceptions;

public class CustomErrorMessage extends RuntimeException {

	public CustomErrorMessage(String message) {
		super(message);
	}

}
