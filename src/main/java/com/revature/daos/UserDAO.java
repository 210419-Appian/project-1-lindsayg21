package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {

	public List<User> findAll();						
	public User findByUserId(int userId);				
	public User findByUsername(String username);
	public boolean addUser(User user);					
	public boolean updateUser(User user);			
	public boolean deleteUser(int userId);

}
