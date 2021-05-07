package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserController {

	private UserService uService = new UserService();
	private ObjectMapper om = new ObjectMapper();
	
	public void getAllUsers(HttpServletResponse resp) throws IOException{
		
		List<User> userList = uService.getAllUsers();
		
		String json = om.writeValueAsString(userList);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		
	}
	
	public void getUserByUsername(HttpServletResponse resp, String string) throws IOException{
		
		User user = uService.findByUsername(string);
		
		String json = om.writeValueAsString(user);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		
	}
	
	public void getUserByUserId(HttpServletResponse resp, int userId) throws IOException{
		
		User user = uService.findByUserId(userId);
		
		String json = om.writeValueAsString(user);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
	}
	
	//NEED to finish this one!
	//needs to go into UserService class
	public void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		BufferedReader reader = req.getReader();
		
		StringBuilder sb = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
	}
	
}
