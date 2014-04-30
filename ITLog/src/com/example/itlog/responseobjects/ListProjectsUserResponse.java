package com.example.itlog.responseobjects;

import java.util.ArrayList;

import com.example.itlog.requestobjects.Project;

public class ListProjectsUserResponse {

	private String username;
	private ArrayList<Project> projects = new ArrayList<Project>();

	public ListProjectsUserResponse(String username,
			ArrayList<Project> listaProjetosUser) {
		super();
		this.username = username;
		this.projects = listaProjetosUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Project> getListaProjetosUser() {
		return projects;
	}

	public void setListaProjetosUser(ArrayList<Project> listaProjetosUser) {
		this.projects = listaProjetosUser;
	}

}
