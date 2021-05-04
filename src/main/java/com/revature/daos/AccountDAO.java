package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {

	public List<Account> findAll();
	public Account findByAccountId(int accountId);
	public Account findByUserId(int userId); 		//added later
	public boolean addAccount(Account account);
	
}
