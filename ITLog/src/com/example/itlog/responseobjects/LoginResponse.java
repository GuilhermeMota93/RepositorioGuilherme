package com.example.itlog.responseobjects;

import java.io.Serializable;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Serializable {

	@SerializedName("Token")
	private String token;

	@SerializedName("StatusCd")
	private String statusCd;

	@SerializedName("StatusTxt")
	private String statusTxt;

	private static LoginResponse instancia = null;

	public static LoginResponse getInstance() {
		if (instancia == null)
			instancia = new LoginResponse();
		return instancia;
	}

	private LoginResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
