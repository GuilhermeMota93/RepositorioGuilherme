package com.example.itlog.services;

import android.os.AsyncTask;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.ListProjectUserRequest;
import com.example.itlog.responseobjects.ListProjectsUserResponse;

public class ListProjectsUserService extends
		AsyncTask<String, Void, ListProjectsUserResponse> {

	private CallbackInterface<String> callback;

	String nomeServico;
	ListProjectUserRequest lpur;

	public ListProjectsUserService(CallbackInterface<String> callback,
			String nomeServico, ListProjectUserRequest lpur) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.lpur = lpur;
	}

	@Override
	protected ListProjectsUserResponse doInBackground(String... params) {

		// que fazer aqui? Se for callGetServoce receber um String [] info ?!
		ListProjectsUserResponse listProjectUser = CommunicationCenter
				.callPostService(nomeServico, lpur,
						ListProjectsUserResponse.class);

		return listProjectUser;

	}

	@Override
	protected void onPostExecute(ListProjectsUserResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
