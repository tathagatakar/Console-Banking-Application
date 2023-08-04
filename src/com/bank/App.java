package com.bank;

import java.util.*;
import com.custom_exceptions.ValidateCustomerNameException;
import com.custom_exceptions.ValidatePanNumberException;
import com.custom_exceptions.ValidatePhoneNumberException;

public class App {
	public static void main (String args[]) 
	{
		String name, panNo;
		long phnNo;
		int panNo_flag, name_flag, phnNo_flag;
		boolean decision=true;
		
		Scanner scanner = new Scanner(System.in);
		//starting the database
		DBOperations database = new DBOperations();
		database.startDatabase();
		
		Customer customer = new Customer();
		
		System.out.println("Welcome to the Bank of JCI...");
		
		try {
			//validating user's pan number (it should be a string of length 10)
			do{
				System.out.println("Enter your PAN number: ");
				panNo = scanner.next();
				customer.setPanNo(panNo);
				panNo_flag=0;
				try {
					customer.panNumberCheck();
				}
				catch(ValidatePanNumberException e) {
					panNo_flag=1;
					System.out.println(e);
				}
				
			} while(panNo_flag==1);
		
			//seraching if the pan number is already there in the database
			try {
				decision = database.searchPan(panNo);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//check if the user is already having a savings or current account in the JCI's bank
			if (decision==true) 
			{
				if (database.getAccountType()==1) 
				{ //1 means Savings Account
					System.out.println("You already have a savings account in JCI");
					System.out.println("Your account details are as follows: Account No: "+database.getAccountNumber()+", Name: "+database.getName()+", Balance: "+database.getAccountBalance()+", Phone Number: "+database.getPhoneNumber());
					
					customer.setName(database.getName());
					customer.setPhnNo(database.getPhoneNumber());
					//proceedings for savings account of existing user
					SavingsTransactions savingsTransaction = new SavingsTransactions(); 
					savingsTransaction.setAccountBalance(database.getAccountBalance()); //set the account balance of the existing customer from the database
					savingsTransaction.setCustomerDetails(customer); //passing the customer details
					savingsTransaction.doTransactions(); //perform the transactions
				}
				else 
				{ //2 means Current Account
					System.out.println("You already have a current account in JCI");
					System.out.println("Your account details are as follows: Account No: "+database.getAccountNumber()+", Name: "+database.getName()+", Balance: "+database.getAccountBalance()+", Phone Number: "+database.getPhoneNumber());
					
					customer.setName(database.getName());
					customer.setPhnNo(database.getPhoneNumber());
					//proceedings for current account of existing user
					CurrentTransactions currentTransactions = new CurrentTransactions(); 
					currentTransactions.setAccountBalance(database.getAccountBalance()); //set the account balance from the database
					currentTransactions.setCustomerDetails(customer); //passing the customer details
					currentTransactions.doTransactions(); //perform the transactions
				}
			}
			
			
			else 
			{//when the user is new to the bank
				customer.setPanNo(panNo); 
				
				do {
					//validating the user's name (it should not contain any special character)
					System.out.println("Enter your name: ");
					name= scanner.next();
					customer.setName(name);
					name_flag=0;
					try {
						customer.customerNameCheck();
					}
					catch(ValidateCustomerNameException e) {
						name_flag=1;
						System.out.println(e);
					}
				} while (name_flag==1);
				
				customer.setName(name);
				
				do{
					//validating user phone number (it should be a number of length 10)
					System.out.println("Enter your phone number");
					phnNo = scanner.nextLong();
					customer.setPhnNo(phnNo);
					phnNo_flag=0;
					try {
						customer.phoneNumberCheck();
					}
					catch(ValidatePhoneNumberException e) {
						phnNo_flag=1;
						System.out.println(e);
					}
				} while(phnNo_flag==1);
				
				customer.setPhnNo(phnNo);	
				
				System.out.println("Enter the type of account you want to create. Press 1 for Savings Account. Press 2 for Current Account.");
				int account=scanner.nextInt();

				if (account == 1) {	
					//proceedings for savings account of the new user
					SavingsTransactions savingsTransaction = new SavingsTransactions();
					savingsTransaction.setCustomerDetails(customer); //passing the customer details
					savingsTransaction.doTransactions(); //perform the respective transactions
				}
				
				else {
					//proceedings for current account of the new user
					CurrentTransactions currentTransactions = new CurrentTransactions();
					currentTransactions.setCustomerDetails(customer); //passing the customer details
					currentTransactions.doTransactions(); //perform the respective transactions
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			database.stopDatabase(); //closing the database
			scanner.close(); //closing the scanncer
		}
	}
}