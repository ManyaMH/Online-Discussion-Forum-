package com.askme.model;

public class Question {
	private int questionId;
	private int topicId;
	private String topicTitle;
	private int userId;
	private String username;
	private String content;
	private String createdAt;
	private int replyCount;
	
	 public Question() {}
	 

    public Question(int questionId, int topicId, int userId, String content, String createdAt) {
        this.questionId = questionId;
        this.topicId    = topicId;
        this.userId     = userId;
        this.content    = content;
        this.createdAt  = createdAt;
    }

    public int    getQuestionId(){ 
		return questionId;
	}
    public int    getTopicId(){
		return topicId;
	}
    public String getTopicTitle() {
		return topicTitle; 
	}
    public int    getUserId(){
		return userId; 
	}
    public String getUsername(){
		return username;
	}
    public String getContent() {
		return content; 
	}
    public String getCreatedAt() {
		return createdAt; 
	}
    public int    getReplyCount() {
		return replyCount;
	}

    public void setQuestionId(int q){
		this.questionId = q; 
		}
    public void setTopicId(int t) {
		this.topicId    = t; 
	}
    public void setTopicTitle(String t)  {
		this.topicTitle = t; 
	}
    public void setUserId(int u) {
		this.userId     = u; 
	}
    public void setUsername(String u){
		this.username   = u;
		}
    public void setContent(String c){
		this.content    = c; 
		}
    public void setCreatedAt(String c) {
		this.createdAt  = c;
		}
    public void setReplyCount(int r) {
		this.replyCount = r; 
		}
}


	