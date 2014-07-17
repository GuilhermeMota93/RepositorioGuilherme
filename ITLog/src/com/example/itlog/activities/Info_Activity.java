package com.example.itlog.activities;

import java.util.ArrayList;

import com.example.itlog.GetDeviceActivity;
import com.example.itlog.GetDevice_RequestObj;
import com.example.itlog.GetDevice_Service;
import com.example.itlog.R;
import com.example.itlog.R.array;
import com.example.itlog.TesteAosServicos;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.objects.Funcionario;
import com.example.itlog.requestobjects.GetSessionInformationRequest;
import com.example.itlog.responseobjects.GetSessionInformationResponse;
import com.example.itlog.responseobjects.LoginResponse;
import com.example.itlog.services.GetSessionInformationService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Info_Activity extends Activity implements
		CallbackInterface<GetSessionInformationResponse> {

	
	String email, nome, id, info;

	TextView nrCred, nomeP, mail, tv1, tv2;
	Button meuProj, addProjectos, addHoras, testarServicos, b1, b2;
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
		addProjectos = (Button) findViewById(R.id.addProj);
		addHoras = (Button) findViewById(R.id.inputHoras);

		// define a custom font
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		
		nrCred.setTypeface(font);
		nomeP.setTypeface(font);
		mail.setTypeface(font);
		meuProj.setTypeface(font);
		addProjectos.setTypeface(font);
		addHoras.setTypeface(font);
		
		validacao();
		nrCred.setText(info);
		mail.setText(email);
		nomeP.setText(nome);

		// falta o servi�o aqui?!
		// new GetSessionInformationService(InfoActivity.this,
		// CommunicationCenter.GetDevices,
		// new GetSessionInformationRequest(t.getUsername()).execute(new
		// String[0]);

		meuProj.setOnClickListener(new OnClickListener() {

			// aqui tem de se fazer os Intents com Extras
			// aqui tem de se fazer o "new Service" com em ADD_DEVICE_ACTIVITY
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intencao = new Intent(Info_Activity.this,
						MeusProj_Activity2.class);
				intencao.putExtra("USERNAME", info);
				// salta para Meus Projectos
				Info_Activity.this.startActivity(intencao);

			}
		});

		addProjectos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intencao = new Intent(Info_Activity.this,
						AddProj_Activity2.class);
				intencao.putExtra("USERNAME", info);
				// Vai para Adicionar Projectos
				Info_Activity.this.startActivity(intencao);
			}
		});

		addHoras.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intencao = new Intent(Info_Activity.this,
						InputHoras_Activity.class);
				intencao.putExtra("USERNAME", info);
				Info_Activity.this.startActivity(intencao);
			}
		});
	}

	// AQUI PARA A ACTION BAR ----> FAZER BOTOES
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_buttons, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public String[] validacao() {
		ArrayList<Funcionario> arrayFunc = Funcionario
				.generateFakeFuncionarios();
		info = getIntent().getExtras().getString("USERNAME");
		for (Funcionario funcs : arrayFunc) {
			if (funcs.getId().equals(info)) {
				email = funcs.getEmail().toString();
				nome = funcs.getName().toString();
			}
		}
		return new String[] { email, nome, info };
	}

	public void callbackCall(GetSessionInformationResponse t) {
		// // nr funcionario
		// nrCred.setText(t.getUserid());
		// // nome funcionario
		// nomeP.setText(t.getFullname());
		// // mail funcionario
		// mail.setText(t.getEmail());

	}

	@Override
	public void onBackPressed() {
		LayoutInflater inflate = LayoutInflater.from(Info_Activity.this);
		View layout = inflate.inflate(R.layout.alertdialog_layout, null);
		tv1 = (TextView) layout.findViewById(R.id.titulo);
		tv1.setText("Log Out");
		tv2 = (TextView) layout.findViewById(R.id.pergunta);
		tv2.setText("Efetuar esta a��o sair� da aplica��o. \n\nPretende continuar?");
		b1 = (Button) layout.findViewById(R.id.botaoCancela);
		b2 = (Button) layout.findViewById(R.id.botaoConfirma);

		final AlertDialog.Builder builder = new AlertDialog.Builder(
				Info_Activity.this);
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
				Intent intencao = new Intent(Info_Activity.this,
						Login_Activity.class);
				intencao.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
				Info_Activity.this.startActivity(intencao);

			}
		});
	}

}