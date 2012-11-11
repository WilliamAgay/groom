package com.opendata.groom.components;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import bma.groomservice.data.Poi;
import bma.groomservice.data.dataprovence.DataprovenceManager;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;

public class SortieListAdapter extends BaseAdapter {
	private final Context context;

	private final List<Poi> poiList;
	private  boolean  isFavorite;

	public SortieListAdapter(Context context, List<Poi> pois, boolean isFavorite) {
		this.context = context;
		this.poiList = pois;
		this.isFavorite = isFavorite;
	}

	@Override
	public int getCount() {
		// return ((GroomApplication) this.context.getApplicationContext()).pois
		// .size();
		return poiList.size();
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
		final RelativeLayout layout;
		if (view instanceof RelativeLayout) {
			layout = (RelativeLayout) view;
		} else {
			LayoutInflater factory = LayoutInflater.from(context);
			if(!isFavorite)	layout = (RelativeLayout) factory.inflate(R.layout.sortie_list_item, viewGroup, false);
			else	layout = (RelativeLayout) factory.inflate(R.layout.sortie_list_favorite_item, viewGroup, false);
		}

		// Poi poi = ((GroomApplication)
		// this.context.getApplicationContext()).pois
		// .get(index);
		final Poi poi = poiList.get(index);

		((TextView) layout.findViewById(R.id.TextViewSortieListItemTitle))
				.setText(poi.raisonsociale);

		// ((TextView)
		// layout.findViewById(R.id.TextViewSortieListItemAdresse)).setText(poi.voie+" "+
		// poi.codepostal+" "+poi.ville);
		((TextView) layout.findViewById(R.id.TextViewSortieListItemAdresse))
				.setText(poi.theme);

		ImageView imv = ((ImageView) layout.findViewById(R.id.ImageViewSortieListItemTitle ));
		if(poi.theme.equals(DataprovenceManager.THEME_CULTURE)) imv.setImageDrawable(context.getResources().getDrawable(R.drawable.ico_culture));
		else if(poi.theme.equals(DataprovenceManager.THEME_PLEINAIR)) imv.setImageDrawable(context.getResources().getDrawable(R.drawable.ico_culture));
		else if(poi.theme.equals(DataprovenceManager.THEME_RESTAURATION)) imv.setImageDrawable(context.getResources().getDrawable(R.drawable.ico_gastro));
		else if(poi.theme.equals(DataprovenceManager.THEME_SPORT)) imv.setImageDrawable(context.getResources().getDrawable(R.drawable.ico_sport));
		if(layout.findViewById(R.id.ButtonSortieListItemCoeur)!=null)
		{
			layout.findViewById(R.id.ButtonSortieListItemCoeur).setSelected(false);
			layout.findViewById(R.id.ButtonSortieListItemCoeur).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(layout.findViewById(R.id.ButtonSortieListItemCoeur).isSelected())
					{
						((GroomApplication)context.getApplicationContext()).favoritesPoi.add(poi);
						layout.findViewById(R.id.ButtonSortieListItemCoeur).setSelected(true);
					}
					else
					{
						((GroomApplication)context.getApplicationContext()).favoritesPoi.remove(poi);
						layout.findViewById(R.id.ButtonSortieListItemCoeur).setSelected(false);
					}
					((GroomApplication)context.getApplicationContext()).savePoiArrayPref(context);
				}
				
			});
			
		}
		
		if(layout.findViewById(R.id.CheckBoxSortieListItemFavorite)!=null)
		{
			final int indexf= index;	
			layout.findViewById(R.id.CheckBoxSortieListItemFavorite).setSelected(poi.done);
			((CheckBox)layout.findViewById(R.id.CheckBoxSortieListItemFavorite)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					((GroomApplication)context.getApplicationContext()).favoritesPoi.get(indexf).done=isChecked;
					((GroomApplication)context.getApplicationContext()).savePoiArrayPref(context);
				}
			});
		}
		return layout;
	}
}
