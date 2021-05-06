package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountType;
import com.revature.utils.ConnectionUtil;

public class AccountTypeDAOImpl implements AccountTypeDAO{

	@Override
	public List<AccountType> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account_type;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<AccountType> list = new ArrayList<>();

			while (result.next()) {
				AccountType accntType = new AccountType();
				accntType.setTypeId(result.getInt("type_id"));
				accntType.setType(result.getString("accnt_type"));
				list.add(accntType);
			}

			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AccountType findByAccountTypeId(int typeId) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account_type WHERE type_id = "+typeId+";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			//List<AccountType> list = new ArrayList<>();
			
			//AccountType accntType = new AccountType();
			AccountType accntType = null;
			
			while (result.next()) {
				accntType = new AccountType(
						result.getInt("type_id"),
						result.getString("accnt_type")
				);
				//list.add(accntType);
			}

			return accntType;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AccountType findByAccountType(String type) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account_type WHERE accnt_type = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, type);

			ResultSet result = statement.executeQuery();
			
			AccountType accntType = new AccountType();

			while (result.next()) {
				accntType.setTypeId(result.getInt("type_id"));
				accntType.setType(result.getString("accnt_type"));
			}

			return accntType;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
