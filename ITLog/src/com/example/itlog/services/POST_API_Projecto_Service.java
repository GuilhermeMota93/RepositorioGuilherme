package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.POST_API_Projecto_Request;
import com.example.itlog.responseobjects.POST_API_Projecto_Response;

import android.os.AsyncTask;

public class POST_API_Projecto_Service extends
		AsyncTask<String, Void, POST_API_Projecto_Response> {

	private CallbackInterface<POST_API_Projecto_Response> callback;
	String nomeServico;
	POST_API_Projecto_Request prjRequest;

	public POST_API_Projecto_Service(
			CallbackInterface<POST_API_Projecto_Response> callback,
			String nomeServico, POST_API_Projecto_Request prjRequest) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.prjRequest = prjRequest;
	}

	@Override
	protected POST_API_Projecto_Response doInBackground(String... params) {
		// TODO Auto-generated method stub

		POST_API_Projecto_Response prjResponse = CommunicationCenter
				.callPostService(nomeServico, prjRequest,
						POST_API_Projecto_Response.class);
		return prjResponse;

	}

	@Override
	protected void onPostExecute(POST_API_Projecto_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}

}
