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

		PrintWriter pw = resp.getWriter();
		// stuff that will get sent back to client

		if (uService.checkLoginCredentials(uDTO)) {

			pw.print(om.writeValueAsString(uDao.findByUsername(uDTO.getUsername())));
			HttpSession ses = req.getSession(); // creates cookie!
			ses.setAttribute("username", uDTO.getUsername());

			resp.setStatus(200);

			pw.print(uDTO.getUsername() + ", you have successfully logged in!");

		} else {
			pw.print("Invalid Credentials");
			resp.setStatus(400);

		}

	}

	public static void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There was no user logged into the session.");
			return;
		}

		HttpSession ses = req.getSession();

		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);
		

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

			if (uService.register(newUser)) {
				pw.print(om.writeValueAsString(uDao.findByUsername(newUser.getUsername())));
				resp.setStatus(201);
			} else {
				resp.setStatus(400);
				pw.print("Invalid fields");
			}
		} else {
			pw.print("The requested action is not permitted");
			resp.setStatus(401);
		}

	}

	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// HttpSession ses = req.getSession(false);

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There was no user logged into the session.");
			return;
		}

		HttpSession ses = req.getSession();

		if (ses != null) {
			pw.print("You have successfully logged out " + ses.getAttribute("username"));
			ses.invalidate();
			resp.setStatus(200);
			// pw.print("You have successfully logged out " + ses.getAttribute("username"));
			// session was already invalidated!!

			// return;
		}

		else {
			resp.setStatus(400);
			pw.print("There was no user logged into the session.");
		}
	}

	public void getAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// only admins and employees

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			resp.setStatus(400);
			return;
		}

		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);

		if (user.getRole().getRoleId() == 1) {

			List<User> userList = uService.getAllUsers();

			String json = om.writeValueAsString(userList);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);
			// set as 200 bc OK
		} else if (user.getRole().getRoleId() == 2) {
			List<User> userList = uService.getAllUsers();

			String json = om.writeValueAsString(userList);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);
		} else {
			pw.print("\"message\": Unauthorized Request.");
			resp.setStatus(400);
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

	public void getUserByUserId(HttpServletRequest req, HttpServletResponse resp, int userId) throws IOException {

		// only admins, employees, and the user

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			return;
		}

		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);

		if (user.getRole().getRoleId() == 1 || user.getRole().getRoleId() == 2) {

			User uServ = uService.findByUserId(userId);

			String json = om.writeValueAsString(uServ);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);
			// set as 200 bc OK

//		} else if (user.getRole().getRoleId() == 2) {
//			User uServ = uService.findByUserId(userId);
//
//			String json = om.writeValueAsString(uServ);
//			System.out.println(json);
//			pw.print(json);
//			resp.setStatus(200);
//
		} else if (user.getUserId() == userId) {
			User uServ = uService.findByUserId(userId);

			String json = om.writeValueAsString(uServ);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);

		} else {
			pw.print("\"message\": Unauthorized Request.");
			resp.setStatus(400);
		}
		
	}

	public void putUser(HttpServletRequest req, HttpServletResponse resp, int userId) throws IOException {

		PrintWriter pw = resp.getWriter();
		// how we can print messages

		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			return;
		}

		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);
		// could also find by ID?

		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);

		User user1 = om.readValue(body, User.class);

		// if Admin, do this.
		if (user.getRole().getRoleId() == 1 || user.getRole().getRoleId() == 2) {
			
			if (uService.updateUser(user1)) {
				resp.setStatus(200);
			} else {
				resp.setStatus(400);
			}

			User uServ = uService.findByUserId(userId);

			String json = om.writeValueAsString(uServ);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);
		}
			// set as 200 bc OK
			
//			
//			if (uService.updateUser(user1)) {
//				resp.setStatus(200);
//			} else {
//				resp.setStatus(400);
//			}
//			User uServ = uService.findByUserId(userId);
//
//			String json = om.writeValueAsString(uServ);
//			System.out.println(json);
//			pw.print(json);
//			resp.setStatus(200);
//
//		} 
		else if (user.getUserId() == userId) {
			
			if (uService.updateUser(user1)) {
				resp.setStatus(200);
			} else {
				resp.setStatus(400);
			}
			User uServ = uService.findByUserId(userId);

			String json = om.writeValueAsString(uServ);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);

		} else {
			pw.print("Unauthorized Request.");
			resp.setStatus(400);
		}

	}

	public void deleteUser(HttpServletRequest req, HttpServletResponse resp, String deletion) throws IOException {
		
		PrintWriter pw = resp.getWriter();
		
		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			return;
		}
		
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
