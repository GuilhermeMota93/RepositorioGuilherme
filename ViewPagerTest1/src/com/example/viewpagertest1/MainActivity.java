package com.example.viewpagertest1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	TextView textView;
	ViewPager pager;
	PagerTitleStrip strip;
	MyPagerAdapter2 myPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pager = (ViewPager) findViewById(R.id.viewPager);
		pager.setAdapter(new MyPagerAdapter2(MainActivity.this));

//		strip = (PagerTitleStrip) findViewById(R.id.tituloViewPagerTeste);
		textView = (TextView) findViewById(R.id.textViewTeste);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

}