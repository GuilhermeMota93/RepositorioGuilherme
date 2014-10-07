package com.example.itlog.responseobjects;

import java.io.Serializable;

import com.example.itlog.objects.TimeSheet;
import com.google.gson.annotations.SerializedName;

public class POST_API_TimeSheets_Response implements Serializable {

	@SerializedName("Impt")
	TimeSheet impt;

	@SerializedName("StatusCd")
	String statusCd;

	@SerializedName("StatusTxt")
	String statusTxt;

	public POST_API_TimeSheets_Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public POST_API_TimeSheets_Response(TimeSheet impt, String statusCd,
			String statusTxt) {
		super();
		this.impt = impt;
		this.statusCd = statusCd;
		this.statusTxt = statusTxt;
	}

	public TimeSheet getImpt() {
		return impt;
	}

	public void setImpt(TimeSheet impt) {
		this.impt = impt;
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
