//package com.example.itlog.activities;
//
//import java.util.ArrayList;
//
//import com.example.itlog.R;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Resources;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemSelectedListener;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//import android.widget.ListView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class ConfirmaHoras_Activity extends Activity implements
//		AdapterView.OnItemSelectedListener {
//
//	int incr = 0;
//	Spinner spinner1;
//	Button botaoConfirma;
//	ListView listView;
//	Typeface font;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.confirmar_horas_layout);
//
//		spinner1 = (Spinner) findViewById(R.id.spinnerConfirmarHoras);
//		botaoConfirma = (Button) findViewById(R.id.confirmar);
//		listView = (ListView) findViewById(android.R.id.list);
//		// para o tipo de letra
//		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
//
//		listView.setAdapter(new Adaptador(this));
//
//		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
//				R.array.opcoesHoras, R.layout.spinner_item);
//		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner1.setAdapter(adapter2);
//
//		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
//			// Sele��o de diferentes clientes (come�a na posi��o 0)
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				Adaptador adapter = (Adaptador) listView.getAdapter();
//				switch (position) {
//				case 0: {
//					adapter.swapData(R.array.meusProjectos);
//					break;
//				}
//				case 1: {
//					adapter.swapData(R.array.ausencia);
//					break;
//				}
//				}
//			}
//
//			public void onNothingSelected(AdapterView<?> parent) {
//				// TODO Auto-generated method stub
//
//			}
//
//		});
//
//		// FALTA FAZER ACTIVIDADE DE CONFIRMA�AO DAS HORAS + BOTAO QUE LEVA PARA
//		// LA AQUI
//		// confirmar.setOnClickListener(new OnClickListener() {
//		//
//		// @Override
//		// public void onClick(View v) {
//		// // TODO Auto-generated method stub
//		// Intent intencao = new Intent(ConfirmaHoras_Activity.this,
//		// ConfirmaHoras_Activity.class);//ao carregar no botao IMPUTAR HORAS
//		// vai para Confirmar Horas
//		// ConfirmaHoras_Activity.this.startActivity(intencao);
//		// }
//		// });
//
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu items for use in the action bar
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.actionbar_buttons, menu);
//		return super.onCreateOptionsMenu(menu);
//	}
//
//	class SingleRow {
//		String projecto;
//
//		public SingleRow(String projecto) {
//			// TODO Auto-generated constructor stub
//			this.projecto = projecto;
//		}
//
//	}
//
//	public class Adaptador extends BaseAdapter {
//
//		ArrayList<SingleRow> listSR;
//		Context context;
//
//		public Adaptador(Context c) {
//			// TODO Auto-generated constructor stub
//			context = c;
//			listSR = new ArrayList<SingleRow>();
//
//			Resources res = c.getResources();
//			// busca info de projectoA
//			String[] projecto = res.getStringArray(R.array.meusProjectos);
//
//			for (int i = 0; i < projecto.length; i++) {
//				// percorre o array de projectos e manda para a ListView
//				listSR.add(new SingleRow(projecto[i]));
//			}
//
//		}
//
//		// metodo para 'trocar' a informa��o que vai aparecer na ListView
//		public void swapData(int resId) {
//			Resources res = context.getResources();
//			String[] projecto = res.getStringArray(resId);
//			listSR.clear();// clear � informa��o
//			for (int i = 0; i < projecto.length; i++) {
//				listSR.add(new SingleRow(projecto[i]));
//				// informa que a informa��o foi modificada e � preciso fazer
//				// 'refresh' para mostrar a nova info
//				notifyDataSetChanged();
//			}
//
//		}
//
//		@Override
//		public int getCount() {
//			// TODO Auto-generated method stub
//			return listSR.size();
//		}
//
//		@Override
//		public Object getItem(int i) {
//			// TODO Auto-generated method stub
//			return listSR.get(i);
//		}
//
//		@Override
//		public long getItemId(int i) {
//			// TODO Auto-generated method stub
//			return i;
//		}
//
//		class MyViewHolder {
//			TextView tV;
//
//			public MyViewHolder(View v) {
//				tV = (TextView) v.findViewById(R.id.textView1);
//				// para o tipo de letra
//				((TextView) tV).setTypeface(font);
//			}
//		}
//
//		@Override
//		// Chamada a cada "row"
//		public View getView(int i, View view, ViewGroup viewGroup) {
//
//			View row = view;
//			MyViewHolder holder = null;
//
//			if (row == null) {
//				// Inflater -> vai ao xml, le propriedades e cria objecto com
//				// essas propriedades!
//				// new object everytime: layout inflater || same object
//				// everytime: findViewById
//				LayoutInflater inflater = (LayoutInflater) context
//						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//				// referencia para o RelativeLayout
//				row = inflater.inflate(R.layout.single_row_listview_checkbox,
//						viewGroup, false);
//				holder = new MyViewHolder(row);
//				row.setTag(holder);
//
//			} else {
//				holder = (MyViewHolder) row.getTag();
//
//			}
//			SingleRow temp = listSR.get(i);
//			holder.tV.setText(temp.projecto);
//
//			// Checkboxes
//			final CheckBox checkBox = (CheckBox) row
//					.findViewById(R.id.checkBox1);
//			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//				@Override
//				public void onCheckedChanged(CompoundButton buttonView,
//						boolean isChecked) {
//					if (isChecked && incr >= 2) {
//						checkBox.setChecked(false);
//						Toast.makeText(context,
//								"S� pode escolher 2 projectos no maximo! ",
//								Toast.LENGTH_LONG).show();
//					} else {
//						// the checkbox either got unchecked
//						// or there are less than 2 other checkboxes checked
//						// change your counter accordingly
//						if (isChecked) {
//							incr++;
//						} else {
//							incr--;
//						}
//						// now everything is fine and you can do whatever
//						// checking the checkbox should do here
//					}
//				}
//			});
//
//			return row;
//		}
//	}
//
//	@Override
//	public void onItemSelected(AdapterView<?> parent, View view, int position,
//			long id) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onNothingSelected(AdapterView<?> parent) {
//		// TODO Auto-generated method stub
//
//	}
//}