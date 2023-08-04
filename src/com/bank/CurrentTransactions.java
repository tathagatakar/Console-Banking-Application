package com.bank;

import java.util.*;

import com.custom_exceptions.LowBalanceException;

public class CurrentTransactions {
	int choice;
	
	Scanner scanner = new Scanner(System.in);
	Current current = new Current();
	Customer customer;
	
	//set the account balance of current account
	public void setAccountBalance(double x) {
		current.setAccountBalance(x);
	}
	
	public void setCustomerDetails(Customer customer) {
		this.customer = customer;
	}
	
	//perform the respective tranactions
	public void doTransactions() {
		do {
			System.out.println("1. Deposit amount.");
			System.out.println("2. Withdraw amount.");
			System.out.println("3. Show Account details.");
			System.out.println("4. Show Transactions details.");
			System.out.println("5. Exit.");
			choice=scanner.nextInt();
			
			switch (choice) {
				case 1: 
					System.out.println("Enter the amount you want to deposit");
					double dep = scanner.nextDouble();
					current.deposit(dep);
					break;
					
				case 2:
					System.out.println("Enter the amount you want to withdraw");
					double with = scanner.nextDouble();
					try {
						current.withDraw(with);
					}
					catch(LowBalanceException e) {
						System.out.println(e);
					}
					break;
					
				case 3: 
					System.out.println(customer);
					current.showDetails();
					break;
					
				case 4:
					current.transactionCount();
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


