package com.example.itlog.adapters;

import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.R.integer;
import android.R.string;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.activities.InputHoras_Activity;
import com.example.itlog.activities.MeusProj_Activity;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.objects.TimeSheet;
import com.example.itlog.objects.TimeSheetDay;
import com.example.itlog.requestobjects.POST_API_TimeSheets_Request;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.responseobjects.POST_API_TimeSheets_Response;
import com.example.itlog.services.POST_API_AddProjectNucLst_Service;
import com.example.itlog.services.POST_API_TimeSheets_Service;

public class Calendario_Adapter extends BaseAdapter {

	POST_API_Login_Response token = POST_API_Login_Response.getInstance();
	private static final String TAG = null;
	private Context mContext;
	private Calendar month;
	static TextView dayView, selecionaDias;
	// instancia de mes anterior para ter View completa
	public GregorianCalendar pmonth, pmonthmaxset, selectedDate;
	int firstDay, maxWeeknumber, maxP, calMaxP, lastWeekDay, leftDays,
			mnthlength;

	private Calendar testeCal;
	String itemvalue, curentDateString;
	SimpleDateFormat df;
	private ArrayList<String> items;
	private static ArrayList<String> posSelecionadas = new ArrayList<String>();
	private List<String> dayString;
	private View previousView;
	String gridvalue, gridvalueMes;
	int count = 0;
	String[] separatedTime = new String[3];

	private ArrayList<View> listaViewsEliminar = new ArrayList<View>();
	ProgressDialog progressDialog;
	private POST_API_TimeSheets_Response resposta;

	ArrayList<TimeSheet> tamanhoArray = new ArrayList<TimeSheet>();

	public Calendario_Adapter(Context c, Calendar month2) {
		testeCal = (Calendar) month2.clone();

		dayString = new ArrayList<String>();
		month = month2;
		// selectedDate = (GregorianCalendar) month2.clone();
		mContext = c;
		getTimeSheets(testeCal.get(Calendar.YEAR),
				testeCal.get(Calendar.MONTH) + 1);
		month.set(GregorianCalendar.DAY_OF_MONTH, 1);
		this.items = new ArrayList<String>();
		df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		curentDateString = df.format(testeCal.getTime());
		try {

			refreshDays();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setItems(ArrayList<String> items) {
		for (int i = 0; i != items.size(); i++) {
			if (items.get(i).length() == 1) {
				items.set(i, "0" + items.get(i));
			}
		}
		this.items = items;
	}

	public int getCount() {
		return dayString.size();
	}

	public Object getItem(int position) {
		return dayString.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new view for each item referenced by the Adapter
	public View getView(final int position, View convertView, ViewGroup parent) {
		// ViewHolder holder = null;
		View v = convertView;
		if (convertView == null) {
			// holder = new ViewHolder();
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.gridview_item, null);
		}

		dayView = (TextView) v.findViewById(R.id.textViewCalendarItem2);
		selecionaDias = (TextView) v.findViewById(R.id.textViewCalendarItem3);

//		// Ao carregar no botao 4 horas
//		Button bt1 = (Button) convertView.findViewById(R.id.botaoQuatroHoras);
//		bt1.setOnClickListener(new Button.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//				((InputHoras_Activity)mContext).
//				
//				selecionaDias.setText("4");
//				v.setBackgroundColor(Color.GREEN);
//			}
//		});
//
//		Button bt2 = (Button) convertView.findViewById(R.id.botaoOitoHoras);
//		bt2.setOnClickListener(new Button.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				selecionaDias.setText("8");
//				v.setBackgroundColor(Color.GREEN);
//			}
//		});

		// separates daystring into parts.
		try {
			separatedTime = dayString.get(position).split("-");
		} catch (IndexOutOfBoundsException e) {
			return v;
		}

		// String value do mes atual ex: ie; 12 from 2012-12-02
		gridvalueMes = separatedTime[1].replaceFirst("^0*", "");
		// taking last part of date. ie; 2 from 2012-12-02
		gridvalue = separatedTime[2].replaceFirst("^0*", "");

		// POE A INVISIVEL AS VIEWS QUE NAO FAZEM PARTE DO MES
		if ((Integer.parseInt(gridvalueMes) != testeCal.get(Calendar.MONTH) + 1)) {
			v.setVisibility(View.INVISIBLE);
			// AS QUE FAZEM
		} else if (resposta != null) {

			int tamanhoArrayDia = resposta.getImpt().getDia().size();
			// //COMPARAR DIA DO MES ATUAL COM DIA DO SERVICO
			for (int i = 0; i < tamanhoArrayDia; i++) {
				if (Integer.parseInt(gridvalue) == resposta.getImpt().getDia()
						.get(i).getDia()) {
					// validacoes
					int tamanhoArrayAllocate = resposta.getImpt().getDia()
							.get(i).getDiaAllocate().size();
					// se for feriado, nao permite clickar e pinta de preto
					if (resposta.getImpt().getDia().get(i).isDiaFeriado() == true) {
						v.setClickable(false);
						v.setBackgroundColor(0x0A0A0A);
						// se tipo de aloca��o for 3 (horas aprovadas) e tiver
						// horas noutros projecto
					} else {
						// "entra" dentro do array Impt -> Dia -> DiaAllocate
						for (int j = 0; j < tamanhoArrayAllocate; j++) {
							int contadorHoras = 0;
							if (resposta.getImpt().getDia().get(i)
									.getDiaAllocate().get(j).getAllocType() == 3) {
								v.setClickable(false);
								// cor verde
								v.setBackgroundColor(0x3CF005);
							} else if (resposta.getImpt().getDia().get(i)
									.getDiaAllocate().get(j).getAllocType() == 2) {
								v.setClickable(false);
								// cor vermelha
								v.setBackgroundColor(0xF00505);
							} else if (resposta.getImpt().getDia().get(i)
									.getDiaAllocate().get(j).getAllocType() == 1) {
								v.setClickable(false);
								// cor azul
								v.setBackgroundColor(0x1D05F0);
							} else if (!resposta.getImpt().getDia().get(i)
									.getDiaAllocate().get(j).getClass()
									.equals("")) {
								// conta as horas no projecto?
								contadorHoras += resposta.getImpt().getDia()
										.get(i).getDiaAllocate().get(j)
										.getHoras();
							}
							// FALTA PARA ALLOCTYPE = 0 -> que significa?
						}
					}

				}
			}

		}

		// for (TimeSheetDay auxiliar : resposta.getImpt().getDia()) {
		//
		// }

		// estas views numa lista
		// listaViewsEliminar.add(v);
		// conta
		// count++;
		// } else {
		// // ver se count chegou a 5 (fim da row)
		// if (count == 5) {
		// // se sim, GONE em todas as views (as 5)
		// for (View v2 : listaViewsEliminar)
		// v.setVisibility(View.GONE);
		// for (int j = 0; j < 4; j++) {
		// dayString.remove(0);
		// }
		// notifyDataSetChanged();
		// return v;
		// }
		// // break ao count. flag para parar
		//
		// }

		dayView.setText(gridvalue);

		// create date string for comparison
		String date = dayString.get(position);

		if (date.length() == 1)
			date = "0" + date;
		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1)
			monthStr = "0" + monthStr;

		// resposta.getImpt().getDia();
		// notifyDataSetChanged();
		return v;
	}

	// Dia selecionado!
	public View setSelected(View view, String position) {
		if (posSelecionadas.contains(position)) {
			view.setBackgroundResource(R.drawable.selector_gridview_item);
			posSelecionadas.remove(position);
		} else {
			posSelecionadas.add(position);
			view.setBackgroundResource(R.drawable.pressed_color);
		}

		return view;
	}

	public void refreshDays() throws ParseException {

		Date data;
		Calendar calendario;
		// clear items
		items.clear();
		dayString.clear();
		pmonth = (GregorianCalendar) month.clone();
		// month start day. ie; sun, mon, etc
		firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
		// finding number of weeks in current month.
		maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
		if (maxWeeknumber >= 6)
			maxWeeknumber = 5;
		// allocating maximum row number for the gridview.
		mnthlength = maxWeeknumber * 7;
		maxP = getMaxP(); // previous month maximum day 31,30....
		calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
		/**
		 * Calendar instance for getting a complete gridview including the three
		 * month's (previous,current,next) dates.
		 */
		pmonthmaxset = (GregorianCalendar) pmonth.clone();
		/**
		 * setting the start date as previous month's required date.
		 */
		pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

		/**
		 * filling calendar gridview.
		 */

		for (int n = 0; n < mnthlength; n++) {

			// Mes sem fins de semana para dentro de "dayString"
			itemvalue = df.format(pmonthmaxset.getTime());
			pmonthmaxset.add(GregorianCalendar.DATE, 1);

			data = (Date) df.parse(itemvalue);
			calendario = Calendar.getInstance();
			calendario.setTime(data);

			// Porque usar "&&" e nao "||"
			if (calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				dayString.add(itemvalue);
			}

		}

	}

	private int getMaxP() {
		int maxP;
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			pmonth.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}
		maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		return maxP;
	}

