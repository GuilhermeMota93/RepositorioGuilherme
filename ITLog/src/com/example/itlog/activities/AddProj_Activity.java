package com.example.itlog.activities;

import java.util.ArrayList;

import com.example.itlog.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddProj_Activity extends Activity implements
		AdapterView.OnItemSelectedListener {

	int incr = 0;
	Spinner spinner1;
	Button botaoAdd;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addproj_layout);

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		botaoAdd = (Button) findViewById(R.id.addProj);
		listView = (ListView) findViewById(android.R.id.list);

		listView.setAdapter(new Adaptador(this));

		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
				R.array.clientes_array, R.layout.spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter2);

		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			// Seleção de diferentes clientes (começa na posição 0)
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
		
		
		botaoAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {//accao ao clicar ao ADICIONAR PROJECTO
				// TODO Auto-generated method stub
				Intent intencao = new Intent(AddProj_Activity.this, PopUp_AddProj.class);//ao carregar no botao lança pop-up para confirmar addProj
				AddProj_Activity.this.startActivity(intencao);
			}
		});
		

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

		// metodo para 'trocar' a informação que vai aparecer na ListView
		public void swapData(int resId) {
			Resources res = context.getResources();
			String[] projecto = res.getStringArray(resId);
			listSR.clear();// clear à informação
			for (int i = 0; i < projecto.length; i++) {
				listSR.add(new SingleRow(projecto[i]));
				// informa que a informação foi modificada e é preciso fazer
				// 'refresh' para mostrar a nova info
				notifyDataSetChanged();
			}

			// ao carregar na listView
			listView.setOnItemClickListener(new ListView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

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
				row = inflater.inflate(R.layout.single_row_listview_addproj, viewGroup, false);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
	
	
}