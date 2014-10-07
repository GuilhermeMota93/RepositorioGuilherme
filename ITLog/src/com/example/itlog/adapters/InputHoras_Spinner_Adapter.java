package com.example.itlog.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.itlog.R;
import com.example.itlog.objects.Projecto;
import com.example.itlog.objects.TimeSheetAllocate;

public class InputHoras_Spinner_Adapter extends ArrayAdapter<Projecto> {

	ArrayList<Projecto> projects;
	private int recurso;
	LayoutInflater inflater;
	boolean notifyOnChange = true;
	Typeface font;

	public InputHoras_Spinner_Adapter(final Context context, final int recurso,
			ArrayList<Projecto> projects) {
		super(context, R.layout.calendario_versao2, projects);
		this.projects = projects;
		this.recurso = recurso;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public InputHoras_Spinner_Adapter(final Context context, final int recurso,
			ArrayList<Projecto> projects, Typeface font) {
		super(context, R.layout.calendario_versao2, projects);
		this.projects = projects;
		this.recurso = recurso;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.font = font;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null)
			v = inflater.inflate(R.layout.spinner_item, null);
		Projecto row = projects.get(position);
		if (row != null) {
			TextView textView = (TextView) v.findViewById(R.id.textSpinnerItem);
			textView.setTypeface(font);
			textView.setText(row.getNome());
		}
		return v;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null)
			v = inflater.inflate(R.layout.spinner_item_dropdown, null);
		Projecto row = projects.get(position);
		if (row != null) {
			TextView textView = (TextView) v
					.findViewById(R.id.textSpinnerItemDropdown);
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

	@Override
	public void setNotifyOnChange(boolean notifyOnChange) {
		// TODO Auto-generated method stub
		super.setNotifyOnChange(notifyOnChange);
	}

	
}
