package com.example.itlog.activities;

import java.security.acl.LastOwnerException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.R.interpolator;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.adapters.Calendario_Adapter;
import com.example.itlog.adapters.InputHoras_Spinner_Adapter;
import com.example.itlog.adapters.ViewPager_Adapter;
import com.example.itlog.objects.Project;

public class InputHoras_Activity extends GeneralButtons_Activity {

	protected static final String TAG = null;
	public Calendar month, itemmonth;// instancias do calendario
	public Calendario_Adapter adapter;// instacia do adaptador

	InputHoras_Spinner_Adapter adapterSpinner;
	ArrayList<Project> projects = Project.generateFakeProjects();
	ArrayList<Project> arrayEspecifico = new ArrayList<Project>();

	Button imputar;
	Typeface font;
	Spinner spinner;

	// o username vem em forma de string desde o log in
	String info;

	ViewPager pager;
	PagerTitleStrip strip;
	ViewPager_Adapter viewPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendario_versao2);

		// buscar info de user de tras, que vem do log in
		info = getIntent().getExtras().getString("USERNAME").toString();
		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

		pager = (ViewPager) findViewById(R.id.viewPager);
		viewPagerAdapter = new ViewPager_Adapter(InputHoras_Activity.this);
		pager.setAdapter(viewPagerAdapter);
		pager = (ViewPager) findViewById(R.id.viewPager);
		imputar = (Button) findViewById(R.id.button1);
		spinner = (Spinner) findViewById(R.id.spinnerGridView1);
		getListaProjsUser();
		adapterSpinner = new InputHoras_Spinner_Adapter(
				InputHoras_Activity.this, R.layout.spinner_item,
				arrayEspecifico, font);
		spinner.setAdapter(adapterSpinner);

		// posicao onde começa o ViewPager
		month = Calendar.getInstance();
		pager.setCurrentItem(month.get(Calendar.MONTH));

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			int posAntes = pager.getCurrentItem();

			@Override
			public void onPageSelected(int posicaoAtual) {
				// TODO Auto-generated method stub
				if (posicaoAtual >= posAntes) {
					Log.i(TAG, "SWIPING RIGHT");

					// se chegar ao max do viewpager (com o getCount)
					if (posicaoAtual == viewPagerAdapter.getCount() - 1) {
						// limpa a lista, e cria com o (YEAR+1)
						viewPagerAdapter.getListaMesesMostrarMaisUm();
						viewPagerAdapter.notifyDataSetChanged();
					}
					posAntes++;

				} else if (posicaoAtual <= posAntes) {
					Log.i(TAG, "SWIPING LEFT");

					// se chegar ao min do viewpager (com o getCount)
					if (posicaoAtual == 0) {
						// limpa a lista, e cria de novo com (YEAR-1)
						viewPagerAdapter.getListaMesesMostarMenosUm();
						// progressbar aqui
						viewPagerAdapter.notifyDataSetChanged();
						pager.setCurrentItem(12);
					}
					posAntes--;

				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		imputar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				adapter = new Calendario_Adapter(InputHoras_Activity.this,
						month);

				LayoutInflater inflate = LayoutInflater
						.from(InputHoras_Activity.this);
				View layout = inflate.inflate(
						R.layout.botao_quatro_oito_horas_layout, null);
				TextView tv1 = (TextView) layout.findViewById(R.id.titulo);
				tv1.setText("Quantas horas pretende adicionar a este projeto?");
				TextView tv2 = (TextView) layout.findViewById(R.id.pergunta);
				tv2.setText("Se imputar 8 horas neste projeto, não poderá imputar horas a mais nenhum projecto neste dia!");
				Button b1 = (Button) layout.findViewById(R.id.botaoQuatroHoras);
				Button b2 = (Button) layout.findViewById(R.id.botaoOitoHoras);

				final AlertDialog.Builder builder = new AlertDialog.Builder(
						InputHoras_Activity.this);
				builder.setView(layout);
				final AlertDialog dialog = builder.create();
				dialog.show();

				b1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(InputHoras_Activity.this,
								"4 Horas adicionadas com sucesso! ",
								Toast.LENGTH_LONG).show();

						Toast.makeText(InputHoras_Activity.this,
								"" + adapter.getArraySelecionaDias().size(),
								Toast.LENGTH_LONG).show();

						// Para todos os elementos selecionados que estao na
						// lista
						for (int i = 0; i < adapter.getArraySelecionaDias().size(); i++) {
							//fazer arraylist de text views para depois fazer setText() ????'
							adapter.getArraySelecionaDias().get(i);
						}

						dialog.dismiss();
					}
				});

				b2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(InputHoras_Activity.this,
								"8 Horas adicionadas com sucesso! ",
								Toast.LENGTH_LONG).show();
						dialog.dismiss();
					}
				});

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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();

		}
		return true;
	}

	public ArrayList<Project> getListaProjsUser() {
		arrayEspecifico.clear();
		for (Project auxProject : projects) {
			if (auxProject.getUserid() != null
					&& (auxProject.getUserid()).equals(info))
				arrayEspecifico.add(auxProject);
		}
		return arrayEspecifico;

	}
}
