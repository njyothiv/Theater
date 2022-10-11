package com.jpmc.theater.exception;

import lombok.Data;

@Data
public class InvalidSequenceException extends RuntimeException {

	private String expMsg;
	private String expCode;
		
	public InvalidSequenceException(String expCode, String exp) {
		super(exp);
		this.expCode = expCode;
		this.expMsg = exp;
	}

}
