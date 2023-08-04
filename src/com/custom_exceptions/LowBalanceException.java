package com.custom_exceptions;

@SuppressWarnings("serial")
public class LowBalanceException extends Exception {
	public LowBalanceException(String message) {
		super(message);
	}
}