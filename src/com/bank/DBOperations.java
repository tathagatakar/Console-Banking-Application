package com.bank;

import java.sql.*;

public class DBOperations {
	String db_name;
	long db_phnNo, db_accNo;
	double db_balance;
	int db_accType;
	Connection connection;
	Statement statement;
	
	public void startDatabase() {
		try {
			// load driver
			Class.forName("org.h2.Driver");
			// create connection
			connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			// create statement
			statement = connection.createStatement();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void stopDatabase() {
		try {
			// close the connections
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean searchPan(String panNo) throws Exception { //extracting the related values of the customer using his/her pan number.
			int flag=0;
			
			String search= "SELECT * FROM BANK1 WHERE PAN_NO = '"+panNo+"'";
			
			ResultSet resultSet = statement.executeQuery(search);
			
			while (resultSet.next()) {
				db_accType=resultSet.getInt("ACCOUNT_TYPE");
				db_balance=resultSet.getDouble("BALANCE");
				db_accNo=Long.parseLong(resultSet.getString("ACCOUNT_NO"));
				db_name=resultSet.getString("NAME");
				db_phnNo=Long.parseLong(resultSet.getString("PHONE_NO"));
				flag=1;
			}
			
			if (flag!=0)
				return true;
			else
				return false;
	}
	
	public String getName() {
		return db_name;
	}
	
	public long getPhoneNumber() {
		return db_phnNo;
	}
	
	public long getAccountNumber() {
		return db_accNo;
	}
	
	public double getAccountBalance() {
		return db_balance;
	}
	
	public int getAccountType() {
		return db_accType;
	}
}
