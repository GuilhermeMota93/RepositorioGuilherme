package com.example.itlog.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.itlog.R;
import com.example.itlog.objects.Client;
import com.example.itlog.objects.Cliente_2;

public class AddProject_Spinner_Adapter extends ArrayAdapter<Cliente_2> {

	ArrayList<Cliente_2> company;
	private int recurso;
	LayoutInflater inflater;
	boolean notifyOnChange = true;
	Typeface font;

	public AddProject_Spinner_Adapter(final Context context, final int recurso,
			ArrayList<Cliente_2> company) {
		super(context, R.layout.addproj_layout, company);
		this.company = company;
		this.recurso = recurso;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	public AddProject_Spinner_Adapter(final Context context, final int recurso,
			ArrayList<Cliente_2> company, Typeface font) {
		super(context, R.layout.addproj_layout, company);
		this.company = company;
		this.recurso = recurso;
		this.font = font;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null)
			v = inflater.inflate(R.layout.spinner_item, null);
		Cliente_2 row = company.get(position);
		if (row != null) {
			TextView textView = (TextView) v.findViewById(R.id.textSpinnerItem);
			textView.setTypeface(font);
			textView.setText(row.getNome());
		}
		return v;

	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null)
			v = inflater.inflate(R.layout.spinner_item_dropdown, null);
		Cliente_2 row = company.get(position);
		if (row != null) {
			TextView textView = (TextView) v.findViewById(R.id.textSpinnerItemDropdown);
			textView.setTypeface(font);
			textView.setText(row.getNome());
		}

		return v;
	}

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

	public void setNotifyOnChange(boolean notifyOnChange) {
		// TODO Auto-generated method stub
		setNotifyOnChange(notifyOnChange);
	}

}
