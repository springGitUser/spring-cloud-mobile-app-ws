package com.springcloud.mobilews.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleRootLevelExceptions(Exception ex, WebRequest wr) {

		String errMsg = ex.getLocalizedMessage();
		if (errMsg == null) {
			errMsg = ex.toString();
		}

		ErrorMessage em = new ErrorMessage(errMsg, new Date());
		return new ResponseEntity<Object>(em, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//Can Handle multiple types of exceptions 
	@ExceptionHandler(value = { CustomErrorMessage.class, NullPointerException.class })
	public ResponseEntity<Object> handleCustomExceptions(Exception ex, WebRequest wr) {

		String errMsg = ex.getLocalizedMessage();
		if (errMsg == null) {
			errMsg = ex.toString();
		}

		ErrorMessage em = new ErrorMessage(errMsg, new Date());
		return new ResponseEntity<Object>(em, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
