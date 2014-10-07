package com.example.viewpagertest1;

import java.util.Calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class MyPagerAdapter2 extends PagerAdapter {

	private static final String[] titles = { "one", "two", "three", "four",
			"five" };

	private LayoutInflater inflater;
	private Context context;

	public MyPagerAdapter2(final Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (View) arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		
		inflater = (LayoutInflater) container.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View v = inflater.inflate(R.layout.gridview_anterior, null);

		Calendar month = Calendar.getInstance();
		Calendar itemmonth = (Calendar) month.clone();

		Calendario_Adapter adapter = new Calendario_Adapter(context, month);
		GridView myGrid = (GridView) v.findViewById(R.id.gridViewCustom2);

		myGrid.setAdapter(adapter);
		
		((ViewPager)container).addView(v, 0);

		return v;

	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return titles[position];
	}
}
