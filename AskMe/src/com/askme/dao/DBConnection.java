package com.askme.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
	
	private static final String URL  =  "jdbc:mysql://localhost:3306/askme_db?user=root&password=admin";
    
	static {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");	
		} catch(ClassNotFoundException e){
			System.out.println("Mysql Driver not found:" + e.getMessage());
		}
	}
		
	public static Connection getConnection() throws SQLException{
			return DriverManager.getConnection(URL);
		}
}
