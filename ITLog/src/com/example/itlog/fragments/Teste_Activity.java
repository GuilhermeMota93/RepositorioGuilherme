package com.example.itlog.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.itlog.R;
import com.example.itlog.adapters.ViewPager_Adapter;

public class Teste_Activity extends FragmentActivity {

	ViewPager viewPage = null;
	ViewPager_Adapter viewPageAdapter;
	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle saveInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(saveInstanceState);
		setContentView(R.layout.viewpager_layout);
		
		viewPage = (ViewPager) findViewById(R.id.meuViewPager);
		viewPage.setAdapter(new ViewPager_Adapter(fm));
	}

}
