package com.revature.utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException{
		//We need to register our driver. This process makes the application aware
		//of what particular Driver class we are using
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://appian-210419.cgorfhr2fxfh.us-east-2.rds.amazonaws.com:5432/postgres";
		String username = "postgres";
		String password = "password";
		
		return DriverManager.getConnection(url, username, password);
		
	}
	public static void main(String[] args) {
		
		try(Connection conn=ConnectionUtil.getConnection()){
			
			System.out.println("Connection successful");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}	
}

