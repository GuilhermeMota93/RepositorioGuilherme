package com.example.itlog.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

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
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.objects.Client;
import com.example.itlog.objects.Cliente_2;
import com.example.itlog.objects.Project;
import com.example.itlog.objects.Projecto_2;
import com.example.itlog.responseobjects.GET_API_ProjectosLst_Response;
import com.example.itlog.responseobjects.GET_API_SessionInfo_Response;
import com.example.itlog.responseobjects.GET_API_ClienteLst_Response;
import com.example.itlog.responseobjects.ListProjectsUserResponse;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.services.GET_API_ProjectosLst_Service;
import com.example.itlog.services.GET_API_SessionInfo_Service;
import com.example.itlog.services.GET_API_ClienteLst_Service;
import com.google.gson.JsonArray;

public class MeusProj_Activity extends GeneralButtons_Activity implements
		CallbackInterface<GET_API_ProjectosLst_Response> {

	ArrayList<Projecto_2> projects = new ArrayList<Projecto_2>();
	POST_API_Login_Response token = POST_API_Login_Response.getInstance();
	Cliente_2 valor, clientesend;
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
		// info = getIntent().getExtras().getString("TOKEN").toString();
		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		listView = (ListView) findViewById(R.id.list);
		getService();
	}

	public void getService() {
		new GET_API_ProjectosLst_Service(this,
				CommunicationCenter.GetProjectosLstService).execute(token
				.getToken());
	}

	@Override
	public void callbackCall(GET_API_ProjectosLst_Response t) {
		// TODO Auto-generated method stub
		projects = t.getProjectos();
		adapterList = new MeusProj_ListView_Adapter(this,
				R.layout.single_row_listview_meusproj, projects, font);
		listView.setAdapter(adapterList);
	}

}
