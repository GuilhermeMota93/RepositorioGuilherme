package com.example.itlog;

import java.util.ArrayList;
import java.util.List;

public class GetDevice_Response {
	
	public ArrayList<ResponseArrayObject> response = new ArrayList<ResponseArrayObject>();
	
	public GetDevice_Response(ArrayList<ResponseArrayObject> response) {
		super();
		this.response = response;
	}

	public ArrayList<ResponseArrayObject> getResponse() {
		return response;
	}

	public void setResponse(ArrayList<ResponseArrayObject> response) {
		this.response = response;
	}	
	
	
	
	
}