package com.example.itlog;

import android.os.AsyncTask;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;

public class AddDevice_Service extends AsyncTask<String, Void, AddDevice_Response> {
	
	public CallbackInterface<AddDevice_Response> callback;
	String nomeServico;
	AddDevice_RequestObj adro;
	
	public AddDevice_Service(CallbackInterface<AddDevice_Response> callback,
			String nomeServico, AddDevice_RequestObj adro) {
		super();
		
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.adro = adro;
	}

	@Override
	protected AddDevice_Response doInBackground(String... params) {
		
		AddDevice_Response cba = CommunicationCenter.callPostService(nomeServico, adro, AddDevice_Response.class);
		
		return null;
	}

	@Override
	protected void onPostExecute(AddDevice_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}
	
	
}