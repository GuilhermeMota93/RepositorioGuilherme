package com.example.itlog;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddDeviceActivity extends Activity {

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
				//esta bem????
				new AddDevice_Service((CallbackInterface<AddDevice_Response>) AddDeviceActivity.this, CommunicationCenter.AddDevice,
						new AddDevice_RequestObj(null)).execute(new String[0]);
				
			}
		});
		
	}	
	
	public void callbackCall(GetDevice_Response t) {
		// TODO Auto-generated method stub
		
	}
}
