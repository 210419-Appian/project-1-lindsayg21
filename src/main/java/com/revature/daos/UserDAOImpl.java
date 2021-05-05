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
				String roleName = result.getString("user_role");
				if(roleName!=null) {
					user.setRole(rDao.findByRoleTitle(roleName)); //??
				}
				list.add(user);
			}
			
//			while (result.next()) {
//				User user = new User();
//				user.setUserId(result.getInt("user_id"));
//				user.setUsername(result.getString("username"));
//				user.setPassword(result.getString("pass_word"));
//				user.setFirstName(result.getString("first_name"));
//				user.setLastName(result.getString("last_name"));
//				user.setEmail(result.getString("email"));
//				user.setRole(result.getString("user_role"));
//				list.add(user);
//			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUserId(int userId) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM user_role WHERE user_id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, userId);

			ResultSet result = statement.executeQuery();

			List<User> list = new ArrayList<>();

			User user = new User();

			while (result.next()) {
				user.setUserId(result.getInt("user_id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("pass_word"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				// user.setRole(result.getString("user_role"));
				list.add(user);
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
			//statement.setString(++index, user.getRole());
			
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
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
