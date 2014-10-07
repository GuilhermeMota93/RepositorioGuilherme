package com.example.itlog.responseobjects;

import java.io.Serializable;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class POST_API_Login_Response implements Serializable {

	@SerializedName("Token")
	private String token;

	@SerializedName("StatusCd")
	private String statusCd;

	@SerializedName("StatusTxt")
	private String statusTxt;

	private static POST_API_Login_Response instancia = null;

	public static POST_API_Login_Response getInstance() {
		if (instancia == null)
			instancia = new POST_API_Login_Response();
		return instancia;
	}

	private POST_API_Login_Response() {
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
