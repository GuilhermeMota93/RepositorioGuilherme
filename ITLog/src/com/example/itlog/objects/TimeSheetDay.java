package com.example.itlog.objects;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class TimeSheetDay implements Serializable {

	@SerializedName("Dia")
	int dia;

	@SerializedName("DiaDaSemana")
	String diaDaSemana;

	@SerializedName("DiaFeriado")
	boolean diaFeriado;

	@SerializedName("DiaAvlbtoAllocate")
	boolean diaAvlbToAllocate;

	@SerializedName("DiaAllocate")
	ArrayList<TimeSheetAllocate> diaAllocate;

	public TimeSheetDay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeSheetDay(int dia, String diaDaSemana, boolean diaFeriado,
			boolean diaAvlbToAllocate, ArrayList<TimeSheetAllocate> diaAllocate) {
		super();
		this.dia = dia;
		this.diaDaSemana = diaDaSemana;
		this.diaFeriado = diaFeriado;
		this.diaAvlbToAllocate = diaAvlbToAllocate;
		this.diaAllocate = diaAllocate;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public boolean isDiaFeriado() {
		return diaFeriado;
	}

	public void setDiaFeriado(boolean diaFeriado) {
		this.diaFeriado = diaFeriado;
	}

	public boolean isDiaAvlbToAllocate() {
		return diaAvlbToAllocate;
	}

	public void setDiaAvlbToAllocate(boolean diaAvlbToAllocate) {
		this.diaAvlbToAllocate = diaAvlbToAllocate;
	}

	public ArrayList<TimeSheetAllocate> getDiaAllocate() {
		return diaAllocate;
	}

	public void setDiaAllocate(ArrayList<TimeSheetAllocate> diaAllocate) {
		this.diaAllocate = diaAllocate;
	}

}
