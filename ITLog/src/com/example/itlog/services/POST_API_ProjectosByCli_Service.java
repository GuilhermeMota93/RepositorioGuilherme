package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.POST_API_ProjectosByCli_Request;
import com.example.itlog.responseobjects.POST_API_ProjectosByCli_Response;

import android.os.AsyncTask;

public class POST_API_ProjectosByCli_Service extends
		AsyncTask<String, Void, POST_API_ProjectosByCli_Response> {

	private CallbackInterface<POST_API_ProjectosByCli_Response> callback;
	String nomeServico;
	POST_API_ProjectosByCli_Request projByCliRequest;

	public POST_API_ProjectosByCli_Service(
			CallbackInterface<POST_API_ProjectosByCli_Response> callback,
			String nomeServico, POST_API_ProjectosByCli_Request projByCliRequest) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.projByCliRequest = projByCliRequest;
	}

	@Override
	protected POST_API_ProjectosByCli_Response doInBackground(String... params) {
		// TODO Auto-generated method stub

		POST_API_ProjectosByCli_Response projByCliResponse = CommunicationCenter
				.callPostService(nomeServico, projByCliRequest,
						POST_API_ProjectosByCli_Response.class);

		return projByCliResponse;
	}

	@Override
	protected void onPostExecute(POST_API_ProjectosByCli_Response result) {
		// TODO Auto-generated method stub

		callback.callbackCall(result);
	}

}
