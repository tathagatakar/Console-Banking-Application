package com.custom_exceptions;

@SuppressWarnings("serial")
public class ValidatePhoneNumberException extends Exception {
	public ValidatePhoneNumberException(String message) {
		super(message);
	}

}
