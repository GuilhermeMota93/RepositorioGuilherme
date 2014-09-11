package com.example.itlog.activities;

import java.util.ArrayList;

import com.example.itlog.R;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.objects.Users;
import com.example.itlog.requestobjects.POST_API_Login_Request;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.services.POST_API_Login_Service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
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

public class Login_Activity extends Activity implements
		CallbackInterface<POST_API_Login_Response> {

	// em vez de progress bar pode ser PROGRESS DIALOG!!!!
	private ProgressBar progressBar;
	private Users user;
	String username, pass;

	Button login, b1, b2; // butao de log in
	EditText password, credencial;// texto de inserçao de credencial/password
	ImageView imgV; // imagem no topo
	TextView bemvindo, tv1, tv2, tv3, tv4;
	Typeface font;

	Boolean verificaLigacaoNet = false;
	Detecta_Conexao_Internet detetor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		user = new Users();
		setContentView(R.layout.login_layout);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar.setVisibility(View.GONE);

		login = (Button) findViewById(R.id.loginBut);
		password = (EditText) findViewById(R.id.password);
		credencial = (EditText) findViewById(R.id.credencial);
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
				progressBar.setVisibility(View.VISIBLE);
				// info da Internet
				verificaLigacaoNet = detetor.existeConexao();

				// se existir conexao, faz pedido
				if (verificaLigacaoNet) {
					new POST_API_Login_Service(Login_Activity.this,
							CommunicationCenter.PostLoginService,
							new POST_API_Login_Request(credencial.getText()
									.toString(), password.getText().toString()))
							.execute(new String[0]);

				} else if (!verificaLigacaoNet) {

					LayoutInflater inflate = LayoutInflater
							.from(Login_Activity.this);
					View layout = inflate.inflate(R.layout.mensagem_erro_login,
							null);
					tv1 = (TextView) layout.findViewById(R.id.titulo);
					tv1.setText("Erro!");
					tv2 = (TextView) layout.findViewById(R.id.pergunta);
					tv2.setText("Não se encontra ligado à rede.");
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
					// } else if (new
					// POST_API_Login_Service(Login_Activity.this,
					// CommunicationCenter.PostLoginService,
					// new POST_API_Login_Request(credencial.getText()
					// .toString(), password.getText().toString()))
					// .execute(new String[0]) == null) {
					// LayoutInflater inflate = LayoutInflater
					// .from(Login_Activity.this);
					// View layout = inflate.inflate(
					// R.layout.mensagem_erro_login_servico, null);
					// tv3 = (TextView) layout.findViewById(R.id.titulo1);
					// tv3.setText("Erro!");
					// tv4 = (TextView) layout.findViewById(R.id.declaracao);
					// tv4.setText("Ocorreu um problema na ligação ao servidor. \nPedimos desculpa pelo incomodo");
					// b2 = (Button) layout.findViewById(R.id.botaoClickOk);
					//
					// final AlertDialog.Builder builder = new
					// AlertDialog.Builder(
					// Login_Activity.this);
					// builder.setView(layout);
					// final AlertDialog dialog = builder.create();
					// dialog.show();
					//
					// b2.setOnClickListener(new OnClickListener() {
					//
					// @Override
					// public void onClick(View v) {
					// dialog.dismiss();
					// }
					// });
					//
					// }
				}
			}
		});

	}

	@Override
	public void callbackCall(POST_API_Login_Response t) {
		// TODO Auto-generated method stub
		String valorStatusCd = t.getStatusCd();
		String valorStatusTxT = t.getStatusTxt();
		String valorToken = t.getToken();

		if (valorStatusCd.equals("OK") && valorStatusTxT.equals("Sucesso")) {
			Intent intencao = new Intent(Login_Activity.this,
					Info_Activity.class);
			Login_Activity.this.startActivity(intencao);
		} else if (valorStatusCd.equals("KO")
				&& valorStatusTxT.equals("Credenciais inválidas.")) {
			Toast.makeText(Login_Activity.this, "Credenciais inválidas.",
					Toast.LENGTH_LONG).show();
			progressBar.setVisibility(View.GONE);
		}

	}

}
