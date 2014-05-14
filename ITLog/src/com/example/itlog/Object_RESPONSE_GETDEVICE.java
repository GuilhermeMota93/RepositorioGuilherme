package com.example.itlog;

import java.util.ArrayList;

public class Object_RESPONSE_GETDEVICE {
	
	public ArrayList<ResponseArrayObject> devices = new ArrayList<ResponseArrayObject>();

	public Object_RESPONSE_GETDEVICE(ArrayList<ResponseArrayObject> devices) {
		super();
		this.devices = devices;
	}

	public ArrayList<ResponseArrayObject> getDevices() {
		return devices;
	}

	public void setDevices(ArrayList<ResponseArrayObject> devices) {
		this.devices = devices;
	}
	
	
	
}
