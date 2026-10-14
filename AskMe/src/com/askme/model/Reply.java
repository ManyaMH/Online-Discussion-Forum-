package com.askme.model;

public class Reply {
    private int replyId;
    private int questionId;
    private int userId;
    private String username;
    private String content;
    private String createdAt;

    public Reply() {}

    public Reply(int replyId, int questionId, int userId, String content, String createdAt) {
        this.replyId    = replyId;
        this.questionId = questionId;
        this.userId     = userId;
        this.content    = content;
        this.createdAt  = createdAt;
    }

    public int getReplyId(){
		return replyId; 
	}
    public int getQuestionId(){
		return questionId; 
	}
    public int getUserId(){
		return userId; 
	}
    public String getUsername(){
		return username; 
	}
    public String getContent(){
		return content; 
	}
    public String getCreatedAt(){
		return createdAt; 
	}
	
	

    public void setReplyId(int r){
		this.replyId    = r; 
	}
    public void setQuestionId(int q){
		this.questionId = q; 
	}
    public void setUserId(int u){
		this.userId     = u; 
	}
    public void setUsername(String u){
		this.username   = u; 
	}
    public void setContent(String c){
		this.content    = c; 
	}
    public void setCreatedAt(String c){
		this.createdAt  = c; 
		}
}