package com.example.itlog.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.R.id;

public class MostraInfoProj_Activity extends GeneralButtons_Activity {

	TextView textC, textProj, textGestProj, textDescr, textHoras,tv1, tv2;
	Button inputH, eliminaProj, b1, b2;
	Typeface font, font1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrar_proj_layout);
		textC = (TextView) findViewById(id.textCliente);
		textProj = (TextView) findViewById(id.textProjecto);
		textGestProj = (TextView) findViewById(id.textGestor);
		textDescr = (TextView) findViewById(id.textDescr);
		textHoras = (TextView) findViewById(id.textHoras);
		inputH = (Button) findViewById(id.btInput);
		eliminaProj = (Button) findViewById(id.btEliminar);
		
		
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		font1 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
		textC.setTypeface(font);
		textDescr.setTypeface(font1);
		textGestProj.setTypeface(font);
		textHoras.setTypeface(font);
		textProj.setTypeface(font);
		inputH.setTypeface(font);
		eliminaProj.setTypeface(font);
		
		inputH.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {// accao ao clicar ao ADICIONAR
											// PROJECTO
				// TODO Auto-generated method stub
				Intent intencao = new Intent(MostraInfoProj_Activity.this,
						InputHoras_Activity.class);// ao carregar no botao
													// IMPUTAR HORAS vai para
													// InputHoras
				MostraInfoProj_Activity.this.startActivity(intencao);
			}
		});
		
		eliminaProj.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflate = LayoutInflater
						.from(MostraInfoProj_Activity.this);
				View layout = inflate.inflate(R.layout.alertdialog_layout,
						null);;

				tv1 = (TextView) layout.findViewById(R.id.titulo);
				tv1.setText("Eliminar projeto");
				tv2 = (TextView) layout.findViewById(R.id.pergunta);
				tv2.setText("Pretende eliminar este projeto?");
				b1 = (Button) layout.findViewById(R.id.botaoCancela);
				b2 = (Button) layout.findViewById(R.id.botaoConfirma);

				final AlertDialog.Builder builder = new AlertDialog.Builder(
						MostraInfoProj_Activity.this);
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
						Toast.makeText(MostraInfoProj_Activity.this,
								"Projeto eliminado com sucesso! ",
								Toast.LENGTH_LONG).show();
						finish();
					}
				});

			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.actionbar_buttons, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	

}
