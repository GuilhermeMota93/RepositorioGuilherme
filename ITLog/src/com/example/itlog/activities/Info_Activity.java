package com.example.itlog.activities;

import com.example.itlog.GetDeviceActivity;
import com.example.itlog.GetDevice_RequestObj;
import com.example.itlog.GetDevice_Service;
import com.example.itlog.R;
import com.example.itlog.TesteAosServicos;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.GetSessionInformationRequest;
import com.example.itlog.responseobjects.GetSessionInformationResponse;
import com.example.itlog.responseobjects.LoginResponse;
import com.example.itlog.services.GetSessionInformationService;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Info_Activity extends Activity implements
		CallbackInterface<GetSessionInformationResponse> {

	TextView nrCred, nomeP, mail;
	Button meuProj, maisProj, addHoras, testarServicos;
	Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_activity_layout);

		nrCred = (TextView) findViewById(R.id.credNum);
		nomeP = (TextView) findViewById(R.id.nomePess);
		mail = (TextView) findViewById(R.id.emailIT);
		meuProj = (Button) findViewById(R.id.meusProj);
		maisProj = (Button) findViewById(R.id.addProj);
		addHoras = (Button) findViewById(R.id.inputHoras);

		// define a custom font
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		nrCred.setTypeface(font);
		nomeP.setTypeface(font);
		mail.setTypeface(font);
		meuProj.setTypeface(font);
		maisProj.setTypeface(font);
		addHoras.setTypeface(font);

		// isto foi um botao de testes, depois sai...ver no layout tambem!!!
		// testarServicos = (Button) findViewById(R.id.testarServicos);

		// falta o serviço aqui?!
		// new GetSessionInformationService(InfoActivity.this,
		// CommunicationCenter.GetDevices,
		// new GetSessionInformationRequest(t.getUsername()).execute(new
		// String[0]);

		meuProj.setOnClickListener(new OnClickListener() {

			// aqui tem de se fazer os Intents com Extras para as prox
			// Activities
			// aqui tem de se fazer o "new Service" com em ADD_DEVICE_ACTIVITY
			@Override
			public void onClick(View v) {// accao ao clicar ao ADICIONAR
											// PROJECTO
				// TODO Auto-generated method stub

				Intent intencao = new Intent(Info_Activity.this,
						MeusProj_Activity.class);// ao carregar no botao MEUS
													// PROJECTOS vai para
													// MeusProj
				Info_Activity.this.startActivity(intencao);

			}
		});

		maisProj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// accao ao clicar ao ADICIONAR PROJECTO
				// TODO Auto-generated method stub
				Intent intencao = new Intent(Info_Activity.this,
						AddProj_Activity.class);// ao carregar no botao
												// ADICIONAR PROJECTOS vai para
												// AddProj_Activity
				Info_Activity.this.startActivity(intencao);
			}
		});

		addHoras.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {// accao ao clicar ao ADICIONAR
											// PROJECTO
				// TODO Auto-generated method stub
				Intent intencao = new Intent(Info_Activity.this,
						InputHoras_Activity.class);// ao carregar no botao
													// IMPUTAR HORAS vai para
													// InputHoras
				Info_Activity.this.startActivity(intencao);
			}
		});

		// acçao do botao de testes...tem de sair! Ver layout tambem!
		// testarServicos.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {// accao ao clicar ao ADICIONAR
		// // PROJECTO
		// // TODO Auto-generated method stub
		// Intent intencao = new Intent(Info_Activity.this,
		// TesteAosServicos.class);// ao carregar no botao IMPUTAR
		// // HORAS vai para InputHoras
		// Info_Activity.this.startActivity(intencao);
		// }
		// });

	}

	public void callbackCall(GetSessionInformationResponse t) {
		// nr funcionario
		nrCred.setText(t.getUserid());
		// nome funcionario
		nomeP.setText(t.getFullname());
		// mail funcionario
		mail.setText(t.getEmail());

	}

}
