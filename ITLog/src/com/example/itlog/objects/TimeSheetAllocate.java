package com.example.itlog.objects;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class TimeSheetAllocate implements Serializable {

	@SerializedName("PrjCod")
	String prjCod;

	@SerializedName("Horas")
	int horas;

	@SerializedName("AllocType")
	int allocType;

	public TimeSheetAllocate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeSheetAllocate(String prjCod, int horas, int allocType) {
		super();
		this.prjCod = prjCod;
		this.horas = horas;
		this.allocType = allocType;
	}

	public String getPrjCod() {
		return prjCod;
	}

	public void setPrjCod(String prjCod) {
		this.prjCod = prjCod;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getAllocType() {
		return allocType;
	}

	public void setAllocType(int allocType) {
		this.allocType = allocType;
	}

}
