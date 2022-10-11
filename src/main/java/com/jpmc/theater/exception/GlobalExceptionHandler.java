package com.jpmc.theater.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * Global Exception to handle given exceptions across the project 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private String expMsg; 
	private String expCode;
	ApiError apiError;

	@ExceptionHandler(value= {InvalidSequenceException.class})
	public ResponseEntity<ApiError> handleInvalidSequenceException(InvalidSequenceException ise) {
		expMsg = ise.getExpMsg();
		expCode = ise.getExpCode();
		
		apiError = new ApiError(expMsg,expCode);
		return new ResponseEntity<> (apiError, HttpStatus.BAD_REQUEST);	
	}
	
	
	@ExceptionHandler(value= {InvalidTicketCountException.class })
	public ResponseEntity<ApiError> handleInvalidTicketCountException(InvalidTicketCountException ise) {
		expMsg = ise.getExp();
		expCode = ise.getExpCode();
		
	    apiError = new ApiError(expMsg,expCode);
		return new ResponseEntity<> (apiError, HttpStatus.BAD_REQUEST);	
	}
}
