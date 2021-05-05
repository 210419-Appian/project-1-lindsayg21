package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {

	public List<User> findAll();						//find all Users
	public User	findByUserId(int userId);				//finding a single user; our PK is our userID
	public boolean addUser(User user);					//either successful or not
	//public List<User> findByAccountId(int accountId);	//find User by account?
	//public User findByUsername(String username);
	public boolean updateUser(User user);				//update User???
	
}
