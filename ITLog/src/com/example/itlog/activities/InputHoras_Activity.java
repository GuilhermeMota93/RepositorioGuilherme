package com.example.itlog.activities;

import java.security.acl.LastOwnerException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.R.interpolator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerTabStrip;
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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.adapters.Calendario_Adapter;
import com.example.itlog.adapters.InputHoras_Spinner_Adapter;
import com.example.itlog.adapters.ViewPager_Adapter;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.objects.Projecto;
import com.example.itlog.requestobjects.POST_API_TimeSheets_Request;
import com.example.itlog.responseobjects.GET_API_ProjectosAndAusLst_Response;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.responseobjects.POST_API_TimeSheets_Response;
import com.example.itlog.services.GET_API_ProjectosAndAusLst_Service;
import com.example.itlog.services.POST_API_TimeSheets_Service;

public class InputHoras_Activity extends GeneralButtons_Activity {

	POST_API_Login_Response token = POST_API_Login_Response.getInstance();
	protected static final String TAG = null;
	public Calendar month, itemmonth;// instancias do calendario
	public Calendario_Adapter adapter;// instacia do adaptador
	InputHoras_Spinner_Adapter adapterSpinner;
	ArrayList<Projecto> projects = new ArrayList<Projecto>();
	Button imputar;
	Typeface font;
	Spinner spinner;
	ViewPager pager;
	PagerTitleStrip strip;
	ViewPager_Adapter viewPagerAdapter;
	ProgressBar progressBar;
	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendario_versao2);
		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		progressBar = (ProgressBar) findViewById(R.id.progressBar4);
		progressBar.setVisibility(View.GONE);
		pager = (ViewPager) findViewById(R.id.viewPager);
		viewPagerAdapter = new ViewPager_Adapter(InputHoras_Activity.this);
		pager.setAdapter(viewPagerAdapter);
		pager = (ViewPager) findViewById(R.id.viewPager);
		imputar = (Button) findViewById(R.id.button1);
		spinner = (Spinner) findViewById(R.id.spinnerGridView1);

		getServiceListaProjsAus();

		// posicao onde come�a o ViewPager
		month = Calendar.getInstance();
		pager.setCurrentItem(month.get(Calendar.MONTH));

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			int posAntes = pager.getCurrentItem();

			@Override
			public void onPageSelected(int posicaoAtual) {
				// TODO Auto-generated method stub

				if (posicaoAtual >= posAntes) {
					// progressBar.setVisibility(View.VISIBLE);

					Log.i(TAG, "SWIPING RIGHT");

					// se chegar ao max do viewpager (com o getCount)
					if (posicaoAtual == viewPagerAdapter.getCount() - 1) {
						// limpa a lista, e cria com o (YEAR+1)
						viewPagerAdapter.getListaMesesMostrarMaisUm();
						viewPagerAdapter.notifyDataSetChanged();

					}
					posAntes++;
					// progressBar.setVisibility(View.GONE);

				} else if (posicaoAtual <= posAntes) {
					// progressBar.setVisibility(View.VISIBLE);

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
					// progressBar.setVisibility(View.GONE);

				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				// progressBar.setVisibility(View.GONE);
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
				tv2.setText("Se imputar 8 horas neste projeto, n�o poder� imputar horas a mais nenhum projecto neste dia!");
				Button b1 = (Button) layout.findViewById(R.id.botaoQuatroHoras);
				Button b2 = (Button) layout.findViewById(R.id.botaoOitoHoras);

				final AlertDialog.Builder builder = new AlertDialog.Builder(
						InputHoras_Activity.this);
				builder.setView(layout);
				final AlertDialog dialog = builder.create();
				dialog.show();

//				b1.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						// Para todos os elementos selecionados que estao na
//						// lista
//						for (int i = 0; i < adapter.getArraySelecionaDias()
//								.size(); i++) {
//							// percorrer a grelha
//							for (int j = 0; j < adapter.getCount(); j++) {
//								// se a posi�ao da grelha corresponder ao valor
//								if (adapter.getArraySelecionaDias().get(i) == adapter
//										.getItem(j)) {
//
//								//escrever o 4 ???
//							
//								}
//							}
//						}
//						dialog.dismiss();
//					}
//				});
//
//				b2.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						Toast.makeText(InputHoras_Activity.this,
//								"8 Horas adicionadas com sucesso! ",
//								Toast.LENGTH_LONG).show();
//						dialog.dismiss();
//					}
//				});

			}
		});

	}

	public void getServiceListaProjsAus() {
		// progressBar.setVisibility(View.VISIBLE);
		new GET_API_ProjectosAndAusLst_Service(new CallbackListaProjsAus(),
				CommunicationCenter.GetLstProjsEAusencias).execute(token
				.getToken());
	}

	private class CallbackListaProjsAus implements
			CallbackInterface<GET_API_ProjectosAndAusLst_Response> {

		@Override
		public void callbackCall(GET_API_ProjectosAndAusLst_Response t) {
			// TODO Auto-generated method stub
			projects = t.getProjectos();
			adapterSpinner = new InputHoras_Spinner_Adapter(
					InputHoras_Activity.this, R.layout.spinner_item, projects,
					font);
			spinner.setAdapter(adapterSpinner);
			// progressBar.setVisibility(View.GONE);
		}

	}

	// public void getServiceTimeSheets(int ano, int mes) {
	// // progressBar.setVisibility(View.VISIBLE);
	// new POST_API_TimeSheets_Service(new CallbackTimeSheet(),
	// CommunicationCenter.PostTimeSheets,
	// new POST_API_TimeSheets_Request(ano, mes, token.getToken()))
	// .execute(new String[0]);
	// }
	//
	// public class CallbackTimeSheet implements
	// CallbackInterface<POST_API_TimeSheets_Response> {
	//
	// @Override
	// public void callbackCall(POST_API_TimeSheets_Response t2) {
	// // TODO Auto-generated method stub
	// // progressBar.setVisibility(View.GONE);
	// }
	//
	// }

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

}
