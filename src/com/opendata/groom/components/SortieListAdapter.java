package com.opendata.groom.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import bma.groomservice.data.Poi;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;

public class SortieListAdapter extends BaseAdapter {
	private final Context context;

	// private ArrayList<Poi> poiList = new ArrayList<Poi>();

	public SortieListAdapter(Context context) {
		this.context = context;
		// this.poiList=pois;
	}

	@Override
	public int getCount() {
		return ((GroomApplication) this.context.getApplicationContext()).pois
				.size();
	}

	@Override
	public Object getItem(int index) {
		// Not used, so no point in retrieving it.
		return null;
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		RelativeLayout layout;
		if (view instanceof RelativeLayout) {
			layout = (RelativeLayout) view;
		} else {
			LayoutInflater factory = LayoutInflater.from(context);
			layout = (RelativeLayout) factory.inflate(
					R.layout.sortie_list_item, viewGroup, false);
		}

		Poi poi = ((GroomApplication) this.context.getApplicationContext()).pois
				.get(index);

		((TextView) layout.findViewById(R.id.TextViewSortieListItemTitle))
				.setText(poi.raisonsociale);

		// ((TextView)
		// layout.findViewById(R.id.TextViewSortieListItemAdresse)).setText(poi.voie+" "+
		// poi.codepostal+" "+poi.ville);
		((TextView) layout.findViewById(R.id.TextViewSortieListItemAdresse))
				.setText(poi.theme);

		return layout;
	}
}
