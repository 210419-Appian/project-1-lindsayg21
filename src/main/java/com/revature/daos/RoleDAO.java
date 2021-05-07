package com.revature.daos;

import java.util.List;

import com.revature.models.Role;

public interface RoleDAO {

	public List<Role> findAll();
	public Role findByRoleId(int roleId);
	public Role findByRoleTitle(String role); 		
}
