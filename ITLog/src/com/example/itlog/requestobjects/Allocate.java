package com.example.itlog.requestobjects;

public class Allocate {
	String day;
	int hours;
	
	public Allocate(String day, int hours) {
		super();
		this.day = day;
		this.hours = hours;
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
	
	
}
