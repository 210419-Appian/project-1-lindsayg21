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
		
		resp.setStatus(400); 		//incase bad request
		
		final String URL = req.getRequestURI().replace(BaseURL, "");
		
		System.out.println(URL);
		
		String[] sections = URL.split("/");
		
		System.out.println(sections);
		
		//need to include switch statements for Get, Post etc later!
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
		doGet(req, resp);
	}
	*/
	
}
