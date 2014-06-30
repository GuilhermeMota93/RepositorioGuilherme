package com.example.itlog.objects;

import java.util.ArrayList;


public class Company extends Project {
	String name;
	ArrayList<Project> projects = new ArrayList<Project>();
	
	
	public Company (String nome, ArrayList<Project> listaProj){
		this.name = nome;
		this.projects = listaProj;
	}
	
	public String getNome(){
		return name;
	}
	
	public void setNome(String nome){
		this.name = nome;
	}	
	
	public ArrayList<Project> getProjetos(){
		return projects;
	}
	
	public void setProjetos(ArrayList<Project> listaProjetos){
		this.projects = listaProjetos;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + "]";
	}

	
	
	
}
