package com.example.itlog.objects;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Projecto implements Serializable {

	@SerializedName("Nome")
	String nome;

	@SerializedName("Cod")
	String cod;

	public Projecto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Projecto(String nome, String cod) {
		super();
		this.nome = nome;
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

}
