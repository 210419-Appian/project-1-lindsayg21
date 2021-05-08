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
//not a servlet

	private UserService uService = new UserService();
	private ObjectMapper om = new ObjectMapper();

	public void getAllUsers(HttpServletResponse resp) throws IOException {
		//only admins and employees

		List<User> userList = uService.getAllUsers();

		String json = om.writeValueAsString(userList);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		// set as 200 bc OK

	}

	public void getUserByUsername(HttpServletResponse resp, String username) throws IOException {

		User user = uService.findByUsername(username);

		String json = om.writeValueAsString(user);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);

	}

	public void getUserByUserId(HttpServletResponse resp, int userId) throws IOException {

		User user = uService.findByUserId(userId);

		String json = om.writeValueAsString(user);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
	}

	// NEED to finish this one!
	// needs to go into UserService class
	public void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);

		User user = om.readValue(body, User.class);
		// User object coming out of our request

		if (uService.createUser(user)) {
			resp.setStatus(201); // User created!
		} else {
			resp.setStatus(406); // maybe 400!
		}

	}

	public void putUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);

		User user = om.readValue(body, User.class);
		
		if(uService.updateTotalUser(user)) {
			resp.setStatus(200);
		}
		else {
			resp.setStatus(400);
		}
	}
	
	public void patchUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);

		User user = om.readValue(body, User.class);
		
		if(uService.updatePartialUser(user)) {
			resp.setStatus(200);
		}
		else {
			System.out.println("Invalid fields");
			resp.setStatus(400);
		}
	}
	
	public void deleteUser(HttpServletResponse resp, String deletion) throws IOException{
		try {
			int userId = Integer.parseInt(deletion);
			if(uService.removeUser(userId)) {
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

}
