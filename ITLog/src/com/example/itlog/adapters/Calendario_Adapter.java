package com.example.itlog.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerTabStrip;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.activities.InputHoras_Activity;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.objects.TimeSheet;
import com.example.itlog.objects.TimeSheetAllocate;
import com.example.itlog.objects.TimeSheetDay;
import com.example.itlog.objects.TimeSheetPut;
import com.example.itlog.requestobjects.POST_API_TimeSheetsPut_Request;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.responseobjects.POST_API_TimeSheetsPut_Response;
import com.example.itlog.responseobjects.POST_API_TimeSheets_Response;
import com.example.itlog.services.POST_API_TimeSheetsPut_Service;

public class Calendario_Adapter extends BaseAdapter {

	POST_API_Login_Response token = POST_API_Login_Response.getInstance();
	private static final String TAG = null;
	private Context mContext;
	private Calendar month;
	TextView textoDia, selecionaDias;
	// instancia de mes anterior para ter View completa
	public GregorianCalendar pmonth, pmonthmaxset, selectedDate;
	int firstDay, maxWeeknumber, maxP, calMaxP, lastWeekDay, leftDays,
			mnthlength;

	private Calendar calendarioUsado;
	String itemvalue, curentDateString;
	SimpleDateFormat dataFormat;
	private ArrayList<String> items;

	private List<String> listaDiasPopular;
	private View previousView;
	String[] dataSeparada = new String[3];

	private ArrayList<View> listaViewsEliminar = new ArrayList<View>();
	ProgressDialog progressDialog;
	private POST_API_TimeSheets_Response respostaDoCallbackTS;
	private POST_API_TimeSheetsPut_Response respostaDoCallbackTSPut;

	ArrayList<TimeSheet> tamanhoArray = new ArrayList<TimeSheet>();
	private static ArrayList<View> listaViewsPreencher = new ArrayList<View>();
	private String tempoMes;
	private String tempoDia;
	private static ArrayList<String> posSelecionadas = new ArrayList<String>();
	ArrayList<Boolean> listaPopuladaBool = new ArrayList<Boolean>();
	ArrayList<String> listaPopuladaString = new ArrayList<String>();
	// ArrayList<Integer> lista
	int contadorHoras = 0;

	InputHoras_Activity inputHorasObjecto;

	public Calendario_Adapter(Context c, Calendar month2,
			POST_API_TimeSheets_Response respostaDoCallbackTS) {

		this.respostaDoCallbackTS = respostaDoCallbackTS;
		// CallbackTimeSheets callbackTS = new CallbackTimeSheets();
		// callbackTS.callbackCall(respostaDoCallbackTS);
		calendarioUsado = (Calendar) month2.clone();
		listaDiasPopular = new ArrayList<String>();
		month = month2;
		mContext = c;
		month.set(GregorianCalendar.DAY_OF_MONTH, 1);
		this.items = new ArrayList<String>();
		dataFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		curentDateString = dataFormat.format(calendarioUsado.getTime());
		try {

			refreshDays();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// getTimeSheets(calendarioUsado.get(Calendar.YEAR),
		// calendarioUsado.get(Calendar.MONTH) + 1);

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
		return listaDiasPopular.size();
	}

	public Object getItem(int position) {
		return listaDiasPopular.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new view for each item referenced by the Adapter
	public View getView(final int position, View convertView, ViewGroup parent) {

		View currentView = convertView;
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			currentView = vi.inflate(R.layout.gridview_item, null);

		}
		textoDia = (TextView) currentView
				.findViewById(R.id.textViewCalendarItem2);
		selecionaDias = (TextView) currentView
				.findViewById(R.id.textViewCalendarItem3);

		// separates daystring into parts.
		try {
			dataSeparada = listaDiasPopular.get(position).split("-");
		} catch (IndexOutOfBoundsException e) {
			return currentView;
		}

		// String value do mes atual ex: ie; 12 from 2012-12-02
		tempoMes = dataSeparada[1].replaceFirst("^0*", "");
		// taking last part of date. ie; 2 from 2012-12-02
		tempoDia = dataSeparada[2].replaceFirst("^0*", "");

		// textoDia.setText(tempoDia + "-" + calendarioUsado.get(Calendar.MONTH)
		// + "-" + tempoMes);
		textoDia.setText(tempoDia);

		// create date string for comparison
		String date = listaDiasPopular.get(position);

		if (date.length() == 1)
			date = "0" + date;
		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1)
			monthStr = "0" + monthStr;

		// POE A INVISIVEL AS VIEWS QUE NAO FAZEM PARTE DO MES
		if ((Integer.parseInt(tempoMes) != calendarioUsado.get(Calendar.MONTH) + 1)) {
			currentView.setVisibility(View.INVISIBLE);
		} else if (respostaDoCallbackTS != null) {
			// COMPARAR DIA DO MES ATUAL COM DIA DO SERVICO
			selecionaDias.setText(listaPopuladaString.get(position));
			for (int i = 0; i < respostaDoCallbackTS.getImpt().getDia().size(); i++) {
				TimeSheetDay dia = respostaDoCallbackTS.getImpt().getDia()
						.get(i);
				if (Integer.parseInt(tempoDia) == dia.getDia()) {
					for (int j = 0; j < dia.getDiaAllocate().size(); j++) {
						TimeSheetAllocate diaAllocate = dia.getDiaAllocate()
								.get(j);

						checkHorasNoDia(contadorHoras, dia, diaAllocate,
								currentView);
						checkIfFeriado(dia, currentView, diaAllocate);
						checkIfAvlAllocate(contadorHoras,dia, currentView, diaAllocate);
						checkAllocType(dia, diaAllocate, currentView);

					}
					break;
				}
			}
		}
		verificaNumero(position);
		return currentView;
	}

