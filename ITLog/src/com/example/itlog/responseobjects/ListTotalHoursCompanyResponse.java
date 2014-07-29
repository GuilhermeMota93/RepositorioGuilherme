package com.example.itlog.responseobjects;

import java.util.HashMap;
import java.util.Map;

import com.example.itlog.objects.Client;

import android.R.integer;


public class ListTotalHoursCompanyResponse {
	
	private String username;
	private String day;
	private int hours;
	private Map<Client, integer> companies = new HashMap<Client, integer>();

	public ListTotalHoursCompanyResponse(String username, String day,
			int hours, Map<Client, integer> companies) {
		super();
		this.username = username;
		this.day = day;
		this.hours = hours;
		this.companies = companies;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Map<Client, integer> getCompanies() {
		return companies;
	}

	public void setCompanies(Map<Client, integer> companies) {
		this.companies = companies;
	}

}
