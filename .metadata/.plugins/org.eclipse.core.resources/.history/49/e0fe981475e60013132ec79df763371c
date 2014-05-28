package com.example.itlog.adapters;

import java.util.ArrayList;
import java.util.List;

import com.example.itlog.R;

import Objects_General.Company;
import Objects_General.Project;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MeusProj_ListView_Adapter extends ArrayAdapter<Project> {

	// este adaptor vai listar todos os projetos...como mostrar somente os
	// do funcionario?

	private Context context;
	private ArrayList<Project> projects;
	private LayoutInflater inflater;
	private boolean notifyOnChange = true;

	public MeusProj_ListView_Adapter(Context context,
			ArrayList<Project> projects, LayoutInflater inflater) {
		super(context, R.layout.single_row_listview_mostrarproj);
		this.context = context;
		this.projects = new ArrayList<Project>(projects);
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public MeusProj_ListView_Adapter(Context context,
			ArrayList<Project> projects) {
		super(context, R.layout.single_row_listview_mostrarproj);
		this.context = context;
		this.projects = new ArrayList<Project>(projects);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}

	@Override
	public Project getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getPosition(Project item) {
		// TODO Auto-generated method stub
		return super.getPosition(item);
	}

	@Override
	public int getViewTypeCount() {
		return 1; // Number of types + 1 !!!!!!!!
	}

	@Override
	public int getItemViewType(int position) {
		return 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Project p = projects.get(position);
		final ViewHolderListViewMeusProj holder;
		int type = getItemViewType(position);
		if (convertView == null) {
			holder = new ViewHolderListViewMeusProj();
			switch (type) {
			case 1:
				convertView = inflater.inflate(R.layout.single_row_listview_mostrarproj,
						parent, false);
				holder.name = (TextView) convertView
						.findViewById(R.id.textView1);
				break;
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolderListViewMeusProj) convertView.getTag();
		}
		holder.name.setText(p.getNome());
		holder.pos = position;
		return convertView;
	}

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		notifyOnChange = true;
	}

	public void setNotifyOnChange(boolean notifyOnChange) {
		this.notifyOnChange = notifyOnChange;
	}
}

class ViewHolderListViewMeusProj {

	TextView name;
	int pos; // to store the position of the item within the list
}
