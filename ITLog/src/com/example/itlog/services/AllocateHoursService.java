package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.requestobjects.AllocateHoursRequest;
import com.example.itlog.responseobjects.AllocateHoursResponse;

import android.os.AsyncTask;

public class AllocateHoursService extends
		AsyncTask<String, String, AllocateHoursResponse> {

	private CallbackInterface<String> callback;

	String nomeServico;
	AllocateHoursRequest ahr;

	public AllocateHoursService(CallbackInterface<String> callback,
			String nomeServico, AllocateHoursRequest ahr) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.ahr = ahr;
	}

	@Override
	protected void onPostExecute(AllocateHoursResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected AllocateHoursResponse doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
