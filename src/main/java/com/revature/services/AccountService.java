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

	public Account findByUserId(int userId) {
		return accDao.findByUserId(userId);
	}
	
	public boolean createAccount(Account account) {
		return accDao.addAccount(account);
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
