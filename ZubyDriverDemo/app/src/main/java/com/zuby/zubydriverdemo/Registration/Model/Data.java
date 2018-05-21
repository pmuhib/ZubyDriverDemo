package com.zuby.zubydriverdemo.Registration.Model;

import com.google.gson.annotations.SerializedName;

public class Data
{

	private String countryCode;

	private String userId;

	private String mobileNo;

	private String timeZone;

	private String registrationTime;

	private String status;

	private String accesstype;

//	private String type;


//	public void setType(String type)
//	{
//		this.type=type;
//	}
//
//	public String getType()
//	{
//		return type;
//	}

	public void setAccesstype(String accesstype)
	{
		this.accesstype=accesstype;
	}

	public String getAccesstype()
	{
		return accesstype;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}

	public String getMobileNo(){
		return mobileNo;
	}

	public void setTimeZone(String timeZone){
		this.timeZone = timeZone;
	}

	public String getTimeZone(){
		return timeZone;
	}

	public void setRegistrationTime(String registrationTime){
		this.registrationTime = registrationTime;
	}

	public String getRegistrationTime(){
		return registrationTime;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}