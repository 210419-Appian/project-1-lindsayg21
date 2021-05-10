package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.AccountDAOImpl;
import com.revature.daos.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.BalanceDTO;
import com.revature.models.TransferDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
//import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserService;

public class AccountController {

	private AccountService accService = new AccountService();
	private ObjectMapper om = new ObjectMapper();
	private AccountDAOImpl accDao = new AccountDAOImpl();
	private UserDAOImpl uDao = new UserDAOImpl();

	public void getAllAccounts(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			return;
		}

		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);

		if (user.getRole().getRoleId() == 1) {

			List<Account> accountList = accService.getAllAccounts();

			String json = om.writeValueAsString(accountList);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);
			// set as 200 bc OK

		} else if (user.getRole().getRoleId() == 2) {

			List<Account> accountList = accService.getAllAccounts();

			String json = om.writeValueAsString(accountList);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);

		} else {
			pw.print("Unauthorized Request.");
			resp.setStatus(400);
		}

	}

	public void getByAccountId(HttpServletRequest req, HttpServletResponse resp, int accountId) throws IOException {

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			return;
		}

		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);

		Account account = accService.findByAccountId(accountId);

		AccountService accServ = new AccountService();

		AccountDAOImpl aDao = new AccountDAOImpl();

		if (user.getRole().getRoleId() == 1 || user.getRole().getRoleId() == 2) {

			String json = om.writeValueAsString(account);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);
			// set as 200 bc OK

		} else if (accServ.findByUserId(user.getUserId()).equals(aDao.findByUserId(accountId))) {

			String json = om.writeValueAsString(account);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);

		} else {
			pw.print("Unauthorized Request.");
			resp.setStatus(400);
		}

	}

	public void submit(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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

			Account account = om.readValue(body, Account.class);
			// Account object coming out of our request

			if (accService.createAccount(account)) {
				pw.print(om.writeValueAsString(accDao.findByUserId(user.getUserId())));
				resp.setStatus(201); // Account created!
			} else {
				resp.setStatus(406); // maybe 400!
			}
		} else {
			pw.print("Cannot complete request");
			resp.setStatus(401);
		}
	}

	public void getAccountByStatusId(HttpServletRequest req, HttpServletResponse resp, int acctStatId)
			throws IOException {
		// findByAccStatusId returns an Account, so that's good

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			return;
		}

		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);
		Account account = accService.findByAccStatId(acctStatId); // account with this status Id
		// crap should this be a list?

		if (user.getRole().getRoleId() == 1 || user.getRole().getRoleId() == 2) {

			Account aServ = accService.findByAccStatId(acctStatId);
			String json = om.writeValueAsString(aServ);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);

		} else if (user.getUserId() == accService.findByAccStatId(acctStatId).getUser().getUserId()) {
			Account aServ = accService.findByAccStatId(acctStatId);
			String json = om.writeValueAsString(aServ);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);

		} else {
			pw.print("Unauthorized Request");
			resp.setStatus(401);
		}
	}

	public void getAccountByUserId(HttpServletRequest req, HttpServletResponse resp, int userId) throws IOException {

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			return;
		}

		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);
		Account aServ = accService.findByUserId(userId);

		if (user.getRole().getRoleId() == 1 || user.getRole().getRoleId() == 2) {

			String json = om.writeValueAsString(aServ);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);
		} else if (user.getUserId() == accService.findByUserId(userId).getUser().getUserId()) {
			String json = om.writeValueAsString(aServ);
			System.out.println(json);
			pw.print(json);
			resp.setStatus(200);

		} else {
			pw.print("Unauthorized Request");
			resp.setStatus(401);
		}
	}

	public void withdraw(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			return;
		}

		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);
		int userId = user.getUserId();

		BalanceDTO balDTO = new BalanceDTO();
		Account account = accService.findByAccountId(balDTO.getAccountId()); // sets
		// account from balDTO

		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		balDTO = om.readValue(body, BalanceDTO.class);
		// sets the accountId and balance.

		if (user.getRole().getRoleId() == 1 || user.getUserId() == accService.findByUserId(userId).getUser().getUserId()) {

			if (accService.withdraw(balDTO)) {
				pw.print("\"message\": $" + balDTO.getAmount() + " has been withdrawn from Account #"
						+ balDTO.getAccountId());
				pw.print(". Your new balance is $" + accDao.findAccountBalance(balDTO.getAccountId()));
				resp.setStatus(200);
			} else {
				pw.print("Cannot perform this action.");
				resp.setStatus(400);
			}
		}

		else {
			pw.print("Cannot perform this action.");
			resp.setStatus(400);
		}

	}

	public void deposit(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			return;
		}

		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);
		int userId = user.getUserId();

		BalanceDTO balDTO = new BalanceDTO();
		Account account = accService.findByAccountId(balDTO.getAccountId());

		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		balDTO = om.readValue(body, BalanceDTO.class);

		if (user.getRole().getRoleId() == 1 || userId == account.getUser().getUserId()) {

			if (accService.deposit(balDTO)) {
				pw.print("\"message\": $" + balDTO.getAmount() + " has been desposited to Account #"
						+ balDTO.getAccountId());
				pw.print(". Your new balance is $" + accDao.findAccountBalance(balDTO.getAccountId()));
				resp.setStatus(200);
			} else {
				pw.print("Cannot perform this action.");
				resp.setStatus(400);
			}

		} else {
			pw.print("Cannot perform this action.");
			resp.setStatus(400);
		}

	}

	public void transfer(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter pw = resp.getWriter();

		if (req.getSession(false) == null) {
			pw.print("There is no one logged in.");
			return;
		}

		HttpSession ses = req.getSession();
		String str = (String) ses.getAttribute("username"); // reading from session cookie
		User user = uDao.findByUsername(str);
		int userId = user.getUserId();

		TransferDTO transDTO = new TransferDTO(); // taking money from
		// TransferDTO transDTO2 = new TransferDTO(); //putting money into

		Account account1 = accService.findByAccountId(transDTO.getAccountId1());
		Account account2 = accService.findByAccountId(transDTO.getAccountId2());

		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		transDTO = om.readValue(body, TransferDTO.class);

		// sets the accountId and balance.

		if (user.getRole().getRoleId() == 1 || userId == account1.getUser().getUserId()) {
			// first account must match the one logged into the session

			if (accService.transferMoney(transDTO)) {
				pw.print("\"message\": $" + transDTO.getAmount() + " has been withdrawn from Account #"
						+ transDTO.getAccountId1());
				pw.print(". Your new balance is $" + accDao.findAccountBalance(transDTO.getAccountId1()));
				System.out.println();
				pw.print(" $" + transDTO.getAmount() + " has been deposited into Account #" + transDTO.getAccountId2());
				pw.print(". Your new balance is $" + accDao.findAccountBalance(transDTO.getAccountId2()));
				resp.setStatus(200);
			}
			// accDao.findAccountBalance(balDTO.getAccountId())
			else {
				pw.print("Cannot perform this action.");
				resp.setStatus(400);
			}

		} else {
			pw.print("Cannot perform this action.");
			resp.setStatus(400);
		}

	}

	public void deleteAccount(HttpServletResponse resp, String deletion) throws IOException {
		try {
			int accountId = Integer.parseInt(deletion);
			if (accService.removeAccount(accountId)) {
				resp.setStatus(204);
			} else {
				resp.setStatus(400);
			}
		} catch (NumberFormatException e) {
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

		if (accService.updateAccount(account)) {
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}
	}

}
