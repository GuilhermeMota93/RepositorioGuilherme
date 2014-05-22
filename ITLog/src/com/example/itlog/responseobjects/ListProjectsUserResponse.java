package com.example.itlog.responseobjects;

import java.util.ArrayList;

import Objects_General.Company;
import Objects_General.Project;

public class ListProjectsUserResponse {

	public String username;
	public ArrayList<Project> projects = new ArrayList<Project>();
	public ArrayList<Company> company = new ArrayList<Company>();

	public ListProjectsUserResponse(String username,
			ArrayList<Project> projects, ArrayList<Company> company) {
		super();
		this.username = username;
		this.projects = projects;
		this.company = company;
	}

	public ListProjectsUserResponse(ArrayList<Project> projects, ArrayList<Company> company) {
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

	public ArrayList<Company> getCompany() {
		return company;
	}

	public void setCompany(ArrayList<Company> company) {
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
