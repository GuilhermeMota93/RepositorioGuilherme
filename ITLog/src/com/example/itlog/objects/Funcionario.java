package com.example.itlog.objects;

import java.util.ArrayList;

public class Funcionario extends Users {
	String name, email, numeroFunc;

	public Funcionario(String name, String email, String id) {
		super();
		this.name = name;
		this.email = email;
		this.numeroFunc = id;
	}

	public Funcionario() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return numeroFunc;
	}

	public void setId(String id) {
		this.numeroFunc = id;
	}

	public static ArrayList<Funcionario> generateFakeFuncionarios() {
		ArrayList<Funcionario> funcionario = new ArrayList<Funcionario>();
		funcionario.add(new Funcionario("Guilherme Mota",
				"guilherme.mota@itsector.pt", "C812"));
		funcionario.add(new Funcionario("Alberto Ambibio",
				"albertini.mail@cenas.pt", "A123"));
		funcionario.add(new Funcionario("Teste", "testefuncionario@teste.pt",
				"a"));

		return funcionario;
	}
}