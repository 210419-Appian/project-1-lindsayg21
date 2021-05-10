package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.daos.RoleDAO;
import com.revature.daos.RoleDAOImpl;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.models.UserDTO;

public class UserService {
	
	private UserDAO uDao = new UserDAOImpl();
	private UserDTO userDTO = new UserDTO();
	private User user = new User();

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
	
	public boolean updateUser(User user) {
		uDao.updateUser(user);
		return uDao.updateUser(user);
	}
	
	public boolean removeUser(int userId) {
		uDao.deleteUser(userId);
		return uDao.deleteUser(userId);
	}
	
	public boolean checkLoginCredentials(UserDTO uDTO) {
		
		User userReq = uDao.findByUsername(uDTO.getUsername()); //u.get?
		
		System.out.println(userReq.toString());
		
		//if (uDao.findByUsername(uDTO.getUsername()).getPassword() == uDTO.getPassword()) {
		if((userReq.getPassword() != null) && (uDTO.getPassword().equals(userReq.getPassword()))) {
			return true;
		}
		return false;
	}

	public boolean register(User newUser) {
		
		if(uDao.addUser(newUser)) {
			return true;
		}
		else {
			return false;
		}
	}	
	
}
