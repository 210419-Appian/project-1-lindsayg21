package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {

	public List<Account> findAll();
	public Account findById(int accountId);
	public boolean addAccount(Account account);
	
}
