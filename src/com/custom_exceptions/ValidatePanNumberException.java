package com.custom_exceptions;

@SuppressWarnings("serial")
public class ValidatePanNumberException extends Exception {
	public ValidatePanNumberException(String message) {
		super(message);
	}

}
