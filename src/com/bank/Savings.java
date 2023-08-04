package com.bank;

import com.custom_exceptions.LowBalanceException;
import com.custom_exceptions.TransactionLimitExceededException;

class Savings extends Account{
	int tCount=0;
	final int LIMIT=5;
	
	double balance=getMinBalance();
	
	public void setAccountBalance(double x) {  //setting the account balance of the existing customer from the database
		balance=x;
	}

	double getRateOfInterest() {
		return 10.0;
	}
	
	double getMinBalance(){
		return 5000.0;
	}
	
	//deposit sum to the savings account
	void deposit(double x) throws TransactionLimitExceededException {
			if (tCount < LIMIT) {
				balance=x+balance;
				System.out.println("Amount deposited: "+x);
				System.out.println("Your account balance is: "+balance);
				tCount+=1;
			}
			else {
				throw new TransactionLimitExceededException("Sorry!! Your transaction limit has been exceeded for the month"); //custom exception to validate transaction limits
		}
	}
	
	//withdraw from the savings account
	void withDraw(double x) throws LowBalanceException, TransactionLimitExceededException {
		if ((balance-x) < getMinBalance() && tCount < LIMIT) {
			throw new LowBalanceException("Warning! JCI's Savings Account Balance cannot be less than 5000/."); //custom exception to validate min account balance
		}
		else if (tCount < LIMIT) {
			balance=balance-x;
			System.out.println("Amount withdrawn: "+x);
			System.out.println("Your account balance is: "+balance);
			tCount+=1;
			}
		else {
			throw new TransactionLimitExceededException("Sorry!! Your transaction limit has been exceeded for the month"); //custom exception to validate transaction limits
		}
	}
	
	//track the transaction counts of the customer
	void transactionCount() {
		System.out.println("Transaction performed: "+tCount);
		System.out.println("Trasaction left: "+(LIMIT-tCount));
	}
	
	void showDetails() {
		System.out.println("Your account balance is: "+balance);
		System.out.println("Trasaction left: "+(LIMIT-tCount));
	}
}