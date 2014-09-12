package com.example.itlog.activities;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.adapters.MeusProj_ListView_Adapter;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.objects.Cliente;
import com.example.itlog.objects.Projecto;
import com.example.itlog.requestobjects.POST_API_DelProjectoFromNucLst_Request;
import com.example.itlog.responseobjects.GET_API_ProjectosLst_Response;
import com.example.itlog.responseobjects.POST_API_DelProjectoFromNucLst_Response;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.services.GET_API_ProjectosLst_Service;
import com.example.itlog.services.POST_API_DelProjectoFromNucLst_Service;

public class MeusProj_Activity extends GeneralButtons_Activity {

	ProgressBar progressBar;
	ArrayList<Projecto> projects = new ArrayList<Projecto>();
	POST_API_Login_Response token = POST_API_Login_Response.getInstance();
	Cliente valor, clientesend;
	MeusProj_ListView_Adapter adapterList;
	Intent intencao;
	ListView listView;
	Spinner spinner;
	// o username vem em forma de string desde o log in
	String info;
	// "Font" para o tipo de letra
	Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meusprojs_layout);
		progressBar = (ProgressBar) findViewById(R.id.progressBar3);
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		listView = (ListView) findViewById(R.id.list);

		getServiceListaMeusProjs();
	}

	public void getServiceListaMeusProjs() {
		progressBar.setVisibility(View.VISIBLE);
		new GET_API_ProjectosLst_Service(new CallBackLstProjs(),
				CommunicationCenter.GetProjectosLstService).execute(token
				.getToken());
	}

	public void getServiceEliminaProj(String prjCod) {
//		progressBar.setVisibility(View.VISIBLE);

		new POST_API_DelProjectoFromNucLst_Service(new CallBackElimnaProj(),
				CommunicationCenter.PostDelProjecto,
				new POST_API_DelProjectoFromNucLst_Request(prjCod,
						token.getToken())).execute(new String[0]);
	}

	public class CallBackLstProjs implements
			CallbackInterface<GET_API_ProjectosLst_Response> {

		@Override
		public void callbackCall(final GET_API_ProjectosLst_Response t) {
			// TODO Auto-generated method stub

			projects = t.getProjectos();
			adapterList = new MeusProj_ListView_Adapter(MeusProj_Activity.this,
					R.layout.single_row_listview_meusproj, projects, font);
			listView.setAdapter(adapterList);

			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						final int position, long id) {
					// TODO Auto-generated method stub
					LayoutInflater inflate = LayoutInflater
							.from(MeusProj_Activity.this);
					View layout = inflate.inflate(
							R.layout.proj_eliminar_inputhoras, null);
					TextView tv1 = (TextView) layout.findViewById(R.id.titulo1);
					tv1.setText("Selecionou um projecto");
					TextView tv2 = (TextView) layout
							.findViewById(R.id.descricao);
					tv2.setText("\nQue pretende fazer?");
					Button b1 = (Button) layout
							.findViewById(R.id.botaoEliminar);
					Button b2 = (Button) layout
							.findViewById(R.id.botaoImputarHoras);

					final AlertDialog.Builder builder = new AlertDialog.Builder(
							MeusProj_Activity.this);
					builder.setView(layout);
					final AlertDialog dialog = builder.create();
					dialog.show();
					b1.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							getServiceEliminaProj(projects.get(position)
									.getCod());
							progressBar.setVisibility(View.VISIBLE);
							adapterList.remove(adapterList.getItem(position));
							adapterList.notifyDataSetChanged();
							dialog.dismiss();

						}
					});

					b2.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent intencao = new Intent(
									MeusProj_Activity.this,
									InputHoras_Activity.class);
							MeusProj_Activity.this.startActivity(intencao);
							dialog.dismiss();

						}
					});
				}
			});
		}
	}

	public class CallBackElimnaProj implements
			CallbackInterface<POST_API_DelProjectoFromNucLst_Response> {

		@Override
		public void callbackCall(POST_API_DelProjectoFromNucLst_Response t2) {
			// TODO Auto-generated method stub
			// getServiceListaMeusProjs();
			progressBar.setVisibility(View.GONE);

			if (t2.getStatusCd().equals("KO")) {
				Toast.makeText(MeusProj_Activity.this,
						"Erro ao remover projecto da sua lista!",
						Toast.LENGTH_LONG).show();
			} else if (t2.getStatusCd().equals("OK")) {
				Toast.makeText(MeusProj_Activity.this,
						"Projecto removido com sucesso!", Toast.LENGTH_LONG)
						.show();
			}
		}

	}
}
