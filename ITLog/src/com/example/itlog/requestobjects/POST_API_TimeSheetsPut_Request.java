package com.example.itlog.requestobjects;

import java.io.Serializable;

import com.example.itlog.objects.TimeSheetPut;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POST_API_TimeSheetsPut_Request implements Serializable {

	@Expose
	private TimeSheetPut allocTS;
	@SerializedName("Token")
	@Expose
	private String token;

	public POST_API_TimeSheetsPut_Request(TimeSheetPut allocTS, String token) {
		super();
		this.allocTS = allocTS;
		this.token = token;
	}

	public POST_API_TimeSheetsPut_Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeSheetPut getAllocTS() {
		return allocTS;
	}

	public void setAllocTS(TimeSheetPut allocTS) {
		this.allocTS = allocTS;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
