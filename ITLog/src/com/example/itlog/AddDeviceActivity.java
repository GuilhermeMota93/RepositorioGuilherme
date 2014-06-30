package com.example.itlog;

import com.example.itlog.activities.Info_Activity;
import com.example.itlog.activities.InputHoras_Activity;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDeviceActivity extends Activity implements
		CallbackInterface<AddDevice_Response> {

	EditText edT1;
	Button bt1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_device_layout);

		edT1 = (EditText) findViewById(R.id.editText1);
		bt1 = (Button) findViewById(R.id.buttonAdd);

		bt1.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				new AddDevice_Service(
						(CallbackInterface<AddDevice_Response>) AddDeviceActivity.this,
						CommunicationCenter.AddDevice,
						new AddDevice_RequestObj(edT1.getText().toString()))
						.execute(new String[0]);				
				finish();
			}
		});

	}

	
	@Override
	public void callbackCall(AddDevice_Response t) {
		// pop ups com msg de erros aqui
		// validar
		String valor = (String.valueOf(t.getError().getId()));
		if (valor == Integer.toString(0)) {
			Toast.makeText(getApplicationContext(), "Adicionado com sucesso!",
					Toast.LENGTH_LONG).show();
		} else if (valor == Integer.toString(1))
			Toast.makeText(getApplicationContext(), "ERRO!!!",
					Toast.LENGTH_LONG).show();
		

	}
}
