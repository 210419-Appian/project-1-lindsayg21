package com.revature.daos;

import java.util.List;

import com.revature.models.AccountStatus;

public interface AccountStatusDAO {
	
	public List<AccountStatus> findAll();
	public AccountStatus findByAccountId(int statusID);
	public AccountStatus findByAccountStatusName(String status);

}
