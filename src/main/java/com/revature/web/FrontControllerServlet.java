package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.AccountController;
import com.revature.controllers.UserController;

public class FrontControllerServlet extends HttpServlet{
	
	private String BaseURL = null;
	private UserController uControl = new UserController();
	private AccountController accControl = new AccountController();

	@Override
	public void init(ServletConfig config) throws ServletException{
		BaseURL = config.getInitParameter("BaseURL");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("application/json");
		
		resp.setStatus(400); 		//in case we have a bad request
		
		final String URL = req.getRequestURI().replace(BaseURL, "");
		//find out where request wants to go
		//we're dealing with a smaller String; get rid of what's in common
		
		System.out.println(URL);
		
		String[] sections = URL.split("/");
		//get each section of URL: /user/5...
		
		System.out.println(sections);
		
		//path variable -- way to pass info about request in the URL
		//usually a final / that takes variable input
		
		//need to include switch statements for Get, Post etc later!
		
		//switch on first section that comes through
		switch(sections[0]) {
			case "users":
				if(sections.length == 2) {
					int userId = Integer.parseInt(sections[1]);
					uControl.getUserByUserId(resp, userId);
					//this gives us all the user info; only admins and employees
				}
				else {
					uControl.getAllUsers(resp); 
					//send request to a User controller
				}
				break;
				
			case "accounts":
				if(sections.length == 2) {
					//PK for account: accountId
					int accountId = Integer.parseInt(sections[1]);
					accControl.getByAccountId(resp, accountId);
				}
				else {
					accControl.getAllAccounts(resp);
				}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req, resp);
	}
	
	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req, resp);
	}
	
	/*@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if(req.getMethod().equals("PATCH")){
			doPatch(req, resp);
		}
		else {
			super.service(req, resp);
		}
	}
	*/
	
}
