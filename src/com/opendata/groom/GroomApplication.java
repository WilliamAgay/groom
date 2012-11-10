package com.opendata.groom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import bma.groomservice.data.Poi;


public class GroomApplication extends Application {

	public static final String PREF_ACCOUNT_BUDGET = "budget";
	public static final String PREF_ACCOUNT_NAME = "name";
	public static final String PREF_ACCOUNT_STATUS = "status";
	public static final String PREF_FAVORITES = "favorites";

	public static final int CSTE_CULTURE = 0;
	public static final int CSTE_PLEINAIR = 1;
	public static final int CSTE_SPORT = 2;
	public static final int CSTE_RESTO = 3;
	public static final String CSTE_CURRENT_THEME = "keyTheme";
	
	public List<String> theme_pleinAirTTS = Arrays.asList("respirer","air","plein air", "espace", "détente", "environnement", "vert", "espace");
	public List<String> theme_restaurationTTS = Arrays.asList("restaurant", "restauration", "manger", "déguster", "fast food", "appétit");
	public List<String> theme_sportTTS = Arrays.asList("sport","santé", "motivation", "bouger", "courrir", "se dégourdir", "endurer");
	public List<String> theme_cultureTTS = Arrays.asList("culture", "cultiver", "penser", "musique", "théàtre", "cinéma");


	


	public List<Poi> pois = new ArrayList<Poi>();
	public List<Poi> favoritesPoi = new ArrayList<Poi>();
	public List<String> themes = new ArrayList<String>();

	public String accountName = null;
	public String accountStatus = null;
	public int accountBudget = -1;

	@Override
	public void onCreate() {
		super.onCreate();
		readAccountDataInPref();
		// List<Poi> pois = new DataprovenceFileHelper(
		// "RestaurantsGastronomiques.json").find(null);
		;
		// ArrayList prefs = new ArrayList<String>();
		// prefs.add(object);
		//
		//
		// {"raisonsociale" = p.readString();
		// "tlphone" = p.readString();
		// "adresseWeb" = p.readString();
		// "latitude" = p.readDouble();
		// "longitude" = p.readDouble();
		// "codepostal" = p.readString();
		// "type" = p.readString();
		// "ville" = p.readString();
		// "voie" = p.readString();}
		//
		// setStringArrayPref(this, PREF_FAVORITES, prefs);
	}

	public void readAccountDataInPref() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		if (prefs.contains(PREF_ACCOUNT_NAME))
			accountName = prefs.getString(PREF_ACCOUNT_NAME, null);
		if (prefs.contains(PREF_ACCOUNT_STATUS))
			accountStatus = prefs.getString(PREF_ACCOUNT_STATUS, null);
		if (prefs.contains(PREF_ACCOUNT_BUDGET))
			accountBudget = prefs.getInt(PREF_ACCOUNT_BUDGET, -1);

	}

	public void saveAccountDataInPref() {

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PREF_ACCOUNT_NAME, accountName);
		editor.putString(PREF_ACCOUNT_STATUS, accountStatus);
		editor.putInt(PREF_ACCOUNT_BUDGET, accountBudget);

		editor.commit();
	}

	public  ArrayList<Poi> getPoisArrayPref(Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

		String json = prefs.getString(PREF_FAVORITES, null);
		ArrayList<Poi> pois = new ArrayList<Poi>();
		if (json != null) {
			try {
				JSONArray a = new JSONArray(json);
				for (int i = 0; i < a.length(); i++) {
					Poi poi = new Gson().fromJson(a.optString(i), Poi.class);
					favoritesPoi.add(poi);
				}
			} catch (JSONException e) {
				Log.e("getPoisArrayPref", e.getMessage());
			}
		}
		return pois;
	}

	public  void savePoiArrayPref(Context context) 
	{

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		
		JSONArray a = new JSONArray();
		for (int i = 0; i < favoritesPoi.size(); i++) {
			a.put(favoritesPoi.get(i));
		}
		if (!favoritesPoi.isEmpty()) {
			editor.putString(PREF_FAVORITES, a.toString());
		} else {
			editor.putString(PREF_FAVORITES, null);
		}
		editor.commit();
	}

}
