package com.askme.model;

public class User{
	private int userId;
	private String username;
	private String email;
	private String password;
	private String createdAt;
	
	public User() {}
	
	
	public User(int userId,  String username,  String email,  String password,  String createdAt){
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
	}
	
	public int getUserId(){
		return userId;
	}
	public String getUsername(){
		return username;
	}
	public String getEmail(){
		return email;
	}
	public String getPassword(){
		return password;
	}
	public String getCreatedAt(){
		return createdAt;
	}
	
	
	
	public void setUserId(int userId){
		this.userId = userId;
	}
	public void setUsername(String u){
		this.username = u;
	}
	public void setEmail(String e){
		this.email = e;
	}
	public void setPassword(String p){
		this.password = p;
	}
	public void setCreatedAt(String c){
		this.createdAt = c;
	}
	
	
}




//It represents one database record
//One database row = One User object.