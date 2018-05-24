package com.zuby.zubydriverdemo.view.DocumentUpload.Presenter;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItem implements Serializable {

	@SerializedName("document_name")
	private String documentName;

	@SerializedName("document_id")
	private int documentId;

	public void setDocumentName(String documentName){
		this.documentName = documentName;
	}

	public String getDocumentName(){
		return documentName;
	}

	public void setDocumentId(int documentId){
		this.documentId = documentId;
	}

	public int getDocumentId(){
		return documentId;
	}
}