package com.example.itlog.responseobjects;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.itlog.objects.Projecto;
import com.google.gson.annotations.SerializedName;

public class GET_API_ProjectosLst_Response implements Serializable {

	// é assim que se faz para retornar a collection do JSON?
	@SerializedName("Projectos")
	ArrayList<Projecto> projectos;

	@SerializedName("StatusCd")
	private String statusCd;

	@SerializedName("StatusTxt")
	private String statusTxt;

	public GET_API_ProjectosLst_Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GET_API_ProjectosLst_Response(ArrayList<Projecto> projectos,
			String statusCd, String statusTxt) {
		super();
		this.projectos = projectos;
		this.statusCd = statusCd;
		this.statusTxt = statusTxt;
	}

	public ArrayList<Projecto> getProjectos() {
		return projectos;
	}

	public void setProjectos(ArrayList<Projecto> projectos) {
		this.projectos = projectos;
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

}
