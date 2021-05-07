package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.services.AccountService;

public class AccountController {
	
	private AccountService accService = new AccountService();
	private ObjectMapper om = new ObjectMapper();
	
	public void getAllAccounts(HttpServletResponse resp) throws IOException{
		
		List<Account> accountList = accService.getAllAccounts();
		
		String json = om.writeValueAsString(accountList);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		//means OK
		
	}
	
	public void getByAccountId(HttpServletResponse resp, int accountId) throws IOException{
		
		Account account = accService.findByAccountId(accountId);
		String json = om.writeValueAsString(account);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
	}
	
	//need to add an account; BUT can also add an account
	//to someone that already has one, OR need to create a 
	//user AND account

}
