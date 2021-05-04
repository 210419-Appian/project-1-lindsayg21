package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String URI = req.getRequestURI();
		System.out.println(URI);
		
		//Print writers allow us to put text into the body of the response. 
		PrintWriter pw = resp.getWriter();
		pw.print("<h1>Hello from your doGet Method!</h1>");
		
	}

}
