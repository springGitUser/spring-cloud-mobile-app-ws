package com.springcloud.photoapp.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		switch (response.status()) {
		case 400:

			break;
		case 404:
			if (methodKey.contains("userAlbums")) {
				return new ResponseStatusException(HttpStatus.valueOf(response.status()), "User Albumns not found");
			}
			break;

		default:
			break;
		}
		return null;
	}

}
