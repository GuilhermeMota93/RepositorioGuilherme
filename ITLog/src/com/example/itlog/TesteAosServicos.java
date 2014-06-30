package com.example.itlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.itlog.activities.AddProj_Activity;
import com.example.itlog.activities.Info_Activity;
import com.example.itlog.activities.MeusProj_Activity;

public class TesteAosServicos extends Activity {

	Button btadd, btget;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teste_aos_servicos);
		
		
		btadd = (Button) findViewById(R.id.addDevice);
		btget = (Button) findViewById(R.id.getDevice);
		
		
		btadd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {//accao ao clicar ao ADICIONAR PROJECTO
				// TODO Auto-generated method stub
				Intent intencao = new Intent(TesteAosServicos.this, AddDeviceActivity.class);//ao carregar no botao MEUS PROJECTOS vai para MeusProj
				TesteAosServicos.this.startActivity(intencao);
			}
		});
		
		btget.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//accao ao clicar ao ADICIONAR PROJECTO
				// TODO Auto-generated method stub
				Intent intencao = new Intent(TesteAosServicos.this, GetDeviceActivity.class);//ao carregar no botao ADICIONAR PROJECTOS vai para AddProj_Activity
				TesteAosServicos.this.startActivity(intencao);
			}
		});
		
	}

}
