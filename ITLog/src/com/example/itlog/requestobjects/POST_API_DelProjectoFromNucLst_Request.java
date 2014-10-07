package com.example.itlog.requestobjects;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class POST_API_DelProjectoFromNucLst_Request implements Serializable {

	@SerializedName("prjCod")
	String prjCod;

	@SerializedName("Token")
	String token;

	public POST_API_DelProjectoFromNucLst_Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public POST_API_DelProjectoFromNucLst_Request(String prjCod, String token) {
		super();
		this.prjCod = prjCod;
		this.token = token;
	}

	public String getPrjCod() {
		return prjCod;
	}

	public void setPrjCod(String prjCod) {
		this.prjCod = prjCod;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
