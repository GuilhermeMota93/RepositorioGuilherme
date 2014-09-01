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
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.itlog.R;
import com.example.itlog.activities.InputHoras_Activity;

public class Calendario_Adapter extends BaseAdapter {

	private static final String TAG = null;
	private Context mContext;
	private Calendar month;
	static TextView dayView, selecionaDias;
	// instacia para mes anterior
	public GregorianCalendar pmonth;
	// instancia de mes anterior para ter View completa
	public GregorianCalendar pmonthmaxset;
	private GregorianCalendar selectedDate;
	int firstDay, maxWeeknumber, maxP, calMaxP, lastWeekDay, leftDays,
			mnthlength;
	String itemvalue, curentDateString;
	SimpleDateFormat df;
	private ArrayList<String> items;
	private static ArrayList<String> posSelecionadas = new ArrayList<String>();
	private List<String> dayString;
	private View previousView;

	public Calendario_Adapter(Context c, Calendar month2) {
		dayString = new ArrayList<String>();
		month = month2;
		selectedDate = (GregorianCalendar) month2.clone();
		mContext = c;
		month.set(GregorianCalendar.DAY_OF_MONTH, 1);
		this.items = new ArrayList<String>();
		df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		curentDateString = df.format(selectedDate.getTime());
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

		// separates daystring into parts.
		final String[] separatedTime = dayString.get(position).split("-");
		// taking last part of date. ie; 2 from 2012-12-02
		final String gridvalue = separatedTime[2].replaceFirst("^0*", "");

		// checking whether the day is in current month or not.
		if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
			// setting offdays to black color.
			// dayView.setTextColor(Color.BLACK);
			v.setVisibility(View.INVISIBLE);
		} else if ((Integer.parseInt(gridvalue) <= 14) && (position > 28)) {
			// dayView.setTextColor(Color.BLACK);
			v.setVisibility(View.INVISIBLE);
		}

		dayView.setText(gridvalue);

		// create date string for comparison
		String date = dayString.get(position);

		if (date.length() == 1)
			date = "0" + date;
		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1)
			monthStr = "0" + monthStr;
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
		maxWeeknumber = month
				.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH + 1);
		if (maxWeeknumber >= 5)
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

	// retorna arraylist que contem todas as posiçoes selecionadas
	public ArrayList<String> getArraySelecionaDias() {
		return posSelecionadas;
	}

}