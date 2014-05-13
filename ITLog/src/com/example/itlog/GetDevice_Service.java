package com.example.itlog;

import android.os.AsyncTask;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;

public class GetDevice_Service extends AsyncTask<String, Void, GetDevice_Response>{
	
	public CallbackInterface<GetDevice_Response> callback;
	String nomeServico;
	GetDevice_RequestObj gdro;
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	public GetDevice_Service(CallbackInterface<GetDevice_Response> callback,
			String nomeServico, GetDevice_RequestObj gdro) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.gdro = gdro;
	}

	@Override
	protected GetDevice_Response doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		GetDevice_Response abc = CommunicationCenter.callPostService(nomeServico, gdro, GetDevice_Response.class);
		return abc;
	}
	
	
	@Override
	protected void onPostExecute(GetDevice_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}
	
}