package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.POST_API_DelProjectoFromNucLst_Request;
import com.example.itlog.responseobjects.POST_API_DelProjectoFromNucLst_Response;

import android.os.AsyncTask;

public class POST_API_DelProjectoFromNucLst_Service extends
		AsyncTask<String, Void, POST_API_DelProjectoFromNucLst_Response> {

	private CallbackInterface<POST_API_DelProjectoFromNucLst_Response> callback;
	String nomeServico;
	POST_API_DelProjectoFromNucLst_Request delProjLstRqst;

	public POST_API_DelProjectoFromNucLst_Service(
			CallbackInterface<POST_API_DelProjectoFromNucLst_Response> callback,
			String nomeServico,
			POST_API_DelProjectoFromNucLst_Request delProjLstRqst) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.delProjLstRqst = delProjLstRqst;
	}

	@Override
	protected POST_API_DelProjectoFromNucLst_Response doInBackground(
			String... params) {
		// TODO Auto-generated method stub

		POST_API_DelProjectoFromNucLst_Response delProjLstResp = CommunicationCenter
				.callPostService(nomeServico, delProjLstRqst,
						POST_API_DelProjectoFromNucLst_Response.class);

		return delProjLstResp;
	}

	@Override
	protected void onPostExecute(POST_API_DelProjectoFromNucLst_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);
	}

}
