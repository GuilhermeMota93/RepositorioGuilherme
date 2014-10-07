package com.example.itlog.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeSheetPut implements Serializable {

	@SerializedName("Ano")
	@Expose
	private Integer ano;
	@SerializedName("Mes")
	@Expose
	private Integer mes;
	@SerializedName("Dia")
	@Expose
	private ArrayList<TimeSheetDay> dia = new ArrayList<TimeSheetDay>();

	public Integer getAno() {
		return ano;
	}

	public TimeSheetPut(Integer ano, Integer mes, ArrayList<TimeSheetDay> dia) {
		super();
		this.ano = ano;
		this.mes = mes;
		this.dia = dia;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public ArrayList<TimeSheetDay> getDia() {
		return dia;
	}

	public void setDia(ArrayList<TimeSheetDay> dia) {
		this.dia = dia;
	}
}
