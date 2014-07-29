package com.example.itlog.responseobjects;

import java.util.ArrayList;

import com.example.itlog.objects.Client;
import com.example.itlog.objects.Project;


public class ListProjectsUserResponse {

	public String username;
	public ArrayList<Project> projects = new ArrayList<Project>();
	public ArrayList<Client> company = new ArrayList<Client>();

	public ListProjectsUserResponse(String username,
			ArrayList<Project> projects, ArrayList<Client> company) {
		super();
		this.username = username;
		this.projects = projects;
		this.company = company;
	}

	public ListProjectsUserResponse(ArrayList<Project> projects, ArrayList<Client> company) {
		super();
		this.projects = projects;
		this.company = company;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	public ArrayList<Client> getCompany() {
		return company;
	}

	public void setCompany(ArrayList<Client> company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "ListProjectsUserResponse [username=" + username + ", projects="
				+ projects + ", company=" + company + ", getUsername()="
				+ getUsername() + ", getProjects()=" + getProjects()
				+ ", getCompany()=" + getCompany() + "]";
	}
	
	

}
