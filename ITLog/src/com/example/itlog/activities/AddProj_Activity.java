package com.example.itlog.activities;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;

public class AddProj_Activity extends GeneralButtons_Activity implements
		AdapterView.OnItemSelectedListener {

	int incr = 0;
	Spinner spinner1;
	ListView listView;
	Typeface font;
	TextView tv1, tv2;
	Button b1, b2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addproj_layout);
		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

		spinner1 = (Spinner) findViewById(R.id.spinnerAddProj);
		listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(new Adaptador(this));
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
				R.array.clientes_array, R.layout.spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter2);

		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			// Sele��o de diferentes clientes (come�a na posi��o 0)
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Adaptador adapter = (Adaptador) listView.getAdapter();
				switch (position) {
				case 0: {
					adapter.swapData(R.array.projectoA);
					break;
				}
				case 1: {
					adapter.swapData(R.array.projectoB);
					break;
				}
				}
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_buttons, menu);
		return super.onCreateOptionsMenu(menu);
	}

	class SingleRow {
		String projecto;

		public SingleRow(String projecto) {
			// TODO Auto-generated constructor stub
			this.projecto = projecto;
		}

	}

	public class Adaptador extends BaseAdapter {

		ArrayList<SingleRow> listSR;
		Context context;

		public Adaptador(Context c) {
			// TODO Auto-generated constructor stub
			context = c;
			listSR = new ArrayList<SingleRow>();

			Resources res = c.getResources();
			// busca info de projectoA
			String[] projecto = res.getStringArray(R.array.projectoA);

			for (int i = 0; i < projecto.length; i++) {
				// percorre o array de projectos e manda para a ListView
				listSR.add(new SingleRow(projecto[i]));
			}

		}

		// metodo para 'trocar' a informa��o que vai aparecer na ListView
		public void swapData(int resId) {
			Resources res = context.getResources();
			String[] projecto = res.getStringArray(resId);
			listSR.clear();// clear � informa��o
			for (int i = 0; i < projecto.length; i++) {
				listSR.add(new SingleRow(projecto[i]));
				// informa que a informa��o foi modificada e � preciso fazer
				// 'refresh' para mostrar a nova info
				notifyDataSetChanged();
			}

			// ao carregar na listView
			listView.setOnItemClickListener(new ListView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					LayoutInflater inflate = LayoutInflater
							.from(AddProj_Activity.this);
					View layout = inflate.inflate(R.layout.alertdialog_layout,
							null);

					tv1 = (TextView) layout.findViewById(R.id.titulo);
					tv1.setText("Adicionar Projeto");
					tv2 = (TextView) layout.findViewById(R.id.pergunta);
					tv2.setText("Pretende adicionar este projeto � sua lista de projetos?");
					b1 = (Button) layout.findViewById(R.id.botaoCancela);
					b2 = (Button) layout.findViewById(R.id.botaoConfirma);

					final AlertDialog.Builder builder = new AlertDialog.Builder(
							AddProj_Activity.this);
					builder.setView(layout);
					final AlertDialog dialog = builder.create();
					dialog.show();
					b1.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							dialog.dismiss();
						}
					});

					b2.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Toast.makeText(context,
									"Projeto adicionado com sucesso! ",
									Toast.LENGTH_LONG).show();
							dialog.dismiss();
						}
					});

				}
			});

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listSR.size();
		}

		@Override
		public Object getItem(int i) {
			// TODO Auto-generated method stub
			return listSR.get(i);
		}

		@Override
		public long getItemId(int i) {
			// TODO Auto-generated method stub
			return i;
		}

		class MyViewHolder {
			TextView tV;

			public MyViewHolder(View v) {
				tV = (TextView) v.findViewById(R.id.textView1);
				// para o tipo de letra
				((TextView) tV).setTypeface(font);
			}
		}

		@Override
		// Chamada a cada "row"
		public View getView(int i, View view, ViewGroup viewGroup) {
			View row = view;
			MyViewHolder holder = null;

			if (row == null) {
				// Inflater -> vai ao xml, le propriedades e cria objecto com
				// essas propriedades!
				// new object everytime: layout inflater || same object
				// everytime: findViewById
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// referencia para o RelativeLayout
				row = inflater.inflate(R.layout.single_row_listview_addproj,
						viewGroup, false);
				holder = new MyViewHolder(row);
				row.setTag(holder);

			} else {
				holder = (MyViewHolder) row.getTag();

			}
			SingleRow temp = listSR.get(i);
			holder.tV.setText(temp.projecto);

			return row;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}