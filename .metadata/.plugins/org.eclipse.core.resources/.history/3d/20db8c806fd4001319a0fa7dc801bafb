package com.example.itlog.services;

import android.os.AsyncTask;

import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.responseobjects.GetSessionInformationResponse;
import com.example.itlog.responseobjects.LoginResponse;

public class GetSessionInformationService extends AsyncTask<String, String, GetSessionInformationResponse>{
	
	String nomeServico;
	GetSessionInformationService gsis;
	
	public GetSessionInformationService(String nomeServico, GetSessionInformationService gsis){
		this.nomeServico = nomeServico;
		this.gsis = gsis;
		
	}
	
	@Override
	protected GetSessionInformationResponse doInBackground(String... params) {
		// TODO Auto-generated method stub
		CommunicationCenter.callPostService(nomeServico, gsis, GetSessionInformationResponse.class);
		return null;
	}

	@Override
	protected void onPostExecute(GetSessionInformationResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
