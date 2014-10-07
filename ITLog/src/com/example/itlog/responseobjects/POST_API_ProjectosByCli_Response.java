package com.example.itlog.responseobjects;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.itlog.objects.Projecto;
import com.google.gson.annotations.SerializedName;

public class POST_API_ProjectosByCli_Response implements Serializable {

	@SerializedName("Projectos")
	ArrayList<Projecto> listaprojs;

	@SerializedName("StatusCd")
	String statusCd;

	@SerializedName("StatusTxt")
	String statusTxt;

	public POST_API_ProjectosByCli_Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public POST_API_ProjectosByCli_Response(ArrayList<Projecto> listaprojs,
			String statusCd, String statusTxt) {
		super();
		this.listaprojs = listaprojs;
		this.statusCd = statusCd;
		this.statusTxt = statusTxt;
	}

	public ArrayList<Projecto> getListaprojs() {
		return listaprojs;
	}

	public void setListaprojs(ArrayList<Projecto> listaprojs) {
		this.listaprojs = listaprojs;
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
