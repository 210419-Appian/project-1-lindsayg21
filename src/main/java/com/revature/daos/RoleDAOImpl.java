package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

public class RoleDAOImpl implements RoleDAO {

	@Override
	public List<Role> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM user_role;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			List<Role> list = new ArrayList<>();

			while (result.next()) {
				Role role = new Role();
				role.setRoleId(result.getInt("role_id"));
				role.setRole(result.getString("role_title"));
				list.add(role);
				//retrieving roles already in database
			}

			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Role findByRoleId(int roleId) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM user_role WHERE role_id = " + roleId + ";";
			
			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);
			
			Role role = new Role();

			while (result.next()) {
				role.setRoleId(result.getInt("role_id"));
				role.setRole(result.getString("role_title"));
			}

			return role;	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Role findByRoleTitle(String userRole) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM user_role WHERE role_title = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, userRole);

			ResultSet result = statement.executeQuery();
			
			Role role = new Role();

			while (result.next()) {
				role.setRoleId(result.getInt("role_id"));
				role.setRole(result.getString("role_title"));
			}

			return role;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
