package com.example.itlog.activities;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.adapters.AddProject_ListView_Adapter;
import com.example.itlog.adapters.AddProject_Spinner_Adapter;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.Callback_Interface_2;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.objects.Cliente;
import com.example.itlog.objects.Projecto;
import com.example.itlog.requestobjects.POST_API_AddProjectToNucLst_Request;
import com.example.itlog.requestobjects.POST_API_Login_Request;
import com.example.itlog.requestobjects.POST_API_ProjectosByCli_Request;
import com.example.itlog.responseobjects.GET_API_ClienteLst_Response;
import com.example.itlog.responseobjects.GET_API_ProjectosLst_Response;
import com.example.itlog.responseobjects.POST_API_AddProjectNucLst_Response;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.responseobjects.POST_API_ProjectosByCli_Response;
import com.example.itlog.services.GET_API_ClienteLst_Service;
import com.example.itlog.services.POST_API_AddProjectNucLst_Service;
import com.example.itlog.services.POST_API_Login_Service;
import com.example.itlog.services.POST_API_ProjectosByCli_Service;

public class AddProj_Activity extends GeneralButtons_Activity {

	POST_API_Login_Response token = POST_API_Login_Response.getInstance();
	ArrayList<Projecto> projects = new ArrayList<Projecto>();
	ArrayList<Cliente> company = new ArrayList<Cliente>();
	AddProject_ListView_Adapter adapterList;
	AddProject_Spinner_Adapter adapterSpinner;
	ListView listView;
	Spinner spinner;
	Typeface font;
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addproj_layout);
		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		progressBar = (ProgressBar) findViewById(R.id.progressBar2);
		listView = (ListView) findViewById(R.id.list);
		spinner = (Spinner) findViewById(R.id.spinnerAddProj);
		getServiceClientes();
	}

	public void getServiceClientes() {
		progressBar.setVisibility(View.VISIBLE);
		new GET_API_ClienteLst_Service(new CallbackClientes(),
				CommunicationCenter.GetClientesLstService).execute(token
				.getToken());
	}

	public void getServiceProjectos(int cod) {
		progressBar.setVisibility(View.VISIBLE);
		new POST_API_ProjectosByCli_Service(new CallbackProjects(),
				CommunicationCenter.ProjectosByCli,
				new POST_API_ProjectosByCli_Request(cod, token.getToken()))
				.execute(new String[0]);
	}

	public void getServiceAddProjecto(String prjCod) {
		progressBar.setVisibility(View.VISIBLE);
		new POST_API_AddProjectNucLst_Service(new CallBackAddProj(),
				CommunicationCenter.PostAddProjectoLstService,
				new POST_API_AddProjectToNucLst_Request(prjCod,
						token.getToken())).execute(new String[0]);
	}

	private class CallbackClientes implements
			CallbackInterface<GET_API_ClienteLst_Response> {
		@Override
		public void callbackCall(GET_API_ClienteLst_Response t) {
			// TODO Auto-generated method stub
			progressBar.setVisibility(View.VISIBLE);
			company = t.getClientes();
			adapterSpinner = new AddProject_Spinner_Adapter(
					AddProj_Activity.this, R.layout.spinner_item, company, font);
			spinner.setAdapter(adapterSpinner);

			// NESTE ON ITEM SELECT TEM DE FAZER O POST DO CODIGO DE CLIENTE
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					progressBar.setVisibility(View.VISIBLE);
					getServiceProjectos(company.get(position).getCod());
				
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
				}
			});
		}
	}

	private class CallbackProjects implements
			CallbackInterface<POST_API_ProjectosByCli_Response> {
		@Override
		public void callbackCall(POST_API_ProjectosByCli_Response t2) {
			projects = t2.getListaprojs();
			adapterList = new AddProject_ListView_Adapter(
					AddProj_Activity.this,
					R.layout.single_row_listview_addproj, projects, font);
			listView.setAdapter(adapterList);

			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						final int position, long id) {
					// TODO Auto-generated method stub
					LayoutInflater inflate = LayoutInflater
							.from(AddProj_Activity.this);
					View layout = inflate.inflate(
							R.layout.adiciona_projecto_layout, null);

					TextView tv1 = (TextView) layout.findViewById(R.id.titulo1);
					tv1.setText("Adicionar Projeto");
					TextView tv2 = (TextView) layout
							.findViewById(R.id.descricao);
					tv2.setText("\nPretende adicionar este projeto à sua lista de projetos?");
					Button b1 = (Button) layout
							.findViewById(R.id.botaoCancelar);
					Button b2 = (Button) layout
							.findViewById(R.id.botaoAdicionarProj);

					final AlertDialog.Builder builder = new AlertDialog.Builder(
							AddProj_Activity.this);
					builder.setView(layout);
					final AlertDialog dialog = builder.create();
					dialog.show();
					b1.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							dialog.dismiss();
							progressBar.setVisibility(View.VISIBLE);
						}
					});

					b2.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							getServiceAddProjecto(projects.get(position)
									.getCod());
							dialog.dismiss();
							progressBar.setVisibility(View.VISIBLE);
						}
					});

				}
			});

		}
	}

	private class CallBackAddProj implements
			CallbackInterface<POST_API_AddProjectNucLst_Response> {

		@Override
		public void callbackCall(POST_API_AddProjectNucLst_Response t3) {
			// TODO Auto-generated method stub

			if (t3.getStatusCd().equals("OK")) {

				Toast.makeText(AddProj_Activity.this,
						"Projeto adicionado à sua lista com sucesso! ",
						Toast.LENGTH_LONG).show();
			} else if (t3.getStatusCd().equals("KO")) {

				Toast.makeText(AddProj_Activity.this,
						"Desculpe, mas não foi possível adicionar o projecto!",
						Toast.LENGTH_LONG).show();
			}

		}

	}

}
