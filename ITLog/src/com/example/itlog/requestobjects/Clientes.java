package com.example.itlog.requestobjects;

import java.util.ArrayList;

public class Clientes extends Projetos {
	String nome;
	ArrayList<Projetos> listaProj = new ArrayList<Projetos>();
	
	public Clientes (String nome, ArrayList<Projetos> listaProj){
		this.nome = nome;
		this.listaProj = listaProj;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}	
	
	public ArrayList<Projetos> getProjetos(){
		return listaProj;
	}
	
	public void setProjetos(ArrayList<Projetos> listaProjetos){
		this.listaProj = listaProjetos;
	}
	
	
	
}
