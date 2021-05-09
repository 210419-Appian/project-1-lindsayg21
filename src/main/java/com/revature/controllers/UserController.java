package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.RoleDAOImpl;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.UserService;

public class UserController {
//not a servlet

	private static UserService uService = new UserService();
	private static UserDAOImpl uDao = new UserDAOImpl();
	private static RoleDAOImpl rDao = new RoleDAOImpl();
	private static ObjectMapper om = new ObjectMapper();

	public static void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		UserDTO uDTO = new UserDTO();
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

		uDTO = om.readValue(body, UserDTO.class);

		PrintWriter out = resp.getWriter();
		// stuff that will get sent back to client

		// next the userDTO should be passed to the service layer to check if the
		if (uService.checkLoginCredentials(uDTO)) {
			out.print(om.writeValueAsString(uDao.findByUsername(uDTO.username)));
			HttpSession ses = req.getSession(); // creates cookie!
			ses.setAttribute("username", uDTO.username);

			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}

	}

	public static void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (req.getSession(false) == null) {
			return;
		}

		HttpSession ses = req.getSession();

		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);
		// rDao = new RoleDAOImpl();

		if (user.getRole().getRoleId() == 1) {
			BufferedReader reader = req.getReader();

			StringBuilder sb = new StringBuilder();

			String line = reader.readLine();

			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			String body = new String(sb);

			User newUser = om.readValue(body, User.class);
			PrintWriter out = resp.getWriter();

			if (uService.register(newUser)) {
				out.print(om.writeValueAsString(uDao.findByUsername(newUser.getUsername())));
				resp.setStatus(201);
			} else {
				resp.setStatus(400);
			}
		} 
		else {
			resp.setStatus(400);
		}

	}

	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// HttpSession ses = req.getSession(false);

		if (req.getSession(false) == null) {
			return;
		}

		HttpSession ses = req.getSession();

		if (ses != null) {
			ses.invalidate();
			resp.setStatus(200);
		}

		else {
			resp.setStatus(400);
		}
	}

	public void getAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// only admins and employees

		if(req.getSession(false) == null) {
			return;
		}
		
		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);
		
		if(user.getRole().getRoleId() == 1) {
		
		List<User> userList = uService.getAllUsers();

		String json = om.writeValueAsString(userList);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		resp.setStatus(200);
		// set as 200 bc OK
		}
		else {
			resp.setStatus(400);
			//need to add message
		}
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
	/*public void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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

	}*/

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

		if (uService.updateUser(user)) {
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}
	}

	/*
	 * public void patchUser(HttpServletRequest req, HttpServletResponse resp)
	 * throws IOException {
	 * 
	 * BufferedReader reader = req.getReader();
	 * 
	 * StringBuilder sb = new StringBuilder();
	 * 
	 * String line = reader.readLine();
	 * 
	 * while (line != null) { sb.append(line); line = reader.readLine(); }
	 * 
	 * String body = new String(sb);
	 * 
	 * User user = om.readValue(body, User.class);
	 * 
	 * if (uService.updatePartialUser(user)) { resp.setStatus(200); } else {
	 * System.out.println("Invalid fields"); resp.setStatus(400); } }
	 */

	public void deleteUser(HttpServletResponse resp, String deletion) throws IOException {
		try {
			int userId = Integer.parseInt(deletion);
			if (uService.removeUser(userId)) {
				resp.setStatus(204);
			} else {
				resp.setStatus(400);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resp.setStatus(418);
		}
	}

}
