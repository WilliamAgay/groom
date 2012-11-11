package com.opendata.groom.activities;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;

public class InitActivity extends Activity implements View.OnClickListener 
{
	
	
	
//	Handler mHandler = new Handler();
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.init_activity);
		setTheme(android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

		
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		
		
		((TextView)findViewById(R.id.FrameLayoutInitActivityTile1).findViewById(R.id.TextViewInitActivityChoix1)).setText(R.string.plein_air);
		((TextView)findViewById(R.id.FrameLayoutInitActivityTile2).findViewById(R.id.TextViewInitActivityChoix1)).setText(R.string.sport);
		((TextView)findViewById(R.id.FrameLayoutInitActivityTile3).findViewById(R.id.TextViewInitActivityChoix1)).setText(R.string.restauration);
		((TextView)findViewById(R.id.FrameLayoutInitActivityTile4).findViewById(R.id.TextViewInitActivityChoix1)).setText(R.string.culture);
		
		
		
		findViewById(R.id.FrameLayoutInitActivityTile1).setBackgroundResource(R.drawable.ico_plein_air);
		findViewById(R.id.FrameLayoutInitActivityTile2).setBackgroundResource(R.drawable.ico_sport);
		findViewById(R.id.FrameLayoutInitActivityTile3).setBackgroundResource(R.drawable.ico_gastro);
		findViewById(R.id.FrameLayoutInitActivityTile4).setBackgroundResource(R.drawable.ico_culture);
		
		
		
		((CheckBox)findViewById(R.id.FrameLayoutInitActivityTile1).findViewById(R.id.CheckBoxInitActivityChoix1)).setChecked(((GroomApplication)getApplicationContext()).prefPleinAirSelected);
		((CheckBox)findViewById(R.id.FrameLayoutInitActivityTile2).findViewById(R.id.CheckBoxInitActivityChoix1)).setChecked(((GroomApplication)getApplicationContext()).prefSportSelected);
		((CheckBox)findViewById(R.id.FrameLayoutInitActivityTile3).findViewById(R.id.CheckBoxInitActivityChoix1)).setChecked(((GroomApplication)getApplicationContext()).prefRestoSelected);
		((CheckBox)findViewById(R.id.FrameLayoutInitActivityTile4).findViewById(R.id.CheckBoxInitActivityChoix1)).setChecked(((GroomApplication)getApplicationContext()).prefCultureSelected);
		
		((CheckBox)findViewById(R.id.FrameLayoutInitActivityTile1).findViewById(R.id.CheckBoxInitActivityChoix1)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				((GroomApplication)getApplicationContext()).saveThemeDataInPref(GroomApplication.PREF_PLEINAIR, isChecked);

			}
		});
		((CheckBox)findViewById(R.id.FrameLayoutInitActivityTile2).findViewById(R.id.CheckBoxInitActivityChoix1)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				((GroomApplication)getApplicationContext()).saveThemeDataInPref(GroomApplication.PREF_SPORT, isChecked);

			}
		});
		((CheckBox)findViewById(R.id.FrameLayoutInitActivityTile3).findViewById(R.id.CheckBoxInitActivityChoix1)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				((GroomApplication)getApplicationContext()).saveThemeDataInPref(GroomApplication.PREF_RESTO, isChecked);

			}
		});
		((CheckBox)findViewById(R.id.FrameLayoutInitActivityTile4).findViewById(R.id.CheckBoxInitActivityChoix1)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				((GroomApplication)getApplicationContext()).saveThemeDataInPref(GroomApplication.PREF_CULTURE, isChecked);

			}
		});

		
		findViewById(R.id.ImageViewInitActivitySettingsValider).setOnClickListener(this);
		
	}

	
	
	

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) {
		
		
	
		
		
		case R.id.ImageViewInitActivitySettingsValider :
			Intent  intent = new Intent(InitActivity.this,DashboardActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}
	
	

	




		
		 @Override
			public void onBackPressed() {
				Intent  intent = new Intent(InitActivity.this,DashboardActivity.class);
				startActivity(intent);
				finish();
			}

}
