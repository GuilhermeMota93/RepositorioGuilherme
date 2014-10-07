package com.example.itlog.objects;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class TimeSheet implements Serializable {

	@SerializedName("Ano")
	int ano;

	@SerializedName("Mes")
	int mes;

	@SerializedName("HorasDiaMultiplo")
	int horasDiaMultiplo;

	@SerializedName("HorasDiaMax")
	int horasDiaMax;

	@SerializedName("Dia")
	ArrayList<TimeSheetDay> dia;

	public TimeSheet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeSheet(int ano, int mes, int horasDiaMultiplo, int horasDiaMax,
			ArrayList<TimeSheetDay> dia) {
		super();
		this.ano = ano;
		this.mes = mes;
		this.horasDiaMultiplo = horasDiaMultiplo;
		this.horasDiaMax = horasDiaMax;
		this.dia = dia;
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

	public int getHorasDiaMultiplo() {
		return horasDiaMultiplo;
	}

	public void setHorasDiaMultiplo(int horasDiaMultiplo) {
		this.horasDiaMultiplo = horasDiaMultiplo;
	}

	public int getHorasDiaMax() {
		return horasDiaMax;
	}

	public void setHorasDiaMax(int horasDiaMax) {
		this.horasDiaMax = horasDiaMax;
	}

	public ArrayList<TimeSheetDay> getDia() {
		return dia;
	}

	public void setDia(ArrayList<TimeSheetDay> dia) {
		this.dia = dia;
	}

}