	public void checkIfFeriado(TimeSheetDay dia, View currentView,
			TimeSheetAllocate diaAllocate) {
		if (dia.isDiaFeriado()) {
			selecionaDias.setText(String.valueOf(diaAllocate.getHoras()));
			currentView.setBackgroundColor(Color.RED);
			currentView.setSelected(false);
			currentView.setClickable(false);
		}
	}

	public void checkIfAvlAllocate(int contadorHoras,TimeSheetDay dia, View currentView,
			TimeSheetAllocate diaAllocate) {
		if (dia.isDiaAvlbToAllocate()) {
			if (contadorHoras == 4) {
				selecionaDias.setText(String.valueOf(diaAllocate.getHoras()));
//				currentView.setBackgroundColor(Color.BLUE);
			} else if (contadorHoras == 8) {
				selecionaDias.setText(String.valueOf(diaAllocate.getHoras()));
				selecionaDias.setClickable(false);
				currentView.setClickable(false);
//				currentView.setBackgroundColor(Color.YELLOW);
			}

		}

	}

	public void checkHorasNoDia(int contadorHoras, TimeSheetDay dia,
			TimeSheetAllocate diaAllocate, View v) {
		contadorHoras += diaAllocate.getHoras();

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
	}

	// Dia selecionado!
	public View setSelected(View view, String position, int position2) {
		if (posSelecionadas.contains(position)) {
			view.setBackgroundColor(Color.WHITE);
			posSelecionadas.remove(position);
			listaViewsPreencher.remove(view);
			listaPopuladaBool.set(position2, false);

		} else {
			posSelecionadas.add(position);
			listaViewsPreencher.add(view);
			view.setBackgroundColor(Color.GRAY);
			listaPopuladaBool.set(position2, true);
		}

		return view;
	}

	public void refreshDays() throws ParseException {

		Date data;
		Calendar calendario;
		// clear items
		items.clear();
		listaDiasPopular.clear();
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
			itemvalue = dataFormat.format(pmonthmaxset.getTime());
			pmonthmaxset.add(GregorianCalendar.DATE, 1);

			data = (Date) dataFormat.parse(itemvalue);
			calendario = Calendar.getInstance();
			calendario.setTime(data);

			if (calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				// lista de String com data
				listaDiasPopular.add(itemvalue);
				// lista de Bool para ver se foi selected
				listaPopuladaBool.add(false);
				// lista de String para escrever 4 ou 8
				listaPopuladaString.add(" ");
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
		return listaDiasPopular.get(pos);
	}

	// retorna arraylist que contem todas as posiçoes selecionadas no formato
	// YYYY-MM-DD
	public ArrayList<String> getArraySelecionaDias() {
		return posSelecionadas;
	}

	public ArrayList<View> getViewsPreencher() {
		return listaViewsPreencher;
	}

	// metodo void setSelected4 (sem parametros)
	public void escreveQuatro() {
		for (int i = 0; i < listaPopuladaBool.size(); i++) {
			if (listaPopuladaBool.get(i) == true) {
				listaPopuladaString.set(i, "4");
				listaPopuladaBool.set(i, false);
			}
		}
		notifyDataSetChanged();
	}

	public void escreveOito() {
		for (int i = 0; i < listaPopuladaBool.size(); i++) {
			if (listaPopuladaBool.get(i) == true) {
				listaPopuladaString.set(i, "8");
				listaPopuladaBool.set(i, false);
			}
		}
		notifyDataSetChanged();
	}

	public void escreveZero() {
		for (int i = 0; i < listaPopuladaBool.size(); i++) {
			if (listaPopuladaBool.get(i) == true) {
				listaPopuladaString.set(i, " ");
				listaPopuladaBool.set(i, false);
			}
		}
		notifyDataSetChanged();
	}

	public void verificaNumero(int position) {
		if (listaPopuladaString.get(position).equals("8")) {
			selecionaDias.setBackgroundColor(Color.rgb(153, 242, 131));
		} else if (listaPopuladaString.get(position).equals("4")) {
			selecionaDias.setBackgroundColor(Color.rgb(141, 240, 189));
		} else if (listaPopuladaString.get(position).equals("Limpar")) {
			selecionaDias.setBackgroundColor(Color.parseColor("#ebeaed"));
		} else {
			selecionaDias.setBackgroundColor(Color.rgb(250, 250, 250));
		}

	}

	public ArrayList<String> getListaPopuladaString() {
		return listaPopuladaString;
	}
}