package com.askme.model;

public class Subject{
	private int subjectId;
	private String name;
	private int createdBy;
	private String createdByUsername;
	private String createdAt;
	
	public Subject() {}
	
	
	public Subject(int subjectId,  String name,  int createdBy, String createdAt){
		this.subjectId = subjectId;
		this.name = name;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}
	
	public int getSubjectId(){
		return subjectId;
	}
	public String getName(){
		return name;
	}
	public int getCreatedBy(){
		return createdBy;
	}
	public String getCreatedByUsername(){
		return createdByUsername;
	}
	public String getCreatedAt(){
		return createdAt;
	}
	
	public void setSubjectId(int s){
		this.subjectId = s;
	}
	public void setName(String n){
		this.name = n;
	}
	public void setCreatedBy(int c){
		this.createdBy = c;
	}
	public void setCreatedByUsername(String u){
		this.createdByUsername = u;
	}
	public void setCreatedAt(String c){
		this.createdAt = c;
	}
	
	
}




//It represents one database record
