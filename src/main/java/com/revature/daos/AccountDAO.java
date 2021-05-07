package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {

	public List<Account> findAll();						//
	public Account findByAccountId(int accountId); 		//
	//should this ^ have return type of List?
	public Account findByUserId(int userId); 			//
	public double findAccountBalance(int accountId);	//
	public boolean addAccount(Account account);			//
	public boolean updateAccount(Account account);		//
	public boolean deleteAccount(int accountId);
	
}
