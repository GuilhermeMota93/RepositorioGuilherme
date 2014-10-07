package com.example.itlog.services;

import android.os.AsyncTask;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.responseobjects.GET_API_SessionInfo_Response;
import com.example.itlog.responseobjects.POST_API_Login_Response;

public class GET_API_SessionInfo_Service extends
		AsyncTask<String, String, GET_API_SessionInfo_Response> {

	private CallbackInterface<GET_API_SessionInfo_Response> callback;
	String nomeServico;

	public GET_API_SessionInfo_Service(
			CallbackInterface<GET_API_SessionInfo_Response> callback,
			String nomeServico) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;

	}

	@Override
	protected GET_API_SessionInfo_Response doInBackground(String... params) {
		// TODO Auto-generated method stub
		GET_API_SessionInfo_Response getSesResponse = CommunicationCenter
				.callGetService(nomeServico, params,
						GET_API_SessionInfo_Response.class);

		return getSesResponse;
	}

	@Override
	protected void onPostExecute(GET_API_SessionInfo_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}

}
