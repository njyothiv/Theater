package com.jpmc.theater.exception;

import lombok.Data;

@Data
public class InvalidTicketCountException extends RuntimeException {

		private final String exp;
		private final String expCode;
			
		public InvalidTicketCountException(String expCode, String exp) {
			super(exp);
			this.expCode = expCode;
			this.exp = exp;
		}

}
