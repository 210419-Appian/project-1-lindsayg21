package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.AccountController;
import com.revature.controllers.RoleController;
import com.revature.controllers.UserController;
import com.revature.models.UserDTO;

public class FrontControllerServlet extends HttpServlet {

	private String BaseURL = null;
	private RoleController rControl = new RoleController();
	private UserController uControl = new UserController();
	private AccountController accControl = new AccountController();
	private UserDTO uDTo = new UserDTO();

	@Override
	public void init(ServletConfig config) throws ServletException {
		BaseURL = config.getInitParameter("BaseURL");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		resp.setStatus(400); // in case we have a bad request

		final String URL = req.getRequestURI().replace(BaseURL, "");
		// find out where request wants to go
		// we're dealing with a smaller String; get rid of what's in common

		System.out.println(URL);

		String[] sections = URL.split("/");
		// get each section of URL. Can parse elements later

		System.out.println(sections);

		switch (sections[0]) {
		
		case "login":
			if (req.getMethod().equals("POST")) {
				UserController.login(req, resp);
			}
			break;
		case "logout":
			if (req.getMethod().equals("POST")) {
				UserController.logout(req, resp);
			}
			break;
		case "roles":
			if (req.getMethod().equals("GET")) {
				if (sections.length == 2) {
					int roleId = Integer.parseInt(sections[1]);
					rControl.getRoleByRoleId(resp, roleId);
				} else {
					rControl.getAllRoles(resp);
				}
			}
			break;
		case "register":
			if (req.getMethod().equals("POST")) {
				UserController.register(req, resp);
			}
		case "users":
			if (req.getMethod().equals("GET")) {
				if (sections.length == 2) {
					int userId = Integer.parseInt(sections[1]);
					uControl.getUserByUserId(req, resp, userId);
				}
				else if (sections.length == 3 && sections[1].equals("owner")) {
					String str = sections[2];
					uControl.getUserByUsername(resp, str);
				} else {
					uControl.getAllUsers(req, resp);
				}
			}

			else if (req.getMethod().equals("PUT") && sections.length == 2) {
				int userId = Integer.parseInt(sections[1]);
				uControl.putUser(req, resp, userId);
				// updating a User
			}

			// need to check this; only Admin, Employee, and current session ID should work
			else if (req.getMethod().equals("DELETE") && sections.length == 2) {
				uControl.deleteUser(req, resp, sections[1]);
			}
			break;

		case "accounts":
			if (req.getMethod().equals("GET")) {
				if (sections.length == 2) {
					int accountId = Integer.parseInt(sections[1]);
					accControl.getByAccountId(req, resp, accountId);
					
				} else if (sections.length == 3 && sections[1].equals("owner")) {
					int userId = Integer.parseInt(sections[2]);
					accControl.getAccountByUserId(req, resp, userId);
					// get by user ID!!!
				}
				else if (sections.length == 3 && sections[1].equals("status")) {
					int accStatId = Integer.parseInt(sections[2]);
					accControl.getAccountByStatusId(req, resp, accStatId);
				 }
				else {
					accControl.getAllAccounts(req, resp);
					// this should only be allowed for Admins and Employees
				}
			} else if (req.getMethod().equals("POST")) {
				if (sections.length == 3 && sections[1].equals("withdraw")) {
					accControl.withdraw(req, resp);
				}
				else if(sections.length == 3 && sections[1].equals("deposit")) {
					accControl.deposit(req, resp);
				}
			} else if (req.getMethod().equals("PUT")) {
				accControl.putAccount(req, resp);
				/*
				 * else if (req.getMethod().equals("PATCH") && sections.length == 4) { int
				 * accountId = Integer.parseInt(sections[2]); double amount =
				 * Double.parseDouble(sections[3]); if (sections.length == 3) {
				 * //accControl.withdrawFromAccount(req, resp, accountId, amount); }
				 */
			} else if (req.getMethod().equals("DELETE") && sections.length == 2) {
				accControl.deleteAccount(resp, sections[1]);
			}
			break;

		}// ends switch statement

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equals("PATCH")) {
			doPatch(req, resp);
		} else {
			super.service(req, resp);
		}
	}

}
