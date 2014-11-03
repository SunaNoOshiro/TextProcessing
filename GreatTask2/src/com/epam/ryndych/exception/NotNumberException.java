package com.epam.ryndych.exception;

public class NotNumberException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NotNumberException() {
		//System.out.println(getMessage());
	}
	
	@Override
	public String getMessage() {
		return "NotNumberException";
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
