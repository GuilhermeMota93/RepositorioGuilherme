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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.adapters.AddProject_ListView_Adapter;
import com.example.itlog.adapters.AddProject_Spinner_Adapter;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.objects.Cliente_2;
import com.example.itlog.objects.Projecto_2;
import com.example.itlog.responseobjects.GET_API_ClienteLst_Response;
import com.example.itlog.responseobjects.GET_API_ProjectosLst_Response;
import com.example.itlog.responseobjects.POST_API_AddProjectNucLst_Response;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.services.GET_API_ClienteLst_Service;
import com.example.itlog.services.GET_API_ProjectosLst_Service;

public class AddProj_Activity extends GeneralButtons_Activity implements
		CallbackInterface<GET_API_ClienteLst_Response> {

	POST_API_Login_Response token = POST_API_Login_Response.getInstance();

	// ArrayList<Project> projects = Project.generateFakeProjects();
	// ArrayList<Project> arrayEspecifico = new ArrayList<Project>();
	// ArrayList<Client> company = Client.generateFakeCompany();

	ArrayList<Projecto_2> projects = new ArrayList<Projecto_2>();
	ArrayList<Projecto_2> arrayEspecifico = new ArrayList<Projecto_2>();

	ArrayList<Cliente_2> company = new ArrayList<Cliente_2>();

	GET_API_ProjectosLst_Response prjObj = new GET_API_ProjectosLst_Response();
	GET_API_ClienteLst_Response cliObj = new GET_API_ClienteLst_Response();

	AddProject_ListView_Adapter adapterList;
	AddProject_Spinner_Adapter adapterSpinner;

	ListView listView;
	Spinner spinner;

	Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addproj_layout);

		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

		listView = (ListView) findViewById(R.id.list);
		spinner = (Spinner) findViewById(R.id.spinnerAddProj);
		
		getService();

	}

	public void getService() {

		new GET_API_ClienteLst_Service(this,
				CommunicationCenter.GetClientesLstService).execute(token
				.getToken());
	}

	@Override
	public void callbackCall(GET_API_ClienteLst_Response t) {
		// TODO Auto-generated method stub
	
		company = t.getClientes();
		projects = prjObj.getProjectos();

		adapterSpinner = new AddProject_Spinner_Adapter(AddProj_Activity.this,
				R.layout.spinner_item, company, font);
		spinner.setAdapter(adapterSpinner);
		
		//NESTE ON ITEM SELECT TEM DE FAZER O POST DO CODIGO DE CLIENTE
//		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				arrayEspecifico.clear();
//				Cliente_2 valor = adapterSpinner.getItem(position);
//				for (Projecto_2 auxProject : projects) {
//					if (auxProject.getCod() == String.valueOf(valor.getCod())
//							&& auxProject.getCod() == null) {
//						arrayEspecifico.add(auxProject);
//					}
//				}
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				// TODO Auto-generated method stub
//
//			}
//		});

		adapterList = new AddProject_ListView_Adapter(AddProj_Activity.this,
				R.layout.single_row_listview_addproj, arrayEspecifico, font);
		listView.setAdapter(adapterList);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				LayoutInflater inflate = LayoutInflater
						.from(AddProj_Activity.this);
				View layout = inflate
						.inflate(R.layout.alertdialog_layout, null);

				TextView tv1 = (TextView) layout.findViewById(R.id.titulo);
				tv1.setText("Adicionar Projeto");
				TextView tv2 = (TextView) layout.findViewById(R.id.pergunta);
				tv2.setText("Pretende adicionar este projeto à sua lista de projetos?");
				Button b1 = (Button) layout.findViewById(R.id.botaoCancela);
				Button b2 = (Button) layout.findViewById(R.id.botaoOK);

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
						Toast.makeText(AddProj_Activity.this,
								"Projeto adicionado com sucesso! ",
								Toast.LENGTH_LONG).show();
						dialog.dismiss();
					}
				});
			}

		});
		
		
	}


}