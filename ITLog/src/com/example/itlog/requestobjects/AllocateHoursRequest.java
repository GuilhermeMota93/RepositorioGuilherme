package com.example.itlog.requestobjects;

import java.util.ArrayList;


public class AllocateHoursRequest {

	private String username;
	private int day;
	private int hours;
	private ArrayList<Allocate> allocate = new ArrayList<Allocate> ();
	
	public AllocateHoursRequest(String username, int day, int hours,
			ArrayList<Allocate> allocate) {
		super();
		this.username = username;
		this.day = day;
		this.hours = hours;
		this.allocate = allocate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public ArrayList<Allocate> getAllocate() {
		return allocate;
	}
	public void setAllocate(ArrayList<Allocate> allocate) {
		this.allocate = allocate;
	}

	

}
