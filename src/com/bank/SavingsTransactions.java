package com.bank;

import java.util.*;

import com.custom_exceptions.LowBalanceException;
import com.custom_exceptions.TransactionLimitExceededException;

public class SavingsTransactions {
	
	int choice;
	Scanner sc = new Scanner(System.in);
	Savings savings= new Savings();
	Customer customer;
	
	//set the account balance of the savings account
	public void setAccountBalance(double x) {
		savings.setAccountBalance(x);
	}
	
	public void setCustomerDetails(Customer customer) {
		this.customer = customer;
	}
	
	//perform the respective transactions
	public void doTransactions() {
		do {
			System.out.println("1. Deposit amount.");
			System.out.println("2. Withdraw amount.");
			System.out.println("3. Show Account details.");
			System.out.println("4. Show Transactions details.");
			System.out.println("5. Exit.");
			choice=sc.nextInt();
			
			switch (choice) {
				case 1: 
					System.out.println("Enter the amount you want to deposit");
					double dep = sc.nextDouble();
					try {
						savings.deposit(dep);
					}
					catch (TransactionLimitExceededException e) {
						System.out.println(e);
					}
					
					break;
					
				case 2:
					System.out.println("Enter the amount you want to withdraw");
					double with = sc.nextDouble();
					try {
					savings.withDraw(with);
					}
					catch(TransactionLimitExceededException e) {
						System.out.println(e);
					}
					catch (LowBalanceException e) {
						System.out.println(e);
					}
					break;
					
				case 3: 
					System.out.println(customer);
					savings.showDetails();
					break;
					
				case 4:
					savings.transactionCount();
					break;
					
				case 5:
					System.out.println("Bye...");
					break;
					
				default:
					System.out.println("Wrong Input!! Enter your choice again.");
			}	
		} while(choice!=5);
	}
	
	
}

