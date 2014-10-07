package com.example.itlog.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Detecta_Conexao_Internet {
	private Context context;

	public Detecta_Conexao_Internet(Context context) {
		super();
		this.context = context;
	}

	public boolean existeConexao() {
		ConnectivityManager conectividade = (ConnectivityManager)context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conectividade != null) {
			NetworkInfo[] infoNetwork = conectividade.getAllNetworkInfo();
			if (infoNetwork != null)
				for (int i = 0; i < infoNetwork.length; i++)
					if (infoNetwork[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}
}
