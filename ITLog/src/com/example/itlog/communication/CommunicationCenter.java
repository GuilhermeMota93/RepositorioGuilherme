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

import com.example.itlog.objects.Funcionario;
import com.google.gson.Gson;

public class CommunicationCenter {

	private static final String TAG = "CommunicationCenter";
	// private static String BaseUrl =
	// "http://192.168.16.87:8080/millenniumNotificationServer/services";
	private static String BaseUrl = "http://itlogapidev.ebankit.com/";

	// SERVIÇOS PARA TESTES

	// PARA GET E POST
	public static final String GetDevices = "getDevices.php";
	// PARA POST
	public static final String AddDevice = "addDevice.php";

	// String de GETs
	public static final String GetSessionInformationService = "API/SessionInfo";
	public static final String GetClientesLstService = "API/ClienteLst";
	public static final String GetProjectosLstService = "API/ProjectosLst";

	public static final String ListProjectsOfTheUser = "listprojectsuser.php?";
	public static final String ListAllProjects = "listallprojects.php?";
	public static final String GetCalendar = "getcalendar.php?";
	public static final String ListTotalHoursPerCompany = "listtotalhourscompany.php?";
	public static final String ListTotalHoursPerProject = "listtotalhoursproject.php?";

	// String de POSTs
	public static final String LoginService = "API/Login";
	public static final String PostProjectoLstService = "API/AddProjectoToNucLst";
	public static final String AddProject = "API/Projecto";
	
	
	public static final String AllocateHours = "allocatehours.php?";

	private static int timeoutConnection = 10000;
	
	private static String resultString;

	public static <T> T callGetService(String nomeServico, String[] info,
			Class<T> resposta) {

		// GET
		boolean isGetSession = nomeServico.equals(GetSessionInformationService);
		boolean isGetClienteLst = nomeServico.equals(GetClientesLstService);
		boolean isGetProjectoLst = nomeServico.equals(GetProjectosLstService);

		boolean isListProjectUser = nomeServico.equals(ListProjectsOfTheUser);
		boolean isListAllProjects = nomeServico.equals(ListAllProjects);
		boolean isGetCalendar = nomeServico.equals(GetCalendar);
		boolean isListTotalHoursCompany = nomeServico
				.equals(ListTotalHoursPerCompany);
		boolean isListTotalHoursProject = nomeServico
				.equals(ListTotalHoursPerProject);

		// TESTES AQUI
		boolean isGetDevices = nomeServico.equals(GetDevices);

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
			} else if (isListProjectUser) {
				builder.append("username=" + info[0]).append('&')
						.append("projects=" + info[1]).append('&')
						.append("fullname=" + info[2]).append('&')
						.append("projectid=" + info[3]).append('&')
						.append("company=" + info[4])
						.append("manager=" + info[5])
						.append("descritption=" + info[6]);
			} else if (isListAllProjects) {
				builder.append("projects=" + info[0]).append('&')
						.append("fullname=" + info[1]).append('&')
						.append("projectid=" + info[2]).append('&')
						.append("company=" + info[3])
						.append("manager=" + info[4])
						.append("descritption=" + info[5]);
			} else if (isGetCalendar) {
				builder.append("month=" + info[0]).append('&')
						.append("business days=" + info[1]).append('&')
						.append("holidays=" + info[2]).append('&');
			} else if (isListTotalHoursCompany) {
				builder.append("username=" + info[0]).append('&')
						.append("companies=" + info[1]).append('&')
						.append("company=" + info[2]).append('&')
						.append("hours=" + info[3]).append('&');
			} else if (isListTotalHoursProject) {
				builder.append("username=" + info[0]).append('&')
						.append("projects=" + info[1]).append('&')
						.append("project=" + info[2]).append('&')
						.append("hours=" + info[3]).append('&');
				// } else if (isGetDevices) {
				// builder.append("response=" + info[0]).append('&')
				// .append("gcmId=" + info[1]).append('&')
				// .append("number=" + info[2]).append('&');
				// }
			}

			if (isGetSession || isListProjectUser || isGetProjectoLst || isGetClienteLst || isListAllProjects
					|| isGetCalendar || isListTotalHoursCompany
					|| isListTotalHoursProject || isGetDevices) {
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
					// return (T) gson.fromJson(resultString, resposta);

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
		// isto esta bem?!
		return (T) gson.fromJson(resultString, resposta);
	}

	// url do serviço, objecto, class resposta
	public static <T> T callPostService(String nomeServico, Object object,
			Class<T> resposta) {

		// POST
		boolean isLogin = nomeServico.equals(LoginService);
		boolean isPostProjecto = nomeServico.equals(PostProjectoLstService);
		
		boolean isAddProject = nomeServico.equals(AddProject);
		boolean isAllocateHours = nomeServico.equals(AllocateHours);

		// TESTE AQUI
		boolean isAddDevices = nomeServico.equals(AddDevice);
		boolean isGetDevices = nomeServico.equals(GetDevices);

		URL url;
		HttpURLConnection connection2 = null;
		Gson gson2 = new Gson();
		if (isAddProject || isPostProjecto || isAllocateHours || isAddDevices || isGetDevices
				|| isLogin) {
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
				// connection2.setUseCaches(false);
				// connection2.setDoInput(true);
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
