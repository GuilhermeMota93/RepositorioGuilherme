package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.responseobjects.GET_API_ProjectosLst_Response;

import android.os.AsyncTask;

public class GET_API_ProjectosLst_Service extends
		AsyncTask<String, String, GET_API_ProjectosLst_Response> {

	private CallbackInterface<GET_API_ProjectosLst_Response> callback;
	String nomeServico;

	public GET_API_ProjectosLst_Service(
			CallbackInterface<GET_API_ProjectosLst_Response> callback,
			String nomeServico) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
	}

	@Override
	protected GET_API_ProjectosLst_Response doInBackground(String... params) {
		// TODO Auto-generated method stub
		GET_API_ProjectosLst_Response getProjLstResponse = CommunicationCenter
				.callGetService(nomeServico, params,
						GET_API_ProjectosLst_Response.class);

		return getProjLstResponse;

	}

	@Override
	protected void onPostExecute(GET_API_ProjectosLst_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}

}
