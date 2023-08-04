package com.custom_exceptions;

@SuppressWarnings("serial")
public class ValidateCustomerNameException extends Exception {
	public ValidateCustomerNameException (String message) {
		super(message);
	}

}
