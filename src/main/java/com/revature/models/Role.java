package com.revature.models;

import java.io.Serializable;

public class Role implements Serializable {
	// keeps track of user permissions
	// Admin, Employee, Standard, or Premium

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int roleId; // primary key
	private String role; // not null, unique this will tell us what their account is??

	public Role() {
		super();
	}

	public Role(String role) {
		super();
		this.role = role;
	}
	
	public Role(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + roleId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}

	/*
	 * pretty sure this is Role logic i.e. Role services if(role == "Admin") {
	 * 
	 * } else if(role == "Employee") {
	 * 
	 * }
	 * 
	 * else if(role == "Standard") {
	 * 
	 * }
	 */
}
