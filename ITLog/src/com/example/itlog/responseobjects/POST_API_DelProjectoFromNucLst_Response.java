package com.example.itlog.responseobjects;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class POST_API_DelProjectoFromNucLst_Response implements Serializable {

	@SerializedName("StatusCd")
	String statusCd;

	@SerializedName("StatusTxt")
	String statusTxt;

	public POST_API_DelProjectoFromNucLst_Response(String statusCd,
			String statusTxt) {
		super();
		this.statusCd = statusCd;
		this.statusTxt = statusTxt;
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
