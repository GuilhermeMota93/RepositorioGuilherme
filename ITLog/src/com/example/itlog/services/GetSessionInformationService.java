package com.example.itlog.services;

import android.os.AsyncTask;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.responseobjects.GetSessionInformationResponse;
import com.example.itlog.responseobjects.LoginResponse;

public class GetSessionInformationService extends
		AsyncTask<String, String, GetSessionInformationResponse> {

	private CallbackInterface<GetSessionInformationResponse> callback;
	String nomeServico;

	public GetSessionInformationService(
			CallbackInterface<GetSessionInformationResponse> callback,
			String nomeServico) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;

	}

	@Override
	protected GetSessionInformationResponse doInBackground(String... params) {
		// TODO Auto-generated method stub
		GetSessionInformationResponse getSesResponse = CommunicationCenter
				.callGetService(nomeServico, params,
						GetSessionInformationResponse.class);

		return getSesResponse;
	}

	@Override
	protected void onPostExecute(GetSessionInformationResponse result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}

}
