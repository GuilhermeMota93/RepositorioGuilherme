package com.example.itlog.responseobjects;

import java.io.Serializable;

import com.example.itlog.objects.Projecto;
import com.google.gson.annotations.SerializedName;

public class POST_API_Projecto_Response implements Serializable{

	@SerializedName("StatusCd")
	String statusCd;
	
	@SerializedName("StatusTxt")
	String statusTxt;
	
	@SerializedName("Prj")
	Projecto prj;

	public POST_API_Projecto_Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public POST_API_Projecto_Response(String statusCd, String statusTxt, Projecto prj) {
		super();
		this.statusCd = statusCd;
		this.statusTxt = statusTxt;
		this.prj = prj;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getStatusTxt() {
		return statusTxt;
	}

	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}

	public Projecto getPrj() {
		return prj;
	}

	public void setPrj(Projecto prj) {
		this.prj = prj;
	}
	
	
	
}
