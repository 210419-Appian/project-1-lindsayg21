package com.revature.services;

import java.util.List;

import com.revature.daos.AccountStatusDAO;
import com.revature.daos.AccountStatusDAOImpl;
import com.revature.daos.AccountTypeDAO;
import com.revature.daos.AccountTypeDAOImpl;
import com.revature.models.AccountType;

public class AccountTypeService {
	
	private AccountTypeDAO accTypeDao = new AccountTypeDAOImpl();

	public List<AccountType> getAllAccountTypes(){
		return accTypeDao.findAll();
	}
	
	public AccountType findByAccountTypeId(int typeId) {
		return accTypeDao.findByAccountTypeId(typeId);
	}
	
	public AccountType findByAccountTypeName(String accountTypeName) {
		return accTypeDao.findByAccountType(accountTypeName);
	}
	
}
