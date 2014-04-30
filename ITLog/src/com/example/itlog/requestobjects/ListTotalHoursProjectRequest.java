package com.example.itlog.requestobjects;

public class ListTotalHoursProjectRequest {
	
	private String username;

	public ListTotalHoursProjectRequest(String username) {
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
