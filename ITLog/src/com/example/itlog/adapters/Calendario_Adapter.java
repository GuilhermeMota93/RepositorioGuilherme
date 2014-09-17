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
import com.example.itlog.objects.TimeSheetAllocate;
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
		View currentView = convertView;
		if (convertView == null) {
			// holder = new ViewHolder();
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			currentView = vi.inflate(R.layout.gridview_item, null);
		}

		dayView = (TextView) currentView
				.findViewById(R.id.textViewCalendarItem2);
		selecionaDias = (TextView) currentView
				.findViewById(R.id.textViewCalendarItem3);

		// // Ao carregar no botao 4 horas
		// Button bt1 = (Button)
		// convertView.findViewById(R.id.botaoQuatroHoras);
		// bt1.setOnClickListener(new Button.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// ((InputHoras_Activity)mContext).
		//
		// selecionaDias.setText("4");
		// v.setBackgroundColor(Color.GREEN);
		// }
		// });
		//
		// Button bt2 = (Button) convertView.findViewById(R.id.botaoOitoHoras);
		// bt2.setOnClickListener(new Button.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// selecionaDias.setText("8");
		// v.setBackgroundColor(Color.GREEN);
		// }
		// });

		// separates daystring into parts.
		try {
			separatedTime = dayString.get(position).split("-");
		} catch (IndexOutOfBoundsException e) {
			return currentView;
		}

		// String value do mes atual ex: ie; 12 from 2012-12-02
		gridvalueMes = separatedTime[1].replaceFirst("^0*", "");
		// taking last part of date. ie; 2 from 2012-12-02
		gridvalue = separatedTime[2].replaceFirst("^0*", "");

		// POE A INVISIVEL AS VIEWS QUE NAO FAZEM PARTE DO MES
		if ((Integer.parseInt(gridvalueMes) != testeCal.get(Calendar.MONTH) + 1)) {
			currentView.setVisibility(View.INVISIBLE);
			// AS QUE FAZEM
			// (Integer.parseInt(gridvalueMes) == testeCal
			// .get(Calendar.MONTH) + 1) &&
		} else if (resposta != null) {
			// tamanho do arraylist "Dia" dentro de "Impt"

			// COMPARAR DIA DO MES ATUAL COM DIA DO SERVICO
			for (int i = 0; i < resposta.getImpt().getDia().size(); i++) {
				// Compara dia do calendario com dia do serviço
				// if (Integer.parseInt(gridvalue) ==
				// resposta.getImpt().getDia()
				// .get(i).getDia()) {
				// Verifica se "Dia" é feriado ou nao se pode imputar

				TimeSheetDay dia = resposta.getImpt().getDia().get(i);

				if (Integer.parseInt(gridvalue) == dia.getDia()) {
					checkIfFeriadoOrNotAvlAllocate(dia, currentView);
					// tamanho do arraylist "DiaAllocate", dentro de "Dia"
					// "entra" dentro de "DiaAllocate"
					for (int j = 0; j < dia.getDiaAllocate().size(); j++) {

						TimeSheetAllocate diaAllocate = dia.getDiaAllocate()
								.get(j);

						int contadorHoras = 0;
						checkAllocType(dia, diaAllocate, currentView);
						checkHorasNoDia(contadorHoras, dia, diaAllocate,
								currentView);
					}
				}
			}

		}
		dayView.setText(gridvalue);

		// create date string for comparison
		String date = dayString.get(position);

		if (date.length() == 1)
			date = "0" + date;
		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1)
			monthStr = "0" + monthStr;

		return currentView;
	}

	public void checkIfFeriadoOrNotAvlAllocate(TimeSheetDay dia,
			View currentView) {
		// se for feriado ou, dia que nao permite alocar horas, nao
		// permite clickar e pinta de preto
		if (dia.isDiaFeriado() || !dia.isDiaAvlbToAllocate()) {
			// v.setBackgroundResource(R.drawable.feriado_naoaloca_bloq_preto);
			currentView.setVisibility(View.INVISIBLE);
		}
	}

	public void checkHorasNoDia(int contadorHoras, TimeSheetDay dia,
			TimeSheetAllocate diaAllocate, View v) {
		// conta as horas no projecto?
		contadorHoras += diaAllocate.getHoras();

	}

	public void checkIfProject() {

	}

	public void checkAllocType(TimeSheetDay dia, TimeSheetAllocate diaAllocate,
			View currentView) {

		// se ja tiver horas aprovadas
		if (diaAllocate.getAllocType() == 3) {
			// muda cor e nao deixa click
			currentView.setClickable(false);
			currentView.setBackgroundResource(R.drawable.horas_aprovadas_cor);
		} else if (diaAllocate.getAllocType() == 2) {
			// muda cor e nao deixa click
			currentView.setClickable(false);
			currentView.setBackgroundResource(R.drawable.horas_rejeitadas_cor);
		} else if (diaAllocate.getAllocType() == 1) {
			// muda cor e nao deixa click
			currentView.setClickable(false);
			currentView
					.setBackgroundResource(R.drawable.horas_impt_pode_alterar);
		}
		// FALTA PARA ALLOCTYPE = 0 -> que significa?
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

	// retorna arraylist que contem todas as posiçoes selecionadas no formato
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
			resposta = t2;
			if (t2.getStatusCd().equals("KO")) {
				Toast.makeText(mContext, "ERRO AO CARREGAR MES",
						Toast.LENGTH_LONG).show();
			} else if (t2.getStatusCd().equals("OK")) {
				Toast.makeText(
						mContext,
						"Ano: " + t2.getImpt().getAno() + "\n" + "Mes: "
								+ t2.getImpt().getMes() + "\n",
						// + "Horas Dia Multiplo: "
						// + t2.getImpt().getHorasDiaMultiplo() + "\n"
						// + "Horas Dia Max: "
						// + t2.getImpt().getHorasDiaMax(),
						Toast.LENGTH_LONG).show();
			}

			progressDialog.dismiss();

		}
	}
}