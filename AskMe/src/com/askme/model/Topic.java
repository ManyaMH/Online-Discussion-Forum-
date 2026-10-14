package com.askme.model;

public class Topic {
	private int topicId;
	private int subjectId;
	private String subjectName;
	private String title;
	private int createdBy;
	private String createdByUsername;
	private String createdAt;
	
	public Topic() {}

    public Topic(int topicId, int subjectId, String title, int createdBy, String createdAt) {
        this.topicId   = topicId;
        this.subjectId = subjectId;
        this.title     = title;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public int    getTopicId(){
		return topicId; 
	}
    public int    getSubjectId(){ 
		return subjectId; 
	}
    public String getSubjectName(){
		return subjectName; 
	}
    public String getTitle(){
		return title; 
	}
    public int    getCreatedBy(){
		return createdBy; 
	}
    public String getCreatedByUsername() { 
		return createdByUsername; 
	}
    public String getCreatedAt(){ 
		return createdAt; 
	}



    public void setTopicId(int t){ 
		this.topicId = t; 
	}
    public void setSubjectId(int s){ 
		this.subjectId = s;
	}
    public void setSubjectName(String s){ 
		this.subjectName = s; 
	}
    public void setTitle(String t){
		this.title= t; 
	}
    public void setCreatedBy(int c){ 
		this.createdBy = c; 
	}
    public void setCreatedByUsername(String u){ 
		this.createdByUsername = u; 
	}
    public void setCreatedAt(String c){
		this.createdAt  = c; 
	}
}