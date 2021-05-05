package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountStatus;
import com.revature.utils.ConnectionUtil;

public class AccountStatusDAOImpl implements AccountStatusDAO{

	@Override
	public List<AccountStatus> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account_status;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<AccountStatus> list = new ArrayList<>();

			while (result.next()) {
				AccountStatus accntStatus = new AccountStatus();
				accntStatus.setStatusId(result.getInt("status_id"));
				accntStatus.setStatus(result.getString("accnt_status"));
				list.add(accntStatus);
			}

			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AccountStatus findByAccountId(int statusId) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account_status WHERE status_id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, statusId);

			ResultSet result = statement.executeQuery(sql);

			List<AccountStatus> list = new ArrayList<>();
			
			AccountStatus accntStatus = new AccountStatus();
			
			while (result.next()) {
				accntStatus.setStatusId(result.getInt("status_id"));
				accntStatus.setStatus(result.getString("accnt_status"));
				list.add(accntStatus);
			}

			return accntStatus;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AccountStatus findByAccountStatusName(String status) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account_status WHERE accnt_status = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, status);

			ResultSet result = statement.executeQuery();
			
			AccountStatus accntStatus = new AccountStatus();

			while (result.next()) {
				accntStatus.setStatusId(result.getInt("statuse_id"));
				accntStatus.setStatus(result.getString("accnt_status"));
			}

			return accntStatus;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
