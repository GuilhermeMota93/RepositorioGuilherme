package com.example.itlog.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.itlog.R;
import com.example.itlog.adapters.MeusProj_ListView_Adapter;
import com.example.itlog.adapters.MeusProj_Spinner_Adapter;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.objects.Client;
import com.example.itlog.objects.Project;
import com.example.itlog.responseobjects.ListProjectsUserResponse;

public class MeusProj_Activity extends GeneralButtons_Activity implements
		CallbackInterface<ListProjectsUserResponse> {

	ArrayList<Project> projects = Project.generateFakeProjects();
	ArrayList<Project> arrayEspecifico = new ArrayList<Project>();
	ArrayList<Client> company = Client.generateFakeCompany();
	Client valor;
	Project projectSend;

	MeusProj_ListView_Adapter adapterList;
	MeusProj_Spinner_Adapter adapterSpinner;

	Intent intencao;
	ListView listView;
	Spinner spinner;

	// o username vem em forma de string desde o log in
	String info;

	// "Font" para o tipo de letra
	Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meusprojs_layout);
		// buscar info de user de tras, que vem do log in
		info = getIntent().getExtras().getString("USERNAME").toString();
		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

		listView = (ListView) findViewById(R.id.list);
		spinner = (Spinner) findViewById(R.id.spinnerMeusProj);

		adapterSpinner = new MeusProj_Spinner_Adapter(MeusProj_Activity.this,
				R.layout.spinner_item, company, font);
		spinner.setAdapter(adapterSpinner);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				arrayEspecifico.clear();
				valor = adapterSpinner.getItem(position);
				for (Project auxProject : projects) {
					if (auxProject.getUserid() != null
							&& (auxProject.getUserid()).equals(info)
							&& auxProject.getCompanyid() == valor.getId()) {
						arrayEspecifico.add(auxProject);
					}
				}
				adapterList = new MeusProj_ListView_Adapter(
						MeusProj_Activity.this,
						R.layout.single_row_listview_meusproj, arrayEspecifico,
						font);
				listView.setAdapter(adapterList);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				projectSend = adapterList.getItem(position);
				mandaInfo();
			}
		});
	}

	@Override
	public void callbackCall(ListProjectsUserResponse t) {
		// TODO Auto-generated method stub

	}

	public void mandaInfo() {

		intencao = new Intent(MeusProj_Activity.this,
				MostraInfoProj_Activity.class);

		Bundle bundle = new Bundle();
		Bundle bundle2 = new Bundle();
		bundle.putSerializable("OBJETO_COMPANY", valor);
		bundle2.putSerializable("OBJETO_PROJETO", projectSend);

		intencao.putExtras(bundle);
		intencao.putExtras(bundle2);
		intencao.putExtra("USERNAME", info);

		startActivity(intencao);
	}
}
