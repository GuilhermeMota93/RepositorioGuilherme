package com.example.itlog.services;

import android.os.AsyncTask;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.requestobjects.ListAllProjectsRequest;
import com.example.itlog.responseobjects.ListAllProjectsResponse;

public class ListAllProjectsService extends AsyncTask<String, String, ListAllProjectsResponse>{
	
	private CallbackInterface<String> callback;
	
	String nomeServico;
	ListAllProjectsRequest lapr;
	
	public ListAllProjectsService(CallbackInterface<String> callback,
			String nomeServico, ListAllProjectsRequest lapr) {
		super();
		this.callback = callback;//callback vale a pena?
		this.nomeServico = nomeServico;
		this.lapr = lapr;
	}

	@Override
	protected void onPostExecute(ListAllProjectsResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected ListAllProjectsResponse doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
