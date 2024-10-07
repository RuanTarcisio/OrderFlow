package com.rtarcisio.inventaryms.services.exceptions;

public class MyMalformedURLException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	public MyMalformedURLException(String msg) {
		super(msg);
	}
	
	public MyMalformedURLException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
