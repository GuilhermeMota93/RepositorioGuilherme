package com.example.itlog.requestobjects;

public class AddProjectRequest {
	private String username;
	private int projectid;
	
	public AddProjectRequest(String username, int projectid) {
		super();
		this.username = username;
		this.projectid = projectid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
	
	
}
