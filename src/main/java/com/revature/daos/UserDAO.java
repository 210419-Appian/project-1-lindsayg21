package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {

	public List<User> findAll();						
	public User findByUserId(int userId);				//our PK is our userID
	public boolean addUser(User user);					//either successful or not
	public User findByUsername(String username);
	public void updateUser(User user);			
	
}
