package com.example.itlog.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Project extends Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5164160685266480492L;
	String name, manager, descripiton, userid;
	int projectId, hours, clientId;

	public Project() {

	}

	public Project(String name, int projectid, int companyid, String manager,
			String description, int hours) {
		this.name = name;
		this.projectId = projectid;
		this.clientId = companyid;
		this.manager = manager;
		this.descripiton = description;
		this.hours = hours;
	}

	public Project(String name, int projectid, int companyid, String manager,
			String description, int hours, String userid) {
		this.name = name;
		this.projectId = projectid;
		this.clientId = companyid;
		this.manager = manager;
		this.descripiton = description;
		this.hours = hours;
		this.userid = userid;

	}

	public Project(String name, int projectid, int companyid, String manager,
			String description) {
		this.name = name;
		this.projectId = projectid;
		this.clientId = companyid;
		this.manager = manager;
		this.descripiton = description;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getDescripiton() {
		return descripiton;
	}

	public void setDescripiton(String descripiton) {
		this.descripiton = descripiton;
	}

	public int getProjectid() {
		return projectId;
	}

	public void setProjectid(int projectid) {
		this.projectId = projectid;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getCompanyid() {
		return clientId;
	}

	public void setCompanyid(int companyid) {
		this.clientId = companyid;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + "]";
	}

	public static ArrayList<Project> generateFakeProjects() {
		ArrayList<Project> projects = new ArrayList<Project>();
		projects.add(new Project(
				"Projecto A",
				1,
				1,
				"António Arroz",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!",
				16, "A123"));
		projects.add(new Project(
				"Projecto B",
				2,
				1,
				"Baltazar Bolacha",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!",
				8, "C812"));
		projects.add(new Project(
				"Projecto C",
				3,
				2,
				"Cristiano Crouguette",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!",
				4));
		projects.add(new Project(
				"Projecto D",
				4,
				1,
				"Dilma Docinho",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!",
				32, "C812"));
		projects.add(new Project("Projecto E", 5, 1, "Elisio Eletro", "É mel!",
				64, "A123"));
		projects.add(new Project(
				"Projecto F",
				6,
				2,
				"Francisca Filetes",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!",
				16, "C812"));
		projects.add(new Project(
				"Projecto G",
				7,
				2,
				"Gina Gomas",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!",
				32));
		projects.add(new Project(
				"Projecto H",
				8,
				2,
				"Helio Hogurte",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!",
				8, "C812"));
		projects.add(new Project(
				"Projecto I",
				9,
				1,
				"Ilda Ilaria",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!",
				16, "A123"));
		projects.add(new Project(
				"Projecto J",
				10,
				1,
				"Jorge Jumento",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!",
				0, "A123"));
		projects.add(new Project(
				"Projecto K",
				11,
				2,
				"Karpem Kiem",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!",
				0, "A123"));
		projects.add(new Project("Projecto L", 12, 1, "Lois Lane",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!"));
		projects.add(new Project("Projecto M", 13, 1, "Marco Maria",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!"));
		projects.add(new Project("Projecto N", 14, 1, "Nando Nespera",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!"));
		projects.add(new Project("Projecto O", 15, 1, "Orlando Olivio",
				"Este projeto consiste em fazer coisas derivadas de assuntos como cenas!"));
		return projects;
	}

}
