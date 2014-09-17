package com.example.itlog.communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.client.ClientProtocolException;
import android.util.Log;
import com.google.gson.Gson;

public class CommunicationCenter {
	private static final String TAG = "CommunicationCenter";
	private static String BaseUrl = "http://itlogapidev.ebankit.com/";
	// PARA GET E POST
	public static final String GetDevices = "getDevices.php";
	// PARA POST
	public static final String AddDevice = "addDevice.php";
	// String de GETs
	public static final String GetClientesLstService = "API/ClienteLst";
	public static final String GetProjectosLstService = "API/ProjectosLst";
	public static final String GetLstAusencias = "API/MovAusLst";
	public static final String GetLstProjsEAusencias = "API/ProjectosAndAusLst";
	public static final String GetSessionInformationService = "API/SessionInfo";
	// String de POSTs
	public static final String PostInfoCliente = "API/Cliente";
	public static final String PostLoginService = "API/Login";
	public static final String PostAddProjectoLstService = "API/AddProjectoToNucLst";
	public static final String PostProject = "API/Projecto";
	public static final String ProjectosByCli = "API/ProjectosByCli";
	public static final String PostDelProjecto = "API/DelProjectoFromNucLst";
	public static final String PostTimeSheets = "API/TimeSheets";

	private static int timeoutConnection = 10000;
	private static String resultString;

	public static <T> T callGetService(String nomeServico, String[] info,
			Class<T> resposta) {
		// GET
		boolean isGetSession = nomeServico.equals(GetSessionInformationService);
		boolean isGetClienteLst = nomeServico.equals(GetClientesLstService);
		boolean isGetLstAusencias = nomeServico.equals(GetLstAusencias);
		boolean isGetProjectoLst = nomeServico.equals(GetProjectosLstService);
		boolean isGetProjsEAusencias = nomeServico
				.equals(GetLstProjsEAusencias);

		BufferedReader readerBuffer = null;
		HttpURLConnection connection = null;
		Gson gson = new Gson();
		int infoSize = 0;
		if (info != null) {
			infoSize = info.length;
		}
		StringBuilder builder = new StringBuilder(BaseUrl).append(nomeServico)
				.append("?");
		if (info != null && info.length > 0) {
			if (isGetSession) {
				builder.append("token=" + info[0]);
			} else if (isGetClienteLst) {
				builder.append("token=" + info[0]);
			} else if (isGetProjectoLst) {
				builder.append("token=" + info[0]);
			} else if (isGetProjsEAusencias) {
				builder.append("token=" + info[0]);
			} else if (isGetLstAusencias) {
				builder.append("token=" + info[0]);
			}

			if (isGetSession || isGetClienteLst || isGetProjsEAusencias
					|| isGetProjectoLst || isGetLstAusencias) {
				try {
					Log.d("Nome servico GET", nomeServico);
					URL url = new URL(builder.toString());
					Log.d("Builder GET", url.toString());
					connection = (HttpURLConnection) url.openConnection();
					// connection = setConnectTimeout (timeoutConnection);
					connection.setRequestMethod("GET");
					// connection.setDoInput(true);

					InputStream inputStream = null;

					if (connection instanceof HttpURLConnection) {
						HttpURLConnection httpConnection = (HttpURLConnection) connection;
						int statusCode = httpConnection.getResponseCode();
						if (statusCode != 200)
							return null;
						else
							inputStream = httpConnection.getInputStream();
					}

					readerBuffer = new BufferedReader(new InputStreamReader(
							inputStream));

					StringWriter stringWriter = new StringWriter();
					char[] buffer = new char[1024 * 4];
					int n = 0;
					while (-1 != (n = readerBuffer.read(buffer))) {
						stringWriter.write(buffer, 0, n);
					}

					resultString = stringWriter.toString();
					Log.d("Resposta GET", resultString);
					Log.d("Classe resposta do GET", resposta.toString());
					gson.fromJson(resultString, resposta);
				} catch (ClientProtocolException e) {
					Log.e(TAG,
							"Got an ClientProtocolException: " + e.getMessage());
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					Log.e(TAG, "Got an IOException: " + e.getMessage());
					e.printStackTrace();
					return null;
				}
			}
		}
		return (T) gson.fromJson(resultString, resposta);
	}

	// url do serviço, objecto, class resposta
	public static <T> T callPostService(String nomeServico, Object object,
			Class<T> resposta) {

		// POST
		boolean isInfoCliente = nomeServico.equals(PostInfoCliente);
		boolean isLogin = nomeServico.equals(PostLoginService);
		boolean isPostProjectoLista = nomeServico
				.equals(PostAddProjectoLstService);
		boolean isProjectosByCli = nomeServico.equals(ProjectosByCli);
		boolean isDelProject = nomeServico.equals(PostDelProjecto);
		boolean isAddProject = nomeServico.equals(PostProject);
		boolean isTimeSheets = nomeServico.equals(PostTimeSheets);

		URL url;
		HttpURLConnection connection2 = null;
		Gson gson2 = new Gson();
		if (isInfoCliente || isAddProject || isPostProjectoLista
				|| isProjectosByCli || isDelProject || isLogin || isTimeSheets) {
			try {
				Log.d("Nome servico POST", nomeServico);
				// Create connection
				StringBuilder builder = new StringBuilder(BaseUrl)
						.append(nomeServico);
				Log.d("Builder POST", builder.toString());
				url = new URL(builder.toString());
				connection2 = (HttpURLConnection) url.openConnection();
				connection2.setRequestMethod("POST");
				// add the content type of the request, most post data is of
				// this type
				connection2.addRequestProperty("Content-Type",
						"application/json");
				connection2.setDoOutput(true);

				// Send request -> de JAVA para JSON
				DataOutputStream wr = new DataOutputStream(
						connection2.getOutputStream());

				String abc = gson2.toJson(object);
				wr.writeBytes(abc);

				// escrever
				Log.d("Print object POST", abc);
				wr.flush();
				wr.close();

				// Get Response -> de JSON para JAVA
				InputStream is = connection2.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String line = "";
				StringBuffer response = new StringBuffer();
				while ((line = br.readLine()) != null) {
					response.append(line);
					response.append('\r');
				}
				Log.d("Print line POST", response.toString());
				br.close();
				
				
				T t = gson2.fromJson(response.toString(), resposta);
				Log.d("Print line Object", gson2.toJson(t));
				return (T) gson2.fromJson(response.toString(), resposta);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				if (connection2 != null) {
					connection2.disconnect();
				}
			}
		} else
			return null;
	}
}
