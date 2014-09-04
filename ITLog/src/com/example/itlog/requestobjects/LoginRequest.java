package com.example.itlog.requestobjects;

import java.io.Serializable;

import javax.crypto.SecretKey;

import com.google.gson.annotations.SerializedName;

public class LoginRequest implements Serializable {

	@SerializedName("UserName")
	private String username;

	@SerializedName("Password")
	private String password;

	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
