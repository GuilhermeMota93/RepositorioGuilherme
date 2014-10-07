package com.example.itlog.requestobjects;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class POST_API_ProjectosByCli_Request implements Serializable {

	@SerializedName("Cod")
	int cod;

	@SerializedName("Token")
	String token;

	public POST_API_ProjectosByCli_Request(int cod, String token) {
		super();
		this.cod = cod;
		this.token = token;
	}

	public POST_API_ProjectosByCli_Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
