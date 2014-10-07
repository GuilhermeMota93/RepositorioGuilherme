package com.example.itlog.services;

import android.os.AsyncTask;


import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.POST_API_TimeSheetsPut_Request;
import com.example.itlog.responseobjects.POST_API_TimeSheetsPut_Response;

public class POST_API_TimeSheetsPut_Service extends
		AsyncTask<String, Void, POST_API_TimeSheetsPut_Response> {

	private CallbackInterface<POST_API_TimeSheetsPut_Response> callback;
	String nomeServico;
	POST_API_TimeSheetsPut_Request timeSheetsPutRequest;

	public POST_API_TimeSheetsPut_Service(
			CallbackInterface<POST_API_TimeSheetsPut_Response> callback,
			String nomeServico,
			POST_API_TimeSheetsPut_Request timeSheetsPutRequest) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.timeSheetsPutRequest = timeSheetsPutRequest;
	}

	@Override
	protected POST_API_TimeSheetsPut_Response doInBackground(String... params) {
		// TODO Auto-generated method stub
		POST_API_TimeSheetsPut_Response timeSheetsPutRsp = CommunicationCenter
				.callPostService(nomeServico, timeSheetsPutRequest,
						POST_API_TimeSheetsPut_Response.class);

		return timeSheetsPutRsp;
	}

	@Override
	protected void onPostExecute(POST_API_TimeSheetsPut_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}

}
