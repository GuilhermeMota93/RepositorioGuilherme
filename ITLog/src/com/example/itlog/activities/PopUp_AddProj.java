package com.example.itlog.activities;

import com.example.itlog.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopUp_AddProj extends Activity {
	PopupWindow popUp;
	TextView tvPerguntar;
	Button btSim, btNao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);

		 popUp = new PopupWindow(this);
		 tvPerguntar = (TextView) findViewById(R.id.tvPergunta);
		btSim = (Button) findViewById(R.id.btSIM);
		btNao = (Button) findViewById(R.id.btNAO);

		btSim.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intencao1 = new Intent(PopUp_AddProj.this,
				 Info_Activity.class);//ao carregar no botao SIM retorna a Info
				 PopUp_AddProj.this.startActivity(intencao1);

				finish();
			}
		});

		btNao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
