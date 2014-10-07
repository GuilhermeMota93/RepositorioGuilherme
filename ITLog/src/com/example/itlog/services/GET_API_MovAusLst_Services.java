package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.responseobjects.GET_API_MovAusLst_Response;

import android.os.AsyncTask;

public class GET_API_MovAusLst_Services extends
		AsyncTask<String, String, GET_API_MovAusLst_Response> {

	private CallbackInterface<GET_API_MovAusLst_Response> callback;
	String nomeServico;

	public GET_API_MovAusLst_Services(
			CallbackInterface<GET_API_MovAusLst_Response> callback,
			String nomeServico) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
	}

	@Override
	protected GET_API_MovAusLst_Response doInBackground(String... params) {
		// TODO Auto-generated method stub

		GET_API_MovAusLst_Response listaAusResponse = CommunicationCenter
				.callGetService(nomeServico, params,
						GET_API_MovAusLst_Response.class);
		
		return listaAusResponse;

	}

	@Override
	protected void onPostExecute(GET_API_MovAusLst_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}

}
