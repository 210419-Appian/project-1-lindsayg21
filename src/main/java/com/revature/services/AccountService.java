package com.revature.services;

import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.models.BalanceDTO;
import com.revature.models.TransferDTO;
import com.revature.models.User;

import java.util.List;

import com.revature.daos.AccountDAO;
import com.revature.daos.AccountDAOImpl;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.Account;

public class AccountService {

	private AccountDAO accDao = new AccountDAOImpl();
	private UserDAO uDao = new UserDAOImpl();
	private BalanceDTO balDTO = new BalanceDTO();

	public List<Account> getAllAccounts() {
		return accDao.findAll();
	}

	public Account findByAccountId(int accountId) {
		return accDao.findByAccountId(accountId);
	}

	public Account findByAccStatId(int accStatId) {
		return accDao.findByAccountStatusId(accStatId);
	}

	public Account findByUserId(int userId) {
		return accDao.findByUserId(userId);
	}

	public Account findOneUser(int userId) {
		return accDao.findByUserId(userId);
	}

	public double getAccountBalance(int accountId) {
		return accDao.findAccountBalance(accountId);
	}

	public boolean updateAccountStatus(Account account) {
		return accDao.updateAccountStatus(account);
	}

	
	public boolean withdraw(BalanceDTO balDTO) {
		Account account = accDao.findByAccountId(balDTO.getAccountId()); // account balance
		double amount = balDTO.getAmount(); // amount to withdraw
		double balance = account.getBalance();

		if (amount <= balance) {

			account.setBalance((balance - amount));
			accDao.updateAccount(account);
			// balance = account.getBalance();
			System.out.println("Your new account balance is $" + balance);

			return true;
		} else {
			return false;
		}

	}

	public boolean deposit(BalanceDTO balDTO) {

		Account account = accDao.findByAccountId(balDTO.getAccountId()); // account balance
		double amount = balDTO.getAmount(); // amount to withdraw
		double balance = account.getBalance();

		account.setBalance((balance + amount));
		accDao.updateAccount(account);
		// balance = account.getBalance();
		System.out.println("Your new account balance is $" + balance);

		return true;

	}
	
	//public boolean transfer(TransferDTO transDTO)
	//double
	//
	public boolean transferMoney(TransferDTO transDTO) {
		Account account1 = accDao.findByAccountId(transDTO.getAccountId1()); 	//account balance of acc 1
		Account account2 = accDao.findByAccountId(transDTO.getAccountId2());	//account balance of acc 2
		double amount = transDTO.getAmount(); 		// amount to withdraw from THIS account
		double balance1 = account1.getBalance();	//balance of first account	
		double balance2 = account2.getBalance();	//balance of second account

		if (amount <= balance1) {

			account1.setBalance((balance1 - amount));
			account2.setBalance((balance2 + amount));
			accDao.updateAccount(account1);
			accDao.updateAccount(account2);
			// balance = account.getBalance();
			//System.out.println("Your new account balance is $" + balance)
			return true;
			
		} else {
			return false;
		}
	}
//		double balanceOfWithdrawAccount = accDao.findAccountBalance(accountIdToWithdraw);
//
//		double balanceOfDepositAccount = accDao.findAccountBalance(accountIdToDeposit);
//
//		if (amount <= balanceOfWithdrawAccount) {
//			balanceOfWithdrawAccount -= amount;
//			balanceOfDepositAccount += amount;
//			System.out.println(
//					"Account number " + accountIdToWithdraw + " now has a balance of $" + balanceOfWithdrawAccount);
//			System.out.println(
//					"Account number " + accountIdToDeposit + " now has a balance of $" + balanceOfDepositAccount);
//		} else {
//			System.out.println(
//					"Account number " + accountIdToWithdraw + " doesn't have enough funds to complete this transfer.");
//			return false;
//		}
//
//		return true;
	

	// look here to finish addAccountAndUser() method
	public boolean createAccount(Account account) {
		return accDao.addAccount(account);
	}

	public boolean removeAccount(int accountId) {
		return accDao.deleteAccount(accountId);
	}

	public boolean updateAccount(Account account) {
		return accDao.updateAccount(account);
	}

}
