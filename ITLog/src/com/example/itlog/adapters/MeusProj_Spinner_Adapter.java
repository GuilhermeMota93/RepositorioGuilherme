package com.example.itlog.adapters;

import java.util.ArrayList;
import java.util.List;

import Objects_General.Company;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.itlog.R;

public class MeusProj_Spinner_Adapter extends BaseAdapter {

	private ArrayList<Company> company;
	private int recurso;
	LayoutInflater inflater;
	boolean notifyOnChange = true;

	public MeusProj_Spinner_Adapter(final Context context, final int recurso,
			ArrayList<Company> company) {
		this.company = company;
		this.recurso = recurso;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return getCount();
	}

	@Override
	public Company getItem(int position) {
		// TODO Auto-generated method stub
		return getItem(position);
	}

	public int getPosition(Company item) {
		// TODO Auto-generated method stub
		return getPosition(item);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return getItemId(position);
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

		Company c = company.get(position);
		final ViewHolderSpinnerMeusProj holder;
		int type = getItemViewType(position);
		if (convertView == null) {
			holder = new ViewHolderSpinnerMeusProj();
			switch (type) {
			case 1:
				convertView = inflater.inflate(R.layout.spinner_item, parent,
						false);
				holder.name = (TextView) convertView
						.findViewById(R.id.textSpinnerItem);
				break;
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolderSpinnerMeusProj) convertView.getTag();
		}
		holder.name.setText(c.getNome());
		holder.pos = position;

		return convertView;

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

	class ViewHolderSpinnerMeusProj {

		TextView name;
		int pos; // to store the position of the item within the list
	}

}