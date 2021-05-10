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
	// done!
	public List<Account> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<Account> list = new ArrayList<>();

			Account account = null;

			while (result.next()) {
				account = new Account(result.getInt("account_id"), result.getDouble("account_balance"), null, null,
						null);
				int accountStatus = result.getInt("account_status");
				account.setStatus(asDao.findByAccountId(accountStatus));
				int accountType = result.getInt("account_type");
				account.setType(atDao.findByAccountTypeId(accountType));
				int accountUser = result.getInt("account_user");
				account.setUser(uDao.findByUserId(accountUser));

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
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account WHERE account_id = " + accountId + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			// List<Account> list = new ArrayList<>();

			Account account = null;

			while (result.next()) {
				account = new Account(result.getInt("account_id"), result.getDouble("account_balance"), null, null,
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
	public Account findByUserId(int userId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account WHERE account_user = " + userId + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			// List<Account> list = new ArrayList<>();

			Account account = null;

			while (result.next()) {
				account = new Account(result.getInt("account_id"), result.getDouble("account_balance"), null, null,
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

	public Account findByAccountStatusId(int accStatId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account WHERE account_status = " + accStatId + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			// List<Account> list = new ArrayList<>();

			Account account = null;

			while (result.next()) {
				account = new Account(result.getInt("account_id"), result.getDouble("account_balance"), null, null,
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
	// done!
	public double findAccountBalance(int accountId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT account_balance FROM account WHERE account_id = " + accountId + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			result.next();

			double accountBalance = result.getDouble("account_balance");
			
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
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			statement.setInt(++index, account.getUser().getUserId());

			statement.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE account "
					+ "SET account_balance = ?, account_status = ?, "
					+ "account_type = ?, account_user = ? "
					+ "WHERE account_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setDouble(++index, account.getBalance());
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			statement.setInt(++index, account.getUser().getUserId());
			statement.setInt(++index, account.getAccountId());
			
			statement.execute();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAccountStatus(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE account"
						+ "SET accnt_status = ?" + "WHERE account_id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getAccountId());

			statement.execute(sql);

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAccount(int accountId) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM account WHERE account_id = " + accountId + ";";

			Statement statement = conn.createStatement();

			statement.execute(sql);

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addAccountAndUser(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = 	"BEGIN;"
							+ "INSERT INTO user_info (username, pass_word, first_name, last_name, email, user_role)"
							+ "VALUES(?,?,?,?,?,?);"
							+ "INSERT INTO account (account_balance, account_status, account_type, account_user)"
							+ "VALUES(?,?,?,?);"
							+ "COMMIT;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			User user = account.getUser();

			int index = 0;
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			statement.setInt(++index, user.getRole().getRoleId());
			statement.setDouble(++index, account.getBalance());
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			statement.setInt(++index, account.getUser().getUserId());

			statement.execute();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
