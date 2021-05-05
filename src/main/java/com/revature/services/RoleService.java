package com.revature.services;

import java.util.List;

import com.revature.daos.RoleDAO;
import com.revature.daos.RoleDAOImpl;
import com.revature.models.Role;

public class RoleService {

	private RoleDAO rDao = new RoleDAOImpl();

	public List<Role> getAllRoles() {

		List<Role> list = rDao.findAll();
		
		return list;

	}
	
	public Role findByRole(String role) {
		return rDao.findByRoleTitle(role);
	}
	
	public Role findByRoleId(int roleId) {
		return rDao.findByRoleId(roleId);
	}

}
