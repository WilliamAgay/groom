package com.opendata.groom;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class GroomApplication extends Application{

	public static final String PREF_ACCOUNT_BUDGET = "budget";
	public static final String PREF_ACCOUNT_NAME = "name";
	public static final String PREF_ACCOUNT_STATUS = "status";
	
	
	public String accountName=  null;
	public String accountStatus=  null;
	public int accountBudget=  -1;
	   


	@Override
	public void onCreate() 
	{
		super.onCreate();
		readAccountDataInPref();
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
	

}
