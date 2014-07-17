package com.example.itlog.objects;

import java.util.ArrayList;

public class Project extends Company {
	String name, manager, descripiton, userid;
	int projectid, hours, companyid;

	public Project() {

	}

	public Project(String name, int projectid, int companyid, String manager,
			String description, int hours) {
		this.name = name;
		this.projectid = projectid;
		this.companyid = companyid;
		this.manager = manager;
		this.descripiton = description;
		this.hours = hours;
	}

	public Project(String name, int projectid, int companyid, String manager,
			String description, int hours, String userid) {
		this.name = name;
		this.projectid = projectid;
		this.companyid = companyid;
		this.manager = manager;
		this.descripiton = description;
		this.hours = hours;
		this.userid = userid;

	}

	public Project(String name, int projectid, int companyid, String manager,
			String description) {
		this.name = name;
		this.projectid = projectid;
		this.companyid = companyid;
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
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + "]";
	}

	public static  ArrayList<Project> generateFakeProjects() {
		ArrayList<Project> projects = new ArrayList<Project>();
		projects.add(new Project("Projecto A", 1, 1, "Ant�nio Arroz",
				"� lindo!", 16, "A123"));
		projects.add(new Project("Projecto B", 2, 1, "Baltazar Bolacha",
				"� fant�stico!", 8, "C812"));
		projects.add(new Project("Projecto C", 3, 2, "Cristiano Crouguette",
				"� excelente!", 4));
		projects.add(new Project("Projecto D", 4, 1, "Dilma Docinho",
				"� belissimo!", 32, "C812"));
		projects.add(new Project("Projecto E", 5, 1, "Elisio Eletro", "� mel!",
				64, "A123"));
		projects.add(new Project("Projecto F", 6, 1, "Francisca Filetes",
				"� bela!", 16, "C812"));
		projects.add(new Project("Projecto G", 7, 2, "Gina Gomas",
				"� poderosa!", 32));
		projects.add(new Project("Projecto H", 8, 2, "Helio Hogurte",
				"� potente!", 8, "C812"));
		projects.add(new Project("Projecto I", 9, 1, "Ilda Ilaria",
				"� inexplicavel!",16, "A123"));
		projects.add(new Project("Projecto J", 10, 1, "Jorge Jumento",
				"� jumanji!"));
		projects.add(new Project("Projecto K", 11, 1, "Karpem Kiem",
				"� kool!"));
		projects.add(new Project("Projecto L", 12, 1, "Lois Lane",
				"� lupin!"));
		projects.add(new Project("Projecto M", 13, 1, "Marco Maria",
				"� maltesears!"));
		projects.add(new Project("Projecto N", 14, 1, "Nando Nespera",
				"� kitkat!"));
		projects.add(new Project("Projecto O", 15, 1, "Orlando Olivio",
				"� gingerbread!"));
		return projects;
	}
	
//	
//	public static ArrayList<Project> generateFakeUsersProjects(){
//		ArrayList<Project> userProjects = new ArrayList<Project>();
//		userProjects.add(new Project("Projecto P", 1, 1, "Ant�nio Arroz",
//				"� lindo!", 16, "A123"));
//		userProjects.add(new Project("Projecto Q", 2, 1, "Baltazar Bolacha",
//				"� fant�stico!", 8, "C812"));
//		userProjects.add(new Project("Projecto R", 3, 2, "Cristiano Crouguette",
//				"� excelente!", 4, "A123"));
//		userProjects.add(new Project("Projecto S", 4, 1, "Dilma Docinho",
//				"� belissimo!", 32, "C812"));
//		userProjects.add(new Project("Projecto T", 5, 1, "Elisio Eletro", "� mel!",
//				64, "A123"));
//		userProjects.add(new Project("Projecto U", 6, 1, "Francisca Filetes",
//				"� bela!", 16, "C812"));
//		userProjects.add(new Project("Projecto V", 7, 2, "Gina Gomas",
//				"� poderosa!", 32, "A123"));
//		return userProjects;
//	}

}
