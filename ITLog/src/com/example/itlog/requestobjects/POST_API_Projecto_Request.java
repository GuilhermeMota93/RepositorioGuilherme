package com.example.itlog.requestobjects;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class POST_API_Projecto_Request implements Serializable {

	@SerializedName("Cod")
	String cod;

	// este TOKEN sera String ou do tipo LoginResponse?
	@SerializedName("Token")
	String token;

	// LoginResponse token;

	public POST_API_Projecto_Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public POST_API_Projecto_Request(String cod, String token) {
		super();
		this.cod = cod;
		this.token = token;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
