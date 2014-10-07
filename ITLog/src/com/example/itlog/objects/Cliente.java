package com.example.itlog.objects;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Cliente implements Serializable {

	@SerializedName("Cod")
	int cod;

	@SerializedName("Nome")
	String nome;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(int cod, String nome) {
		super();
		this.cod = cod;
		this.nome = nome;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
