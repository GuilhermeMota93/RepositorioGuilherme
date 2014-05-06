package com.example.itlog.responseobjects;

import java.util.HashMap;
import java.util.Map;

import android.R.integer;

import com.example.itlog.requestobjects.Project;

public class ListTotalHoursProjectResponse {

	private String username;
	private int hours;
	private Map<Project, integer> projects = new HashMap<Project, integer>();

	public ListTotalHoursProjectResponse(String username, int hours,
			Map<Project, integer> projects) {
		super();
		this.username = username;
		this.hours = hours;
		this.projects = projects;
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

	public Map<Project, integer> getProjects() {
		return projects;
	}

	public void setProjects(Map<Project, integer> projects) {
		this.projects = projects;
	}

}
