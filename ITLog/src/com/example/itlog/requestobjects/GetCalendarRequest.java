package com.example.itlog.requestobjects;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GetCalendarRequest {

	private String month;

	public GetCalendarRequest(String month) {
		super();
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
