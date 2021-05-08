package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
//import com.revature.models.User;
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
	
	public void addAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		BufferedReader reader = req.getReader();
		
		StringBuilder sb = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		Account account = om.readValue(body, Account.class);
		//Account object coming out of our request
		
		if(accService.createAccount(account)) {
			resp.setStatus(201); //Account created!
		}
		else {
			resp.setStatus(406);	//maybe 400!
		}
	}
	
	public void withdrawFromAccount(HttpServletRequest req, HttpServletResponse resp, int accountId, double amount) throws IOException{
		
		if(accService.withdraw(accountId, amount)) {
			resp.setStatus(202); //success
		}
		else {
			resp.setStatus(400);
		}
		
	}

}