	// posicao do dia
	public String getDayString(int pos) {
		return dayString.get(pos);
	}

	// retorna arraylist que contem todas as posi�oes selecionadas no formato
	// YYYY-MM-DD
	public ArrayList<String> getArraySelecionaDias() {
		return posSelecionadas;
	}

	public void getTimeSheets(int ano, int mes) {

		progressDialog = ProgressDialog.show(mContext, "Aguarde, por favor",
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

			if (t2.getStatusCd().equals("KO")) {
				Toast.makeText(mContext, "ERRO AO CARREGAR MES",
						Toast.LENGTH_LONG).show();
			} else if (t2.getStatusCd().equals("OK")) {
				Toast.makeText(
						mContext,
						"Ano: " + t2.getImpt().getAno() + "\n" + "Mes: "
								+ t2.getImpt().getMes() + "\n"
								+ "Horas Dia Multiplo: "
								+ t2.getImpt().getHorasDiaMultiplo() + "\n"
								+ "Horas Dia Max: "
								+ t2.getImpt().getHorasDiaMax(),
						Toast.LENGTH_LONG).show();
			}

			progressDialog.dismiss();

		}
	}

	// private class TaskService extends AsyncTask<Void, Void, Void> {
	//
	// @Override
	// protected void onPreExecute() {
	// // TODO Auto-generated method stub
	// // super.onPreExecute();
	//
	// }
	//
	// @Override
	// protected void onPostExecute(Void result) {
	// // TODO Auto-generated method stub
	// progressDialog.dismiss();
	// }
	//
	// @Override
	// protected Void doInBackground(Void... params) {
	// // TODO Auto-generated method stub
	//
	// return null;
	// }
	//
	// }

}