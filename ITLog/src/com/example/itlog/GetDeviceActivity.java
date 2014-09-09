package com.example.itlog;

import com.example.itlog.activities.Info_Activity;
import com.example.itlog.activities.Login_Activity;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.responseobjects.POST_API_Login_Response;

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
		// para todos os elementos
		StringBuilder sb = new StringBuilder();
		int tamanho = t.response.devices.size();
		boolean separador = false;
		for (int i = 0; i < tamanho; i++) {
			if (separador)
				sb.append(',');
			separador = true;
			//sb.append(t.response.get(i));
			txtV.setText(sb.append(t.response.devices.get(i).getGcmId()));
		}
	}
}
