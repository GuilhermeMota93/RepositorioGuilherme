package com.example.itlog.responseobjects;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.itlog.objects.Cliente;
import com.google.gson.annotations.SerializedName;

public class GET_API_ClienteLst_Response implements Serializable {

	@SerializedName("Clientes")
	ArrayList<Cliente> clientes;

	@SerializedName("StatusCd")
	String statusCd;

	@SerializedName("StatusTxt")
	String statusTxt;

	public GET_API_ClienteLst_Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GET_API_ClienteLst_Response(ArrayList<Cliente> clientes,
			String statusCd, String statusTxt) {
		super();
		this.clientes = clientes;
		this.statusCd = statusCd;
		this.statusTxt = statusTxt;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
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
