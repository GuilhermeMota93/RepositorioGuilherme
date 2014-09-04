package com.example.itlog.activities;

import java.util.ArrayList;

import com.example.itlog.R;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.objects.Users;
import com.example.itlog.requestobjects.LoginRequest;
import com.example.itlog.responseobjects.LoginResponse;
import com.example.itlog.services.LoginService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
		CallbackInterface<LoginResponse> {

	// em vez de progress bar pode ser PROGRESS DIALOG!!!!
	private ProgressBar progressBar;

	private Users user;
	String username, pass;

	Button login, b1; // butao de log in
	EditText password, credencial;// texto de inserçao de credencial/password
	ImageView imgV; // imagem no topo
	TextView bemvindo, tv1, tv2;
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

		// login.setOnClickListener(new OnClickListener() {
		//
		// /*
		// * NOTA "COMO PASSAR OBJECTS DO GSON NO BUNDLE":
		// *
		// * Initial Activity Intent activity = new
		// * Intent(MyActivity.this,NextActivity.class);
		// * activity.putExtra("myObject", new Gson().toJson(myobject);
		// * startActivity(activity);
		// *
		// * Next Activity
		// *
		// * Sting jsonMyObject; Bundle extras = getIntent().getExtras(); if
		// * (extras != null) { jsonMyObject = extras.getString("myObject"); }
		// * MyObject myObject = new Gson().fromJson(jsonMyObject,
		// * MyObject.class);
		// */
		//
		// // ao click no botao LOG IN, fazer o servico para ir buscar as info
		// // depois passar Intents com "Extras" para a prox atividade
		// // fazer o set.TexView() ???
		//
		// @Override
		// public void onClick(View v) {// accao ao clicar
		// // TODO Auto-generated method stub
		// if (credencial.getText().toString().equals("")
		// && password.getText().toString().equals("")) {
		// Toast.makeText(Login_Activity.this, "Campos vazios! ",
		// Toast.LENGTH_LONG).show();
		// } else {
		//
		// if (login() == true) {
		// progressBar.setVisibility(View.VISIBLE);
		//
		// Intent intencao = new Intent(Login_Activity.this,
		// Info_Activity.class);
		// // para passar info para prox activity
		// intencao.putExtra("USERNAME", username);
		// Login_Activity.this.startActivity(intencao);

		// } else if (login() == false) {
		// Toast.makeText(Login_Activity.this,
		// "ERRO! Username ou Password incorrectos ",
		// Toast.LENGTH_LONG).show();
		// credencial.setText("");
		// password.setText("");
		// }
		// }
		// }
		// });

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// info da Internet
				verificaLigacaoNet = detetor.existeConexao();

				// se existir conexao, faz pedido
				if (verificaLigacaoNet) {
					new LoginService(Login_Activity.this,
							CommunicationCenter.LoginService, new LoginRequest(
									credencial.getText().toString(), password
											.getText().toString()))
							.execute(new String[0]);

				} else {

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
					
					
					
					

				}
			}
		});

	}

	@Override
	public void callbackCall(LoginResponse t) {
		// TODO Auto-generated method stub

		String valorStatusCd = t.getStatusCd();
		String valorStatusTxT = t.getStatusTxt();
		String valorToken = t.getToken();

		if (valorStatusCd.equals("OK") && valorStatusTxT.equals("Sucesso")) {
			progressBar.setVisibility(View.VISIBLE);
			Intent intencao = new Intent(Login_Activity.this,
					Info_Activity.class);
			// para passar info para prox activity
			// intencao.putExtra("TOKEN", valorToken);
			Login_Activity.this.startActivity(intencao);
		} else if (valorStatusCd.equals("KO")
				&& valorStatusTxT.equals("Credenciais inválidas.")) {
			Toast.makeText(Login_Activity.this, "Credenciais inválidas.",
					Toast.LENGTH_LONG).show();
		}

	}

	// public boolean login() {
	// ArrayList<Users> arrayUsers = Users.generateFakeUsers();
	// username = credencial.getText().toString();
	// pass = password.getText().toString();
	// for (Users user : arrayUsers) {
	// if (user.getUser().equals(username) && user.getPass().equals(pass)) {
	// return true;
	// }
	// }
	// return false;
	// }

}
