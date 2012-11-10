package com.opendata.groom.components;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import bma.groomservice.data.Poi;

import com.opendata.groom.R;


final class SortieListAdapter extends BaseAdapter {
  private final Context context;
  
  private ArrayList<Poi> poiList = new ArrayList<Poi>();

  SortieListAdapter(Context context, ArrayList<Poi> pois) {
    this.context = context;
    this.poiList=pois;
  }

  @Override
  public int getCount() {
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
    RelativeLayout layout;
    if (view instanceof RelativeLayout) {
      layout = (RelativeLayout) view;
    } else {
      LayoutInflater factory = LayoutInflater.from(context);
      layout = (RelativeLayout) factory.inflate(R.layout.sortie_list_item, viewGroup, false);
    }

   Poi poi= poiList.get(index);


      ((TextView) layout.findViewById(R.id.TextViewSortieListItemTitle)).setText(poi.type);
   
      ((TextView) layout.findViewById(R.id.TextViewSortieListItemAdresse)).setText(poi.voie+" "+ poi.codepostal+" "+poi.ville);
    
    return layout;
  }
}
