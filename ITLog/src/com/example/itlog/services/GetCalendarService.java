package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.requestobjects.GetCalendarRequest;
import com.example.itlog.responseobjects.GetCalendarResponse;

import android.os.AsyncTask;

public class GetCalendarService extends
		AsyncTask<String, String, GetCalendarResponse> {

	private CallbackInterface<String> callback;

	String nomeServico;
	GetCalendarRequest gcr;

	public GetCalendarService(CallbackInterface<String> callback,
			String nomeServico, GetCalendarRequest gcr) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.gcr = gcr;
	}

	@Override
	protected GetCalendarResponse doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPostExecute(GetCalendarResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
