package com.example.itlog.requestobjects;

public class ListTotalHoursCompanyRequest {
	private String username;

	public ListTotalHoursCompanyRequest(String username) {
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
