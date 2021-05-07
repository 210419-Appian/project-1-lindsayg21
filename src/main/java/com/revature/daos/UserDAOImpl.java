package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	
	private static RoleDAO rDao = new RoleDAOImpl();

	@Override
	public List<User> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM user_info;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<User> list = new ArrayList<>();
			
			while (result.next()) {
				User user = new User(
						result.getInt("user_id"), 
						result.getString("username"), 
						result.getString("pass_word"), 
						result.getString("first_name"), 
						result.getString("last_name"), 
						result.getString("email"),
						null
						);
				int roleId = result.getInt("user_role");
				if(roleId!=0) {
					user.setRole(rDao.findByRoleId(roleId)); 
				}
				list.add(user);
			}

			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUserId(int userId) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM user_info WHERE user_id = " +userId+";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			//List<User> list = new ArrayList<>();

			User user = null;

			while (result.next()) {
				user = new User(
						result.getInt("user_id"),
						result.getString("username"),
						result.getString("pass_word"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("email"),
						null
						);
				int roleId = result.getInt("user_role");
				//if(roleId != 0) {
					user.setRole(rDao.findByRoleId(roleId));
				//}
				//list.add(user);
			}

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addUser(User user) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO user_info (username, pass_word, first_name, last_name, email, user_role)"
					+ "VALUES(?,?,?,?,?,?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			//statement.setString(++index, user.getRole().getRole());
			
			if(user.getRole() != null) {
				statement.setString(++index, user.getRole().getRole());		
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
	public void updateUser(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE user_info SET username = ?, pass_word = ?, first_name = ?, last_name = ?, email = ?, user_role = ?"
					+ "WHERE user_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			//need statement for Role!!!
			statement.setInt(++index, user.getUserId());	//should be LAST, it's our last parameter
			//return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		//return null;
	}

	@Override
	public User findByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM user_info WHERE username = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			
			User user = null;
			
			while(result.next()) {
				user = new User(
					result.getInt("user_id"),
					result.getString("username"),
					result.getString("pass_word"),
					result.getString("first_name"),
					result.getString("last_name"),
					result.getString("email"),
					null
					);
				int roleId = result.getInt("user_role");
				if(roleId != 0) {
					user.setRole(rDao.findByRoleId(roleId));
				}
			}
			return user;	
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteUser(int userId) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql1 = "DELETE FROM account WHERE account_user = " + userId + ";";
			String sql2 = "DELETE FROM user_info WHERE user_id = "+ userId + ";";
			
			Statement statement = conn.createStatement();
			
			statement.execute(sql1);
			statement.execute(sql2);
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}

