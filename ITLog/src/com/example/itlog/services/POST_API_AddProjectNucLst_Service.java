package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.POST_API_AddProjectToNucLst_Request;
import com.example.itlog.responseobjects.POST_API_AddProjectNucLst_Response;

import android.os.AsyncTask;

public class POST_API_AddProjectNucLst_Service extends
		AsyncTask<String, String, POST_API_AddProjectNucLst_Response> {

	private CallbackInterface<POST_API_AddProjectNucLst_Response> callback;
	String nomeServico;
	POST_API_AddProjectToNucLst_Request apnlr;

	public POST_API_AddProjectNucLst_Service(
			CallbackInterface<POST_API_AddProjectNucLst_Response> callback,
			String nomeServico, POST_API_AddProjectToNucLst_Request apnlr) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.apnlr = apnlr;
	}

	@Override
	protected POST_API_AddProjectNucLst_Response doInBackground(String... params) {
		// TODO Auto-generated method stub
		POST_API_AddProjectNucLst_Response apnlrResponse = CommunicationCenter
				.callPostService(nomeServico, apnlr,
						POST_API_AddProjectNucLst_Response.class);

		return apnlrResponse;

	}

	@Override
	protected void onPostExecute(POST_API_AddProjectNucLst_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}

}
