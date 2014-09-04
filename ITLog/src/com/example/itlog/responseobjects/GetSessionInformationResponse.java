package com.example.itlog.responseobjects;

import com.google.gson.annotations.SerializedName;

public class GetSessionInformationResponse {

	@SerializedName("UserName")
	private String username;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("UserID")
	private String userID;

	@SerializedName("Email")
	private String email;

	@SerializedName("StatusCd")
	private String statusCd;

	@SerializedName("StatusTxt")
	private String statusTxt;

	public GetSessionInformationResponse(String username, String fullname,
			String userID, String email, String statusCd, String statusTxt) {
		super();
		this.username = username;
		this.fullName = fullname;
		this.userID = userID;
		this.email = email;
		this.statusCd = statusCd;
		this.statusTxt = statusTxt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullName;
	}

	public void setFullname(String fullname) {
		this.fullName = fullname;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
