package com.revature;

import java.util.List;

import com.revature.models.*;
import com.revature.services.AccountService;
import com.revature.services.AccountStatusService;
import com.revature.services.AccountTypeService;
import com.revature.services.RoleService;
import com.revature.services.UserService;

public class Driver {
	
	private static UserService uService = new UserService();
	private static RoleService rService = new RoleService();
	private static AccountStatusService accStatusService = new AccountStatusService();
	private static AccountTypeService accTypeService = new AccountTypeService();
	private static AccountService accService = new AccountService();
	
	public static void main(String[] args) {
		
		System.out.println("List of Roles");
		List<Role> listRoles = rService.getAllRoles();
		
		for(Role r: listRoles) {
			System.out.println(r);
		}
		
		System.out.println("============================================");
		
		System.out.println("Find Role by role id");
		Role r1 = rService.findByRoleId(1);
		System.out.println(r1);
		
		Role r2 = rService.findByRoleId(2);
		System.out.println(r2);
		
		Role r3 = rService.findByRoleId(3);
		System.out.println(r3);
		
		System.out.println("============================================");
		
		//Role r2 = new Role("Fury's secret bunker", "5468", "3rd Street", "Brooklyn", "NY", "10521", "USA");
		
		//rService.createHome(h2);
		
		System.out.println("List of Users");
		List<User> listUsers = uService.getAllUsers();
		System.out.println(listUsers);
		
		System.out.println("============================================");
		
		System.out.println("Find User by user id");
		User u1 = uService.findByUserId(1);
		System.out.println(u1);
		
		System.out.println();
		
		User u2 = uService.findByUserId(2);
		System.out.println(u2);
		
		System.out.println();
		
		User u3 = uService.findByUserId(5);
		System.out.println(u3);
		
		System.out.println();
		
		User u4 = uService.findByUserId(6);
		System.out.println(u4);
		
		System.out.println("============================================");
		
		System.out.println("List of Account Statuses");
		List<AccountStatus> listAccountStatuses = accStatusService.findAll();
		System.out.println(listAccountStatuses);
		
		System.out.println("============================================");
		
		System.out.println("Find Account Status by status id");
		AccountStatus accStatus = accStatusService.findByAccountStatusId(2);
		System.out.println(accStatus);
		
		System.out.println("============================================");
		
		System.out.println("Find Account Status by status name");
		AccountStatus accStatus2 = accStatusService.findByAccountStatusName("Open");
		System.out.println(accStatus2);
		
		System.out.println("============================================");
		
		System.out.println("List of Account Types");
		List<AccountType> listAccountTypes = accTypeService.getAllAccountTypes();
		System.out.println(listAccountTypes);
		
		System.out.println("============================================");
		
		System.out.println("Find Account Type by type id");
		AccountType accType = accTypeService.findByAccountTypeId(1);
		System.out.println(accType);
		
		System.out.println("===========================================");
		
		System.out.println("Find Account Type by type name");
		AccountType accType2 = accTypeService.findByAccountTypeName("Savings");
		System.out.println(accType2);
		
		System.out.println("===========================================");

		System.out.println("List of Accounts");
		List<Account> listAccounts = accService.getAllAccounts();
		
		for(Account a: listAccounts) {
			System.out.println(a);
		}
		
		//List<Account> listAccounts = accService.createAccount();
		//System.out.println(listAccounts);
		
		System.out.println("============================================");
		
		System.out.println("Find Account by account id");
		Account acc1 = accService.findByAccountId(3);
		System.out.println(acc1);
		
		//Account acc2 = accService.findByAccountId(1);
		//System.out.println(acc2);*/
		
	}

}
