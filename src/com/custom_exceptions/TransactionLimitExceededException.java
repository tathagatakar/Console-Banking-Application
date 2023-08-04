package com.custom_exceptions;

@SuppressWarnings("serial")
public class TransactionLimitExceededException extends Exception {
	public TransactionLimitExceededException(String message) {
		super(message);
	}
}
