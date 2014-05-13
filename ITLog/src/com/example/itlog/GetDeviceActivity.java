package com.example.itlog;

import com.example.itlog.activities.InfoActivity;
import com.example.itlog.activities.LoginActivity;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.responseobjects.LoginResponse;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GetDeviceActivity extends Activity implements
		CallbackInterface<GetDevice_Response> {

	TextView txtV;
	Button bt1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_device_layout);

		txtV = (TextView) findViewById(R.id.textView1);
		bt1 = (Button) findViewById(R.id.buttonGet);

		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// accao ao clicar
				new GetDevice_Service(GetDeviceActivity.this,
						CommunicationCenter.GetDevices,
						new GetDevice_RequestObj()).execute(new String[0]);
			}
		});

	}

	public void callbackCall(GetDevice_Response t) {
		// TODO Auto-generated method stub
		// so para primeiro elemento do array:
		// txtV.setText(t.getResponse().get(0).getGcmId());
		
		//para todos os elementos
		for (int i = 0; i < t.response.size(); i++) {
			txtV.setText(t.getResponse().get(i).getGcmId());
		}
	}
}