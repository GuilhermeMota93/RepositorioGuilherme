package com.example.itlog.communication;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.widget.Toast;

import com.example.itlog.activities.Info_Activity;
import com.example.itlog.adapters.ViewPager_Adapter;
import com.example.itlog.objects.TimeSheet;
import com.example.itlog.requestobjects.POST_API_TimeSheets_Request;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.responseobjects.POST_API_TimeSheets_Response;
import com.example.itlog.services.POST_API_TimeSheets_Service;

public class ClasseMeses {

	POST_API_Login_Response token = POST_API_Login_Response.getInstance();
	Context context;
	static ArrayList<Calendar> listaMeses = new ArrayList<Calendar>();
	static ViewPager_Adapter viewPager;
	static ArrayList<TimeSheet> listaMesesImpt = new ArrayList<TimeSheet>();

	public ClasseMeses(Context context, ArrayList<Calendar> listaMeses) {
		super();
		this.context = context;
		this.listaMeses = listaMeses;
		
	}

	public void populaMesesEstatico() {
		listaMeses = viewPager.getListaMesesMostrar();
		for (int i = 0; i < listaMeses.size(); i++) {
			getTimeSheets(listaMeses.get(i).get(Calendar.YEAR),
					listaMeses.get(i).get(Calendar.MONTH) + 1);
			}
	}

	public void getTimeSheets(int ano, int mes) {

		// progressDialog = ProgressDialog.show(mContext, "Aguarde, por favor",
		// "A carregar dados...", true);
		// progressDialog.setCancelable(true);

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
			// respostaDoCallbackTS = t2;
			if (t2.getStatusCd().equals("KO")) {
				Toast.makeText(context, "ERRO AO CARREGAR MES",
						Toast.LENGTH_LONG).show();
			} else if (t2.getStatusCd().equals("OK")) {
				Toast.makeText(
						context,
						"Ano: " + t2.getImpt().getAno() + "\n" + "Mes: "
								+ t2.getImpt().getMes(), Toast.LENGTH_LONG)
						.show();
			}
			listaMesesImpt.add(t2.getImpt());
		}
	}

}
