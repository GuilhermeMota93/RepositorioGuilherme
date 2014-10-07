package com.example.itlog.communication;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.itlog.requestobjects.POST_API_TimeSheets_Request;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.responseobjects.POST_API_TimeSheets_Response;
import com.example.itlog.services.POST_API_TimeSheets_Service;

public class GetCalendario {

	ProgressDialog progressDialog;
	private Context mContext;
	POST_API_Login_Response token;
	GetCalendarioListener listener;
	ArrayList<Calendar> listaMesesMostrar = new ArrayList<Calendar>();
	ArrayList<POST_API_TimeSheets_Response> respostasArray = new ArrayList<POST_API_TimeSheets_Response>();

	static int i;

	public GetCalendario(Context mContext, POST_API_Login_Response token,
			GetCalendarioListener listener,
			ArrayList<Calendar> listaMesesMostrar) {
		super();
		i = 0;
		this.mContext = mContext;
		this.token = token;
		this.listener = listener;
		this.listaMesesMostrar = listaMesesMostrar;

		getTimeSheets(listaMesesMostrar.get(i).get(Calendar.YEAR),
				listaMesesMostrar.get(i).get(Calendar.MONTH) + 1);
	}

	public interface GetCalendarioListener {
		public void onGetCalendarioComplete(
				ArrayList<POST_API_TimeSheets_Response> t2);

	}

	public void getTimeSheets(int ano, int mes) {

		progressDialog = ProgressDialog.show(mContext, "Um momento, por favor",
				"A carregar dados...", true);
		progressDialog.setCancelable(true);

		new POST_API_TimeSheets_Service(new CallbackTimeSheets(),
				CommunicationCenter.PostTimeSheets,
				new POST_API_TimeSheets_Request(ano, mes, token.getToken()))
				.execute(new String[0]);

	}

	public class CallbackTimeSheets implements
			CallbackInterface<POST_API_TimeSheets_Response> {
		@Override
		public void callbackCall(POST_API_TimeSheets_Response t2) {
			// TODO Auto-generated method stub
			respostasArray.add(t2);
			i++;
			if (i < 12) {
				getTimeSheets(listaMesesMostrar.get(i).get(Calendar.YEAR),
						listaMesesMostrar.get(i).get(Calendar.MONTH) + 1);
			} else {
				listener.onGetCalendarioComplete(respostasArray);
			}
			progressDialog.dismiss();
		}

	}

}
