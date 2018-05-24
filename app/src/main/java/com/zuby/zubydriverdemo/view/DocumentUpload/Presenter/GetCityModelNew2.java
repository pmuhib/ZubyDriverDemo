package com.zuby.zubydriverdemo.view.DocumentUpload.Presenter;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetCityModelNew2 implements Serializable {

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("type")
	private String type;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}
}