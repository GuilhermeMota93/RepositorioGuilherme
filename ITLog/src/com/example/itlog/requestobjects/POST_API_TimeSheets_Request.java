package com.example.itlog.requestobjects;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class POST_API_TimeSheets_Request implements Serializable {

	@SerializedName("Ano")
	int ano;

	@SerializedName("Mes")
	int mes;

	@SerializedName("Token")
	String token;

	public POST_API_TimeSheets_Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public POST_API_TimeSheets_Request(int ano, int mes, String token) {
		super();
		this.ano = ano;
		this.mes = mes;
		this.token = token;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
