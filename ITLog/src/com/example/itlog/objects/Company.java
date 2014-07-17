package com.example.itlog.objects;

import java.util.ArrayList;

public class Company {

	String name;
	int id;
	ArrayList<Project> projects = new ArrayList<Project>();

	public Company() {

	}

	public Company(String nome, int id, ArrayList<Project> listaProj) {
		this.name = nome;
		this.id = id;
		this.projects = listaProj;
	}

	public Company(String nome, int id) {
		this.name = nome;
		this.id = id;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static ArrayList<Company> generateFakeCompany() {
		Project project1 = new Project();
		Project project2 = new Project();
		ArrayList<Company> company = new ArrayList<Company>();
		company.add(new Company("IT Sector", 1, project1.projects));
		company.add(new Company("ebankIT", 2, project2.projects));
		return company;
	}

}
