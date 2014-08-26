package com.example.itlog.adapters;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.services.ListAllProjectsService;

public class ViewPager_Adapter extends PagerAdapter {

	ArrayList<Calendar> listaMesesMostrar = new ArrayList<Calendar>();
	ArrayList<Calendar> listaRetornoNovoAno = new ArrayList<Calendar>();
	ArrayList<Calendar> listaRetornoMenosAno = new ArrayList<Calendar>();

	Calendar month, itemmonth, mesAtual, mesMais, mesMenos, mesMaisDois,
			mesMenosDois, janeiro, fevereiro, marco, abril, maio, junho, julho,
			agosto, setembro, outubro, novembro, dezembro;
	int mesAtualIndice, mesMaisIndice, mesMenosIndice, mesMaisDoisIndice,
			mesMenosDoisIndice;

	GridView myGrid;

	private LayoutInflater inflater;
	private Context context;

	public ViewPager_Adapter(final Context context) {
		super();
		this.context = context;
		getListaMesesMostrar();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaMesesMostrar.size();
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
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
		inflater = (LayoutInflater) container.getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.gridview, null);
		month = listaMesesMostrar.get(position);
		final Calendario_Adapter adapter = new Calendario_Adapter(context,
				month);
		myGrid = (GridView) v.findViewById(R.id.gridViewCustom2);
		myGrid.setAdapter(adapter);
		((ViewPager) container).addView(v, 1);

		/*
		 * ao clickar num dia fora do mes corrente, "salta" para esse mes e muda
		 * no calendario na view atual
		 */
		myGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				((Calendario_Adapter) parent.getAdapter()).setSelected(view);
				// AQUI BUSCAR CLICK DO DIA. USAR PARA MULTI DIAS SELECTION ????
				String selectedGridDate = adapter.getDayString(position);
				String[] separatedTime = selectedGridDate.split("-");
				// tira ultima parte de uma data. ex: 2 from 2012-12-02
				String gridvalueString = separatedTime[2].replaceFirst("^0*",
						"");
				int gridValue = Integer.parseInt(gridvalueString);
				((Calendario_Adapter) parent.getAdapter()).setSelected(view);
				showToast(selectedGridDate);
			}
		});
		return v;
	}

	protected void showToast(String string) {
		Toast.makeText(context, string, Toast.LENGTH_SHORT).show();

	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public CharSequence getPageTitle(int position) {
		return new SimpleDateFormat("MMM").format(listaMesesMostrar.get(
				position).getTime())
				+ " " + listaMesesMostrar.get(position).get(Calendar.YEAR);

	}

	public ArrayList<Calendar> getListaMesesMostrar() {
		janeiro = Calendar.getInstance();
		janeiro.set(Calendar.MONTH, Calendar.JANUARY);
		fevereiro = Calendar.getInstance();
		fevereiro.set(Calendar.MONTH, Calendar.FEBRUARY);
		marco = Calendar.getInstance();
		marco.set(Calendar.MONTH, Calendar.MARCH);
		abril = Calendar.getInstance();
		abril.set(Calendar.MONTH, Calendar.APRIL);
		maio = Calendar.getInstance();
		maio.set(Calendar.MONTH, Calendar.MAY);
		junho = Calendar.getInstance();
		junho.set(Calendar.MONTH, Calendar.JUNE);
		julho = Calendar.getInstance();
		julho.set(Calendar.MONTH, Calendar.JULY);
		agosto = Calendar.getInstance();
		agosto.set(Calendar.MONTH, Calendar.AUGUST);
		setembro = Calendar.getInstance();
		setembro.set(Calendar.MONTH, Calendar.SEPTEMBER);
		outubro = Calendar.getInstance();
		outubro.set(Calendar.MONTH, Calendar.OCTOBER);
		novembro = Calendar.getInstance();
		novembro.set(Calendar.MONTH, Calendar.NOVEMBER);
		dezembro = Calendar.getInstance();
		dezembro.set(Calendar.MONTH, Calendar.DECEMBER);

		listaMesesMostrar.add(janeiro);
		listaMesesMostrar.add(fevereiro);
		listaMesesMostrar.add(marco);
		listaMesesMostrar.add(abril);
		listaMesesMostrar.add(maio);
		listaMesesMostrar.add(junho);
		listaMesesMostrar.add(julho);
		listaMesesMostrar.add(agosto);
		listaMesesMostrar.add(setembro);
		listaMesesMostrar.add(outubro);
		listaMesesMostrar.add(novembro);
		listaMesesMostrar.add(dezembro);

		return listaMesesMostrar;

	}

	// nova lista de ano seguinte ao corrente
	public void getListaMesesMostrarMaisUm() {
		// refresh ao arraylist sempre que e preciso +1 ano
		listaRetornoNovoAno = new ArrayList<Calendar>();

		// a lista de meses original a mostrar vai aumentado com novos/menos
		// anos...primeiro 12, depois 24, etc
		// vai buscar a lista de meses a mostrar, os ultimos 12 meses para
		// "clonar"
		int i = listaMesesMostrar.size() - 12;
		for (int j = i; j < listaMesesMostrar.size(); j++) {
			listaRetornoNovoAno
					.add((Calendar) listaMesesMostrar.get(j).clone());
		}
		// com a lista clonada, substitui, adicionando 1 ano a cada elemento
		for (int k = 0; k < listaRetornoNovoAno.size(); k++) {
			listaRetornoNovoAno.get(k).add(Calendar.YEAR, 1);
			// listaRetornoNovoAno.add(listaRetornoNovoAno.get(k));
		}
		// adiciona a lista a mostrar, o novo ano
		for (Calendar listaQ : listaRetornoNovoAno)
			listaMesesMostrar.add(listaQ);

	}

	// nova lista de ano anterior ao corrente
	public void getListaMesesMostarMenosUm() {
		// refresh ao arraylist sempre que e preciso -1 ano
		listaRetornoMenosAno = new ArrayList<Calendar>();

		// a lista de meses original a mostrar vai aumentado com novos/menos
		// anos...primeiro 12, depois 24, etc
		// vai buscar a lista de meses a mostrar, os primeiros 12 meses para
		// "clonar"
		for (int i = 0; i < 12; i++)
			listaRetornoMenosAno.add((Calendar) listaMesesMostrar.get(i)
					.clone());
		// com a lista clonada, substitui, retirando 1 ano a cada elemento
		for (int k = 0; k < listaRetornoMenosAno.size(); k++) {
			listaRetornoMenosAno.get(k).add(Calendar.YEAR, -1);
			// listaRetornoMenosAno.add(listaRetornoMenosAno.get(k));
		}
		// adiciona a lista a mostrar, o ano anterior
		for (int i = listaRetornoMenosAno.size() - 1; i >= 0; i--) {
			listaMesesMostrar.add(0, listaRetornoMenosAno.get(i));
		}

	}

}
