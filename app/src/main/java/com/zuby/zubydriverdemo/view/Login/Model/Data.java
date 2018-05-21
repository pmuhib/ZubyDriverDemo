package com.zuby.zubydriverdemo.view.Login.Model;


public class Data
{

	private String userId;

	private String sessionId;

	private String sessionLoginType;

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setSessionId(String sessionId){
		this.sessionId = sessionId;
	}

	public String getSessionId(){
		return sessionId;
	}

	public void setSessionLoginType(String sessionLoginType){
		this.sessionLoginType = sessionLoginType;
	}

	public String getSessionLoginType(){
		return sessionLoginType;
	}
}