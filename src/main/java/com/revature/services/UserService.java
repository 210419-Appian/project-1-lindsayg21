package com.revature.services;

import java.util.List;

import com.revature.daos.RoleDAO;
import com.revature.daos.RoleDAOImpl;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.Role;
import com.revature.models.User;

public class UserService {

	//need to compare passwords	
	//be able to create a new user as well 
	//reference demo!!!
	
	private UserDAO uDao = new UserDAOImpl();

	public List<User> getAllUsers() {
		return uDao.findAll();
	}
	
	public User findByUserId(int userId) {
		return uDao.findByUserId(userId);
	}
	
	public User findByUsername(String username) {
		return uDao.findByUsername(username);
	}
	
	public boolean createUser(User user) {
		return uDao.addUser(user);
	}
	
	public void updateUser(User user) {
		//need to be able to change name, email... everything.
		//reference his update avenger from hellofrontcontroller
		uDao.updateUser(user);
	}
	
	public boolean removeUser(int userId) {
		return uDao.deleteUser(userId);
	}
	
}
