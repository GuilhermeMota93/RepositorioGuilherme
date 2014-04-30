package com.example.itlog.requestobjects;

public class ListProjectUserRequest {
	String username;

	public ListProjectUserRequest(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
