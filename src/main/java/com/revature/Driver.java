package com.revature;

import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.RoleService;
import com.revature.services.UserService;

public class Driver {
	
	private static UserService uService = new UserService();
	private static RoleService rService = new RoleService();
	
	public static void main(String[] args) {
		
		List<Role> listRoles = rService.getAllRoles();
		
		for(Role r: listRoles) {
			System.out.println(r);
		}
		
		System.out.println("======================================");
		
		
		//Role r2 = new Role("Fury's secret bunker", "5468", "3rd Street", "Brooklyn", "NY", "10521", "USA");
		
		//rService.createHome(h2);
		
		List<User> listUsers = uService.getAllUsers();
		System.out.println(listUsers);
		System.out.println("======================================");
		
	}

}
