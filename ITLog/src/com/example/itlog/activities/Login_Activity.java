package com.example.itlog.activities;

import java.util.ArrayList;

import com.example.itlog.R;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.POST_API_Login_Request;
import com.example.itlog.requestobjects.POST_API_TimeSheets_Request;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.responseobjects.POST_API_TimeSheets_Response;
import com.example.itlog.services.POST_API_Login_Service;
import com.example.itlog.services.POST_API_TimeSheets_Service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.IntentCompat;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends Activity {

	// em vez de progress bar pode ser PROGRESS DIALOG!!!!
	private ProgressBar progressBar;

	private ProgressDialog progressDialog;

	String username, pass;
	Button login, b1, b2, b3; // butao de log in
	EditText password, credencial;// texto de inser�ao de credencial/password
	ImageView imgV; // imagem no topo
	TextView bemvindo, tv1, tv2, tv3, tv4, tv5, tv6;
	Typeface font;
	Boolean verificaLigacaoNet = false;
	Detecta_Conexao_Internet detetor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar.setVisibility(View.GONE);

		login = (Button) findViewById(R.id.loginBut);
		password = (EditText) findViewById(R.id.password);
		credencial = (EditText) findViewById(R.id.credencial);
		credencial.requestFocus();
		imgV = (ImageView) findViewById(R.id.imgV);
		bemvindo = (TextView) findViewById(R.id.textView1);

		// define a custom font
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		login.setTypeface(font);
		password.setTypeface(font);
		credencial.setTypeface(font);
		bemvindo.setTypeface(font);

		// objecto para detectar conexao a net
		detetor = new Detecta_Conexao_Internet(getApplicationContext());

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				verificaLigacaoNet = detetor.existeConexao();
				// se existir conexao, faz pedido
				if (verificaLigacaoNet) {
					// new TaskService().execute();

					getServiceLogIn();

				} else if (!verificaLigacaoNet) {

					LayoutInflater inflate = LayoutInflater
							.from(Login_Activity.this);
					View layout = inflate.inflate(R.layout.mensagem_erro_login,
							null);
					tv1 = (TextView) layout.findViewById(R.id.titulo);
					tv1.setText("Erro!");
					tv2 = (TextView) layout.findViewById(R.id.texto);
					tv2.setText("N�o se encontra ligado � rede.");
					b1 = (Button) layout.findViewById(R.id.botaoConfirma);

					final AlertDialog.Builder builder = new AlertDialog.Builder(
							Login_Activity.this);
					builder.setView(layout);
					final AlertDialog dialog = builder.create();
					dialog.show();

					b1.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}
					});

				}

			}

		});

	}

	private void getServiceLogIn() {

		progressDialog = ProgressDialog.show(this, "Aguarde, por favor",
				"A validar dados de acesso...", true);
		progressDialog.setCancelable(true);

		new POST_API_Login_Service(new CallBackLogInService(),
				CommunicationCenter.PostLoginService,
				new POST_API_Login_Request(credencial.getText().toString(),
						password.getText().toString())).execute(new String[0]);
	}

	private class CallBackLogInService implements
			CallbackInterface<POST_API_Login_Response> {

		@Override
		public void callbackCall(POST_API_Login_Response t) {
			// TODO Auto-generated method stub
			if (t.getStatusCd().equals("OK")
					&& t.getStatusTxt().equals("Sucesso")) {

				Intent intencao = new Intent(Login_Activity.this,
						Info_Activity.class);
				Login_Activity.this.startActivity(intencao);

			} else if (t.getStatusCd().equals("KO")
					&& t.getStatusTxt().equals("Credenciais inv�lidas.")) {

				LayoutInflater inflate = LayoutInflater
						.from(Login_Activity.this);
				View layout = inflate.inflate(R.layout.mensagem_erro_login,
						null);
				tv3 = (TextView) layout.findViewById(R.id.titulo);
				tv3.setText("Credenciais inv�lidas!");
				tv4 = (TextView) layout.findViewById(R.id.texto);
				tv4.setText("Volte a introduzir o seu Username e Password, por favor.");
				b2 = (Button) layout.findViewById(R.id.botaoConfirma);

				final AlertDialog.Builder builder = new AlertDialog.Builder(
						Login_Activity.this);
				builder.setView(layout);
				final AlertDialog dialog = builder.create();
				dialog.show();

				b2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

			}

		}

	}

}
