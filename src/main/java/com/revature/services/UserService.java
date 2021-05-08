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
		/*if(user.getRole() == null) {
			return aDao.
			do this after
		}*/
		return uDao.addUser(user);
	}
	
	public boolean updateTotalUser(User user) {
		//need to be able to change name, email... everything.
		//reference his update avenger from hellofrontcontroller
		return uDao.updateUser(user);
	}
	
	//makes sense, but maybe don't let people change first name/last name?
	//actually, can change these. 
	public boolean updatePartialUser(User user) {
		
		if(user.getUserId() == 0) {
			return false;
			//need userId to update a user
		}
		
		User userInfo = findByUserId(user.getUserId());
		//we have their user id
		
		if(user.getUsername() == null) {
			user.setUsername(userInfo.getUsername());
		}
		
		if(user.getPassword() == null) {
			user.setPassword(userInfo.getPassword());
		}
		
		if(user.getFirstName() == null) {
			user.setFirstName(userInfo.getFirstName());
		}

		if(user.getLastName() == null) {
			user.setLastName(userInfo.getLastName());
		}
		
		if(user.getEmail() == null) {
			user.setEmail(userInfo.getEmail());
		}
		if(user.getRole() == null) {
			user.setRole(userInfo.getRole());
		}
		
		return uDao.updateUser(user);
		//need to figure out what's changing and what isn't
	}
	
	public boolean removeUser(int userId) {
		return uDao.deleteUser(userId);
	}
	
}
