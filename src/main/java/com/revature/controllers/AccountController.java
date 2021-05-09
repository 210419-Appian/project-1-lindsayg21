package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.BalanceDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
//import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserService;

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
	
	public void getAccountByStatusId(HttpServletResponse resp, int acctStatId) throws IOException{
		Account account = accService.findByUserId(acctStatId);
		String json = om.writeValueAsString(account);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
	}
	
	public void getAccountByUserId(HttpServletResponse resp, int userId) throws IOException{
		Account account = accService.findByUserId(userId);
		String json = om.writeValueAsString(account);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
	}
	
	/*public void withdraw(HttpServletRequest req, HttpServletResponse resp) {
		
		BalanceDTO balDTO = new BalanceDTO();
		UserService uService = new UserService();
		UserDAOImpl uDao = new UserDAOImpl();

		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);

		balDTO = om.readValue(body, BalanceDTO.class);

		PrintWriter out = resp.getWriter();
		// stuff that will get sent back to client

		// next the userDTO should be passed to the service layer to check if the
		if (accService.withdraw(balDTO)) {
			out.print(om.writeValueAsString(accService.getAccountBalance(balDTO.getAccountId())));
			HttpSession ses = req.getSession(); // creates cookie!
			ses.setAttribute("username", balDTO);
			//do we start session?
			//this is totally wrongggg

			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}

	}*/
	
	/*public void withdrawFromAccount(HttpServletRequest req, HttpServletResponse resp, int accountId, double amount) throws IOException{
		
		if(accService.withdraw(accountId, amount)) {
			resp.setStatus(202); //success
		}
		else {
			resp.setStatus(400);
		}
		
	}*/
	
	public void deleteAccount(HttpServletResponse resp, String deletion) throws IOException{
		try {
			int accountId = Integer.parseInt(deletion);
			if(accService.removeAccount(accountId)) {
				resp.setStatus(204);
			}
			else {
				resp.setStatus(400);
			}
		} catch(NumberFormatException e) {
			e.printStackTrace();
			resp.setStatus(418);
		}
	}
	
	public void putAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);

		Account account = om.readValue(body, Account.class);
		
		if(accService.updateAccount(account)) {
			resp.setStatus(200);
		}
		else {
			resp.setStatus(400);
		}
	}

}
