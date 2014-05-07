package com.example.volleyjsonexample;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends ActionBarActivity {

	ListView videoList;
	ArrayList<String> videoArray = new ArrayList<String>();
	String feedUrl = "https://gdata.youtube.com/feeds/api/users/twistedequations/uploads?v2&alt=json&start-index=1&max-results=30";
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_main);
		videoList = (ListView) findViewById(R.id.videoList);
		
		final ArrayAdapter <String> videoAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, videoArray);
		videoList.setAdapter(videoAdapter);
		videoAdapter.add("1");
		
		RequestQueue rq = Volley.newRequestQueue(this);
		JsonObjectRequest jsonRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, feedUrl, null, 
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							JSONArray videos = response.getJSONObject("data").getJSONArray("items");
							for (int i = 0; i < videos.length(); i++) {
								videoArray.add(videos.getJSONObject(i).getString("title"));
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						videoAdapter.notifyDataSetChanged();
					}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
				
			}
		});
		rq.add(jsonRequest);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
