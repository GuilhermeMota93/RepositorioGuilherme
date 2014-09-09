package com.example.itlog.adapters;

import java.util.ArrayList;

import com.example.itlog.R;
import com.example.itlog.objects.Project;
import com.example.itlog.objects.Projecto_2;

import android.content.Context;
import android.graphics.Typeface;
import android.renderscript.Type;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddProject_ListView_Adapter extends ArrayAdapter<Projecto_2> {

	private ArrayList<Projecto_2> projects;
	private int recurso;
	LayoutInflater inflater ;
	boolean notifyOnChange = true;
	Typeface font;

	public AddProject_ListView_Adapter(final Context context,
			final int recurso, ArrayList<Projecto_2> projects) {
		super(context, R.layout.addproj_layout, projects);
		this.projects = projects;
		this.recurso = recurso;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public AddProject_ListView_Adapter(final Context context,
			final int recurso, ArrayList<Projecto_2> projects, Typeface font) {
		super(context, R.layout.addproj_layout, projects);
		this.projects = projects;
		this.recurso = recurso;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.font = font;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// atribuir a View que vamos converter a uma variavel local
		View v = convertView;
		// verificar se a View esta vazia, e se sim, "inflate" nela
		// "inflate" significa mostrar a vista (view)
		if (v == null)
			v = inflater.inflate(
					R.layout.single_row_listview_addproj, null);
		// "position" é passado como argumento, refere-se a posiçao na lista
		// row é o item atual de Project
		Projecto_2 row = projects.get(position);
		if (row != null) {
			TextView textView = (TextView) v
					.findViewById(R.id.textViewAddProj);
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
