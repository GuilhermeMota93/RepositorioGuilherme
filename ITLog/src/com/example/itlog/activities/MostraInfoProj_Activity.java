package com.example.itlog.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.itlog.R;
import com.example.itlog.R.id;

public class MostraInfoProj_Activity extends Activity {

	TextView textC, textProj, textGestProj, textDescr, textHoras;
	Button inputH, eliminaProj;

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
		
		

	}

}
