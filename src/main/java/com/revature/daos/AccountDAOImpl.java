package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO{
	
	private static UserDAO uDao = new UserDAOImpl();
	private static AccountStatusDAO asDao = new AccountStatusDAOImpl();
	private static AccountTypeDAO atDao = new AccountTypeDAOImpl();

	@Override
	public List<Account> findAll() {		
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<Account> list = new ArrayList<>();

			while (result.next()) {
				Account account = new Account(
						result.getInt("account_id"), 
						result.getDouble("account_balance"), 
						null, 
						null,
						null
						);
				String accountStatus = result.getString("account_status");
				String accountType = result.getString("account_type");
				String accountUser = result.getString("account_user");
				
				if(accountStatus != null) {
					//account.setStatus(asDao.findByRoleTitle(roleName));
				}
				list.add(account);
				if(accountType != null) {
					//account.setType(atDao.findByRoleTitle(roleName));
				}
				list.add(account);
				if(accountUser != null) {
					//account.setUser(uDao.findByUserId(accountUser)); 
				}
				list.add(account);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Account findByAccountId(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Account findByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean addAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double findAccountBalance(double balance) {
		// TODO Auto-generated method stub
		return 0;
	}

}
