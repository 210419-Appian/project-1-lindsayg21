package com.revature.daos;

import java.util.List;

import com.revature.models.AccountType;

public interface AccountTypeDAO {

	public List<AccountType> findAll();
	public AccountType findByAccountTypeId(int typeId);
	public AccountType findByAccountType(String type);
	
}
