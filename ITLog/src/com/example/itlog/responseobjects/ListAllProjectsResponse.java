package com.example.itlog.responseobjects;

import java.util.ArrayList;

import com.example.itlog.objects.Project;


public class ListAllProjectsResponse {

	private ArrayList<Project> projects = new ArrayList<Project>();

	public ListAllProjectsResponse(ArrayList<Project> projects) {
		super();
		this.projects = projects;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

}
