package com.example.itlog.requestobjects;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class GET_API_ProjectosAndAusLst_Request implements Serializable {

	@SerializedName("Token")
	String token;

	public GET_API_ProjectosAndAusLst_Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GET_API_ProjectosAndAusLst_Request(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
