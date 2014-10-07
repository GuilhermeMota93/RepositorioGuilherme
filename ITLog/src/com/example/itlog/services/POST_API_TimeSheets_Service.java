package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.POST_API_TimeSheets_Request;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.responseobjects.POST_API_TimeSheets_Response;

import android.os.AsyncTask;

public class POST_API_TimeSheets_Service extends
		AsyncTask<String, Void, POST_API_TimeSheets_Response> {

	private CallbackInterface<POST_API_TimeSheets_Response> callback;
	String nomeServico;
	POST_API_TimeSheets_Request timeSheetsServiceReqst;

	public POST_API_TimeSheets_Service(
			CallbackInterface<POST_API_TimeSheets_Response> callback,
			String nomeServico,
			POST_API_TimeSheets_Request timeSheetsServiceReqst) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.timeSheetsServiceReqst = timeSheetsServiceReqst;
	}

	@Override
	protected POST_API_TimeSheets_Response doInBackground(String... params) {
		// TODO Auto-generated method stub

		POST_API_TimeSheets_Response timeSheetsRspn = CommunicationCenter
				.callPostService(nomeServico, timeSheetsServiceReqst,
						POST_API_TimeSheets_Response.class);

		return timeSheetsRspn;
	}

	@Override
	protected void onPostExecute(POST_API_TimeSheets_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}

}
