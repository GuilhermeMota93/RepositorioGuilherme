package com.example.itlog.activities;

import java.util.ArrayList;
import com.example.itlog.R;
import com.example.itlog.R.array;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;

import com.example.itlog.requestobjects.GET_API_SessionInfo_Request;
import com.example.itlog.responseobjects.GET_API_SessionInfo_Response;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.services.GET_API_SessionInfo_Service;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Info_Activity extends Activity implements
		CallbackInterface<GET_API_SessionInfo_Response> {

	private ProgressBar progressBar;
	POST_API_Login_Response token = POST_API_Login_Response.getInstance();
	String email, nome, id;
	TextView nrCred, nomeP, mail, tv1, tv2;
	Button meuProj, addProjectos, addHoras, testarServicos, b1, b2;
	Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_activity_layout);
		progressBar = (ProgressBar) findViewById(R.id.progressBar2);
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

		getService();

		meuProj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				progressBar.setVisibility(View.VISIBLE);
				Intent intencao = new Intent(Info_Activity.this,
						MeusProj_Activity.class);
				Info_Activity.this.startActivity(intencao);

			}
		});

		addProjectos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				progressBar.setVisibility(View.VISIBLE);
				Intent intencao = new Intent(Info_Activity.this,
						AddProj_Activity.class);
				Info_Activity.this.startActivity(intencao);
			}
		});

		addHoras.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				progressBar.setVisibility(View.VISIBLE);
				Intent intencao = new Intent(Info_Activity.this,
						InputHoras_Activity.class);
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

	public void getService() {
		progressBar.setVisibility(View.VISIBLE);
		new GET_API_SessionInfo_Service(Info_Activity.this,
				CommunicationCenter.GetSessionInformationService).execute(token
				.getToken());
	}

	public void callbackCall(GET_API_SessionInfo_Response t) {
		// nr funcionario
		nrCred.setText(t.getUserID());
		// nome funcionario
		nomeP.setText(t.getFullname());
		// mail funcionario
		mail.setText(t.getEmail());
		progressBar.setVisibility(View.GONE);

	}

	@Override
	public void onBackPressed() {
		LayoutInflater inflate = LayoutInflater.from(Info_Activity.this);
		View layout = inflate.inflate(R.layout.logou_layout, null);
		tv1 = (TextView) layout.findViewById(R.id.titulo1);
		tv1.setText("Log Out");
		tv2 = (TextView) layout.findViewById(R.id.descricao);
		tv2.setText("Efetuar esta ação terminará a sua sessão. \n\nPretende continuar?");
		b1 = (Button) layout.findViewById(R.id.botaoCancela);
		b2 = (Button) layout.findViewById(R.id.botaoOK);

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
				finish();
			}
		});
	}

}
