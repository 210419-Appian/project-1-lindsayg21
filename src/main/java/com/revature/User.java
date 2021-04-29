package com.revature;

public class User {
	
	//keeps track of user information
	//so do we prompt for all of this???
	
	private int userId; //primary key, used in our database 
	private String username; //not null, unique
	private String password; //not null
	private String firstName; //not null
	private String lastName; //not null
	private String email;//not null
	private Role role; //Role object

	public  User(int userId, String username, String password) { //creates a User Object 
		this.userId = userId;
		this.username = username;
		this.password = password;
		
		
		
	}

	
	//We want to ask for their userId, username, and password, and from there, 
	
	
}
