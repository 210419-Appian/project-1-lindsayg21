package com.revature;

public class Account {
	
	//represents single account for a user

	private int accountId; // primary key
	private double balance;  // not null
	private AccountStatus status;
	private AccountType type;
	
	//need to get account id
	
	//URL: /accounts/withdraw
	public String withdraw(int accountId, double amount, AccountType type) { 
		//have to call database here
		//we need accountId, amount, and account withdrawing from
		//ask for user's inputs:
		System.out.println("account id: ");
		System.out.println("amount: ");
		balance -= amount;
		return "Your new balance is: $" + balance;
	}
	
	//URL: /account/deposit
	public String deposit(int accountId, double amount, AccountType type) {
		//we need accountId, amount, and account depositing into
		//ask for user's inputs:
		System.out.println("account id:"); //do something with URI
		System.out.println("amount: "); //do something with URI
		balance += amount; 
		return "Your new balance is: $" + balance;
	}
	
	public String transfer(int accountId, double firstAcct, double secondAcct, double amount) {
		//need accountId, account taking money from, account putting money into, 
		//and the amount of the transaction
		firstAcct -= amount;
		secondAcct += amount;
		return "Your now have $" + secondAcct +" in your "; 
	}
	
}
