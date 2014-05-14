package com.example.itlog;

import java.util.ArrayList;
import java.util.List;

public class GetDevice_Response {

	Object_ERROR error;
	Object_RESPONSE_GETDEVICE response;

	public GetDevice_Response(Object_ERROR error, Object_RESPONSE_GETDEVICE response) {
		super();
		this.error = error;
		this.response = response;
	}

	public Object_ERROR getError() {
		return error;
	}

	public void setError(Object_ERROR error) {
		this.error = error;
	}

	public Object_RESPONSE_GETDEVICE getResponse() {
		return response;
	}

	public void setResponse(Object_RESPONSE_GETDEVICE response) {
		this.response = response;
	}

	
	
}
