package com.example.volleyexample2;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

	private RequestQueue mRequestQueue;
	private String TAG = this.getClass().getSimpleName();
	private ListView lstView;
	private ArrayList<NewsModel> arrNews;
	private LayoutInflater lf;
	private VolleyAdapter va;
	private ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lf = LayoutInflater.from(this);
		arrNews = new ArrayList<NewsModel>();
		va = new VolleyAdapter();

		lstView = (ListView) findViewById(R.id.listView);
		lstView.setAdapter(va);
		mRequestQueue = Volley.newRequestQueue(this);
		String url = "http://pipes.yahooapis.com/pipes/pipe.run?_id=giWz8Vc33BG6rQEQo_NLYQ&_render=json";
		pd = ProgressDialog.show(this, "Please Wait...", "Please Wait...");

		JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url,
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						Log.i(TAG, response.toString());
						parseJSON(response);
						va.notifyDataSetChanged();
						pd.dismiss();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.i(TAG, error.getMessage());
					}
				});
		mRequestQueue.add(jr);
	}

	private void parseJSON(JSONObject json) {
		try {
			JSONObject value = json.getJSONObject("value");
			JSONArray items = value.getJSONArray("items");
			for (int i = 0; i < items.length(); i++) {
				JSONObject item = items.getJSONObject(i);
				NewsModel nm = new NewsModel();
				nm.setTitle(item.optString("title"));
				nm.setDescription(item.optString("descripiton"));
				nm.setLink(item.optString("link"));
				nm.setPubDate(item.optString("pubDate"));
				arrNews.add(nm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class NewsModel {
		private String title;
		private String link;
		private String description;
		private String pubDate;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPubDate() {
			return pubDate;
		}

		public void setPubDate(String pubDate) {
			this.pubDate = pubDate;
		}

	}

	class VolleyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return arrNews.size();
		}

		@Override
		public Object getItem(int i) {
			// TODO Auto-generated method stub
			return arrNews.get(i);
		}

		@Override
		public long getItemId(int i) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int i, View view, ViewGroup ViewGroup) {
			ViewHolder vh;

			if (view == null) {
				vh = new ViewHolder();
				view = lf.inflate(R.layout.row_listview, null);
				vh.tvTitle = (TextView) view.findViewById(R.id.txtTitle);
				vh.tvDesc = (TextView) view.findViewById(R.id.txtDesc);
				vh.tvDate = (TextView) view.findViewById(R.id.txtDate);
				view.setTag(vh);
			} else {
				vh = (ViewHolder) view.getTag();
			}
			
			NewsModel nm = arrNews.get(i);
			vh.tvTitle.setText(nm.getTitle());
            vh.tvDesc.setText(nm.getDescription());
            vh.tvDate.setText(nm.getPubDate());			
			return view;
		}
		
		class ViewHolder{
			TextView tvTitle;
			TextView tvDesc;
			TextView tvDate;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

// @Override
// public boolean onOptionsItemSelected(MenuItem item) {
// // Handle action bar item clicks here. The action bar will
// // automatically handle clicks on the Home/Up button, so long
// // as you specify a parent activity in AndroidManifest.xml.
// int id = item.getItemId();
// if (id == R.id.action_settings) {
// return true;
// }
// return super.onOptionsItemSelected(item);
// }
//
// /**
// * A placeholder fragment containing a simple view.
// */
// public static class PlaceholderFragment extends Fragment {
//
// public PlaceholderFragment() {
// }
//
// @Override
// public View onCreateView(LayoutInflater inflater, ViewGroup container,
// Bundle savedInstanceState) {
// View rootView = inflater.inflate(R.layout.fragment_main, container,
// false);
// return rootView;
// }
// }

