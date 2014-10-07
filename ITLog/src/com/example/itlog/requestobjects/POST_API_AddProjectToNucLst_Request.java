package com.example.itlog.requestobjects;

import java.io.Serializable;

import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.google.gson.annotations.SerializedName;

public class POST_API_AddProjectToNucLst_Request implements Serializable {

	@SerializedName("prjCod")
	String prjCod;

	// este token vai ser do tipo LoginResponse??? Ou string?
	@SerializedName("Token")
	String token;

	

	public POST_API_AddProjectToNucLst_Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public POST_API_AddProjectToNucLst_Request(String prjCod, String token) {
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
