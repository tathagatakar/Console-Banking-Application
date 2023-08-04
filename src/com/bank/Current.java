package com.bank;

import com.custom_exceptions.LowBalanceException;

class Current extends Account{
	int tCount=0;
	double balance=getMinBalance();

	double getRateOfInterest() { //defining the rate of interest of the bank
		return 5.0;
	}
	
	double getMinBalance() {
		return 0.0;
	}
	
	public void setAccountBalance(double x) { //setting the account balance of the existing customer from the database
		balance=x;
	}
	
	//deposit sum to the current account
	void deposit(double x) {
		balance=x+balance;
		System.out.println("Amount deposited: "+x);
		tCount+=1;
	}
	
	//withdraw amount from the current account
	void withDraw(double x) throws LowBalanceException{
		if ((balance-x)<0) {
			throw new LowBalanceException("Sorry! You do not have enough funds to carry out the transaction"); //custom exception to validate low account balance
		}
		else {
			balance=balance-x;
			System.out.println("Amount withdrawn: "+x);
			System.out.println("Your account balance is: "+balance);
			tCount+=1;
		}
	}
	
	//tracking transaction counts
	void transactionCount() {
		System.out.println("Transaction performed: "+tCount);
		System.out.println("Trasaction left: Unlimited");
	}
	
	void showDetails() {
		System.out.println("Your account balance is: "+balance);
		System.out.println("Trasaction left: Unlimited");
	}
}
