package com.opendata.groom;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class GroomApplication extends Application{

	public static final String PREF_ACCOUNT_BUDGET = "budget";
	public static final String PREF_ACCOUNT_NAME = "name";
	public static final String PREF_ACCOUNT_STATUS = "status";
	public static final String PREF_FAVORITES = "favorites";
	
	
	public String accountName=  null;
	public String accountStatus=  null;
	public int accountBudget=  -1;
	   


	@Override
	public void onCreate() 
	{
		super.onCreate();
		readAccountDataInPref();
//		List<Poi> pois = new DataprovenceFileHelper(
//				"RestaurantsGastronomiques.json").find(null);
				;
//		ArrayList prefs =  new ArrayList<String>();
//		prefs.add(object);
//		
//		
//		{"raisonsociale" = p.readString();
//		"tlphone" = p.readString();
//		"adresseWeb" = p.readString();
//		"latitude" = p.readDouble();
//		"longitude" = p.readDouble();
//		"codepostal" = p.readString();
//		"type" = p.readString();
//		"ville" = p.readString();
//		"voie" = p.readString();}
//		
//		setStringArrayPref(this, PREF_FAVORITES, prefs);
	}
	
	
	
	
	public void readAccountDataInPref() 
	{
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	    
	    if(prefs.contains(PREF_ACCOUNT_NAME)) accountName=  prefs.getString(PREF_ACCOUNT_NAME, null);
	    if(prefs.contains(PREF_ACCOUNT_STATUS)) accountStatus=  prefs.getString(PREF_ACCOUNT_STATUS, null);
	    if(prefs.contains(PREF_ACCOUNT_BUDGET)) accountBudget=  prefs.getInt(PREF_ACCOUNT_BUDGET, -1);
	    
	 
	}
	
	public void saveAccountDataInPref() 
	{
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(PREF_ACCOUNT_NAME, accountName);
	    editor.putString(PREF_ACCOUNT_STATUS, accountStatus);
	    editor.putInt(PREF_ACCOUNT_BUDGET, accountBudget);
	
	    editor.commit();
	}
	
	public static ArrayList<String> getStringArrayPref(Context context, String key) {
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		
	    String json = prefs.getString(key, null);
	    ArrayList<String> urls = new ArrayList<String>();
	    if (json != null) {
	        try {
	            JSONArray a = new JSONArray(json);
	            for (int i = 0; i < a.length(); i++) {
	                String url = a.optString(i);
	                urls.add(url);
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	    }
	    return urls;
	}
	public static void setStringArrayPref(Context context, String key, ArrayList<String> values) {
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
	    SharedPreferences.Editor editor = prefs.edit();
	    JSONArray a = new JSONArray();
	    for (int i = 0; i < values.size(); i++) {
	        a.put(values.get(i));
	    }
	    if (!values.isEmpty()) {
	        editor.putString(key, a.toString());
	    } else {
	        editor.putString(key, null);
	    }
	    editor.commit();
	}

}
