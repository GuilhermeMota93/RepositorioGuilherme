package com.example.itlog.responseobjects;

import java.util.HashMap;
import java.util.Map;

import com.example.itlog.objects.Project;

import android.R.integer;


public class ListTotalHoursProjectResponse {

	private String username;
	private int hours;
	private Project project;
	
	public ListTotalHoursProjectResponse(String username, int hours,
			Project project) {
		super();
		this.username = username;
		this.hours = hours;
		this.project = project;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	

}
