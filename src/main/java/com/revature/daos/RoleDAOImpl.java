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

			String sql = "SELECT * FROM user_role WHERE role_id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, roleId);

			ResultSet result = statement.executeQuery();

			List<Role> list = new ArrayList<>();
			
			Role role = new Role();


			while (result.next()) {
				role.setRoleId(result.getInt("role_id"));
				role.setRole(result.getString("role_title"));
				list.add(role);
			}

			return role;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Role findByRoleTitle(String role) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM user_role WHERE role_title = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, role);

			ResultSet result = statement.executeQuery();

			List<Role> list = new ArrayList<>();
			
			Role r = new Role();


			while (result.next()) {
				r.setRoleId(result.getInt("role_id"));
				r.setRole(result.getString("role_title"));
				list.add(r);
			}

			return r;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
