package com.rtarcisio.orderms.exceptions;

public class URISyntaxException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public URISyntaxException(String msg) {
		super(msg);
	}
	
	public URISyntaxException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
