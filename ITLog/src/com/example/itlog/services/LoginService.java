package com.example.itlog.services;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.LoginRequest;
import com.example.itlog.responseobjects.LoginResponse;
//parametro do background, parametro do onProgressUpdate, parametro do PostExecute
public class LoginService extends AsyncTask<String, Void, LoginResponse> {

	private CallbackInterface<String> callback;

	String nomeServico;
	LoginRequest lr;

	public LoginService(String nomeServico, LoginRequest lr) {
		this.nomeServico = nomeServico;
		this.lr = lr;
	}

	@Override
	protected LoginResponse doInBackground(String... params) {
		// TODO Auto-generated method stub
		CommunicationCenter.callPostService(nomeServico, lr,
				LoginResponse.class);
		return null;
	}
	
	

	@Override
	protected void onPostExecute(LoginResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	
		/*if(status.equals("OK")){
     //navigate to Main Menu
    Intent i = new Intent(LoginActivity.this, InfoActivity.class);
     startActivity(i);
    }
    else{
     //dar em POPUP msg de erro("Sorry!! Incorrect Username or Password");
       */  
//		Toast.makeText(/*context aqui*/, "Wrong Credentials",
//			      Toast.LENGTH_SHORT).show();
//	}
	}

}
