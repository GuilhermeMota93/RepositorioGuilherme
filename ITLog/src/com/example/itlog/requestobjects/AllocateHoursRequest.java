package com.example.itlog.requestobjects;

import java.util.ArrayList;

import com.example.itlog.objects.Allocate;


public class AllocateHoursRequest {

	private String username;	
	private ArrayList<Allocate> allocate = new ArrayList<Allocate> ();

	public AllocateHoursRequest(String username, ArrayList<Allocate> allocate) {
		super();
		this.username = username;
		this.allocate = allocate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Allocate> getAllocate() {
		return allocate;
	}

	public void setAllocate(ArrayList<Allocate> allocate) {
		this.allocate = allocate;
	}
	
	
	

}
