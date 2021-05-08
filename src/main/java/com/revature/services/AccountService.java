package com.revature.services;

import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.models.User;

import java.util.List;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;

public class AccountService {

	private AccountDAO accDao = new AccountDAOImpl();
	
	
	public List<Account> getAllAccounts() {
		return accDao.findAll();
	}

	public Account findByAccountId(int accountId) {
		return accDao.findByAccountId(accountId);
	}

	public Account findOneUser(int userId) {
		return accDao.findByUserId(userId);
	}

	public double getAccountBalance(int accountId) {
		return accDao.findAccountBalance(accountId);
	}

	// need: account id, amount to withdraw
	public boolean withdraw(int accountId, double amount) {

		double balance = accDao.findAccountBalance(accountId);
		
		if (amount <= balance) {
			balance -= amount;
			System.out.println("Your new account balance is: $" + balance);
		} else {
			System.out.println("You don't have enough funds to make this transaction");
			return false;
		}

		return true;
	}
	
	public double deposit(int accountId, double amount) {
		
		double balance = accDao.findAccountBalance(accountId);
		
		balance += amount;
		
		System.out.println("Your new account balance is $" + balance);
		
		return balance;
	}
	
	public boolean transferMoney(int accountIdToWithdraw, int accountIdToDeposit, double amount) {
		
		double balanceOfWithdrawAccount = accDao.findAccountBalance(accountIdToWithdraw);
		
		double balanceOfDepositAccount = accDao.findAccountBalance(accountIdToDeposit);
		
		if(amount <= balanceOfWithdrawAccount) {
			balanceOfWithdrawAccount -= amount;
			balanceOfDepositAccount += amount;
			System.out.println("Account number " + accountIdToWithdraw + " now has a balance of $" + balanceOfWithdrawAccount);
			System.out.println("Account number " + accountIdToDeposit + " now has a balance of $" + balanceOfDepositAccount);
		}
		else {
			System.out.println("Account number " + accountIdToWithdraw + " doesn't have enough funds to complete this transfer.");
			return false; 
		}
		
		return true;
	}

	public boolean createAccount(Account account) {
		return accDao.addAccount(account);
	}

	public boolean removeAccount(int accountId) {
		return accDao.deleteAccount(accountId);
	}
	
	// represents single account for a user
	// this is the BUSINESS logic
	// needs review
	// services should call on account

	// URL: /accounts/withdraw
//	public String withdraw(int accountId, double amount, AccountType type) {
//		// have to call database here
//		// we need accountId, amount, and account withdrawing from
//		System.out.println("account id: ");
//		System.out.println("amount: ");
//		balance -= amount;
//		return "Your new balance is: $" + balance;
//	}
//
//	// URL: /account/deposit
//	public String deposit(int accountId, double amount, AccountType type) {
//		// we need accountId, amount, and account depositing into
//		System.out.println("account id:"); // do something with URI
//		System.out.println("amount: "); // do something with URI
//		balance += amount;
//		return "Your new balance is: $" + balance;
//	}
//
//	public String transfer(int accountId, double firstAcct, double secondAcct, double amount) {
//		// need accountId, account taking money from, account putting money into,
//		// and the amount of the transaction
//		firstAcct -= amount;
//		secondAcct += amount;
//		return "Your now have $" + secondAcct + " in your ";
//	}

}
