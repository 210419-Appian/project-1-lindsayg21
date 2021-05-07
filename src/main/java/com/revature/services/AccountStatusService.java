package com.revature.services;

import java.util.List;

import com.revature.daos.AccountStatusDAO;
import com.revature.daos.AccountStatusDAOImpl;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.AccountStatus;

public class AccountStatusService {
	
	private AccountStatusDAO accStatusDao = new AccountStatusDAOImpl();
	
	public List<AccountStatus> getAllAccountStatuses(){
		return accStatusDao.findAll();
	}
	
	public AccountStatus findByAccountStatusId(int accStatusId) {
		return accStatusDao.findByAccountId(accStatusId);
	}
	
	public AccountStatus findByAccountStatusName(String accStatusName) {
		return accStatusDao.findByAccountStatusName(accStatusName);
	}

}
