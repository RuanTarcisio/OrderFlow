package com.rtarcisio.usernotification.services.exceptions;

public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ConflictException(String msg) {
		super(msg);
	}
}
