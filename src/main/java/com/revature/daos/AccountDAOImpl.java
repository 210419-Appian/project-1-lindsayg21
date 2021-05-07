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

public class AccountDAOImpl implements AccountDAO {

	private static UserDAO uDao = new UserDAOImpl();
	private static AccountStatusDAO asDao = new AccountStatusDAOImpl();
	private static AccountTypeDAO atDao = new AccountTypeDAOImpl();

	@Override
	//done!
	public List<Account> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<Account> list = new ArrayList<>();

			Account account = null;

			while (result.next()) {
				account = new Account(
						result.getInt("account_id"), 
						result.getDouble("account_balance"), 
						null, 
						null,
						null);
				int accountStatus = result.getInt("account_status");
				account.setStatus(asDao.findByAccountId(accountStatus));
				int accountType = result.getInt("account_type");
				account.setType(atDao.findByAccountTypeId(accountType));
				int accountUser = result.getInt("account_user");
				account.setUser(uDao.findByUserId(accountUser));

				/*
				 * if(accountStatus != 0) {
				 * account.setStatus(asDao.findByAccountId(accountStatus)); } list.add(account);
				 * if(accountType != 0) {
				 * account.setType(atDao.findByAccountTypeId(accountType)); } list.add(account);
				 * if(accountUser != 0l) { account.setUser(uDao.findByUserId(accountUser)); }
				 */
				
				list.add(account);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//done!
	public Account findByAccountId(int accountId) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account WHERE account_id = " + accountId + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			// List<Account> list = new ArrayList<>();

			Account account = null;

			while (result.next()) {
				account = new Account(
						result.getInt("account_id"), 
						result.getDouble("account_balance"), 
						null,
						null,
						null);

				int accountStatus = result.getInt("account_status");
				account.setStatus(asDao.findByAccountId(accountStatus));
				int accountType = result.getInt("account_type");
				account.setType(atDao.findByAccountTypeId(accountType));
				int accountUser = result.getInt("account_id");
				account.setUser(uDao.findByUserId(accountUser));

				// account.setStatus(asDao.findByAccountId(accountStatus));

				// list.add(account);
				// account.setType(atDao.findByAccountTypeId(accountType));

				// list.add(account);
				// account.setUser(uDao.findByUserId(accountUser));

				// list.add(account);
				// need return type of Account
				// return account;
			}

			return account;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//make a list???
	public Account findByUserId(int userId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account WHERE account_user = " + userId + ";";
			
			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			// List<Account> list = new ArrayList<>();

			Account account = null;

			while (result.next()) {
				account = new Account(
						result.getInt("account_id"), 
						result.getDouble("account_balance"), 
						null,
						null,
						null);

				int accountStatus = result.getInt("account_status");
				account.setStatus(asDao.findByAccountId(accountStatus));
				int accountType = result.getInt("account_type");
				account.setType(atDao.findByAccountTypeId(accountType));
				int accountUser = result.getInt("account_id");
				account.setUser(uDao.findByUserId(accountUser));
			}
			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//done! 
	public double findAccountBalance(int accountId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT account_balance FROM account WHERE account_id = " + accountId + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			result.next();
			double accountBalance = result.getDouble("account_balance");
			// here: getting result from DB, from "account_balance", turning into a double.
			

			return accountBalance;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public boolean addAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO account (account_balance, account_status, account_type, account_user)"
					+ "VALUES(?,?,?,?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setDouble(++index, account.getBalance());
//			statement.setString(++index, null);
//			statement.setString(++index, null);
//			statement.setString(++index, null);

			if (account.getStatus() != null) {
				statement.setString(++index, account.getStatus().getStatus());
			} else {
				statement.setString(++index, null);
			}
			if (account.getType() != null) {
				statement.setString(++index, account.getType().getType());
			} else {
				statement.setString(++index, null);
			}
			if (account.getUser() != null) {
				statement.setString(++index, account.getUser().toString());
			} else {
				statement.setString(++index, null);
			}

			statement.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	// not done
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount(int accountId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "DELETE FROM account WHERE account_id = " + accountId + ";";
			
			Statement statement = conn.createStatement();
			
			statement.execute(sql);
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
