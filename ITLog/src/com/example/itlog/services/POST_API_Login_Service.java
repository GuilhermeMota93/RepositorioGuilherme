package com.example.itlog.services;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.POST_API_Login_Request;
import com.example.itlog.responseobjects.POST_API_Login_Response;

//parametro do background, parametro do onProgressUpdate, parametro do PostExecute
public class POST_API_Login_Service extends
		AsyncTask<String, Void, POST_API_Login_Response> {

	private CallbackInterface<POST_API_Login_Response> callback;
	String nomeServico;
	POST_API_Login_Request lr;

	public POST_API_Login_Service(
			CallbackInterface<POST_API_Login_Response> callback,
			String nomeServico, POST_API_Login_Request lr) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.lr = lr;
	}

	@Override
	protected POST_API_Login_Response doInBackground(String... params) {
		// TODO Auto-generated method stub
		POST_API_Login_Response lro = CommunicationCenter.callPostService(
				nomeServico, lr, POST_API_Login_Response.class);
		POST_API_Login_Response.getInstance().setToken(lro.getToken());
		POST_API_Login_Response.getInstance().setStatusCd(lro.getStatusCd());
		POST_API_Login_Response.getInstance().setStatusTxt(lro.getStatusTxt());
		return lro;
	}

	@Override
	protected void onPostExecute(POST_API_Login_Response result) {
		// TODO Auto-generated method stub
		callback.callbackCall(result);

	}

}
