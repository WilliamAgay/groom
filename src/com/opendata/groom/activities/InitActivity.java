package com.opendata.groom.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;

public class InitActivity extends Activity implements View.OnClickListener {

	// Handler mHandler = new Handler();
	public int lighter = 0x44000000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.init_activity);
		setTheme(android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);

		Typeface androgyne = Typeface.createFromAsset(getAssets(),
				"Androgyne_TB.otf");

		((TextView) findViewById(R.id.FrameLayoutInitActivityTile1)
				.findViewById(R.id.TextViewInitActivityChoix1))
				.setTypeface(androgyne);
		((TextView) findViewById(R.id.FrameLayoutInitActivityTile2)
				.findViewById(R.id.TextViewInitActivityChoix1))
				.setTypeface(androgyne);
		((TextView) findViewById(R.id.FrameLayoutInitActivityTile3)
				.findViewById(R.id.TextViewInitActivityChoix1))
				.setTypeface(androgyne);
		((TextView) findViewById(R.id.FrameLayoutInitActivityTile4)
				.findViewById(R.id.TextViewInitActivityChoix1))
				.setTypeface(androgyne);

		((TextView) findViewById(R.id.FrameLayoutInitActivityTile1)
				.findViewById(R.id.TextViewInitActivityChoix1))
				.setText(R.string.plein_air);
		((TextView) findViewById(R.id.FrameLayoutInitActivityTile2)
				.findViewById(R.id.TextViewInitActivityChoix1))
				.setText(getString(R.string.sport)+" ");
		((TextView) findViewById(R.id.FrameLayoutInitActivityTile3)
				.findViewById(R.id.TextViewInitActivityChoix1))
				.setText(getString(R.string.restauration)+ " ");
		((TextView) findViewById(R.id.FrameLayoutInitActivityTile4)
				.findViewById(R.id.TextViewInitActivityChoix1))
				.setText(R.string.culture);

		// findViewById(R.id.FrameLayoutInitActivityTile1).setBackgroundResource(R.drawable.ico_plein_air);
		if (((GroomApplication) getApplication()).prefPleinAirSelected)
			findViewById(R.id.FrameLayoutInitActivityTile1).setBackgroundColor(
					lighter);
		else
			findViewById(R.id.FrameLayoutInitActivityTile1).setBackgroundColor(
					Color.TRANSPARENT);
		((ImageView) findViewById(R.id.FrameLayoutInitActivityTile1)
				.findViewById(R.id.idico1))
				.setBackgroundResource(R.drawable.ico_plein_air);

		// findViewById(R.id.FrameLayoutInitActivityTile2).setBackgroundResource(R.drawable.ico_sport);
		if (((GroomApplication) getApplication()).prefSportSelected)
			findViewById(R.id.FrameLayoutInitActivityTile2).setBackgroundColor(
					lighter);
		else
			findViewById(R.id.FrameLayoutInitActivityTile2).setBackgroundColor(
					Color.TRANSPARENT);
		((ImageView) findViewById(R.id.FrameLayoutInitActivityTile2)
				.findViewById(R.id.idico1))
				.setBackgroundResource(R.drawable.ico_sport);

		if (((GroomApplication) getApplication()).prefRestoSelected)
			findViewById(R.id.FrameLayoutInitActivityTile3).setBackgroundColor(
					lighter);
		else
			findViewById(R.id.FrameLayoutInitActivityTile3).setBackgroundColor(
					Color.TRANSPARENT);
		((ImageView) findViewById(R.id.FrameLayoutInitActivityTile3)
				.findViewById(R.id.idico1))
				.setBackgroundResource(R.drawable.ico_gastro);
		// findViewById(R.id.FrameLayoutInitActivityTile3).setBackgroundResource(R.drawable.ico_gastro);

		if (((GroomApplication) getApplication()).prefCultureSelected)
			findViewById(R.id.FrameLayoutInitActivityTile4).setBackgroundColor(
					lighter);
		else
			findViewById(R.id.FrameLayoutInitActivityTile4).setBackgroundColor(
					Color.TRANSPARENT);
		((ImageView) findViewById(R.id.FrameLayoutInitActivityTile4)
				.findViewById(R.id.idico1))
				.setBackgroundResource(R.drawable.ico_culture);
		// findViewById(R.id.FrameLayoutInitActivityTile4).setBackgroundResource(R.drawable.ico_culture);

		((CheckBox) findViewById(R.id.FrameLayoutInitActivityTile1)
				.findViewById(R.id.CheckBoxInitActivityChoix1))
				.setChecked(((GroomApplication) getApplication()).prefPleinAirSelected);
		((CheckBox) findViewById(R.id.FrameLayoutInitActivityTile2)
				.findViewById(R.id.CheckBoxInitActivityChoix1))
				.setChecked(((GroomApplication) getApplication()).prefSportSelected);
		((CheckBox) findViewById(R.id.FrameLayoutInitActivityTile3)
				.findViewById(R.id.CheckBoxInitActivityChoix1))
				.setChecked(((GroomApplication) getApplication()).prefRestoSelected);
		((CheckBox) findViewById(R.id.FrameLayoutInitActivityTile4)
				.findViewById(R.id.CheckBoxInitActivityChoix1))
				.setChecked(((GroomApplication) getApplication()).prefCultureSelected);

		((CheckBox) findViewById(R.id.FrameLayoutInitActivityTile1)
				.findViewById(R.id.CheckBoxInitActivityChoix1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						((GroomApplication) getApplication()).prefPleinAirSelected = isChecked;
						((GroomApplication) getApplication())
								.saveThemeDataInPref(
										GroomApplication.PREF_PLEINAIR,
										isChecked);
						if (((GroomApplication) getApplication()).prefPleinAirSelected)
							findViewById(R.id.FrameLayoutInitActivityTile1)
									.setBackgroundColor(0x44000000);
						else
							findViewById(R.id.FrameLayoutInitActivityTile1)
									.setBackgroundColor(Color.TRANSPARENT);
					}

				});
		((CheckBox) findViewById(R.id.FrameLayoutInitActivityTile2)
				.findViewById(R.id.CheckBoxInitActivityChoix1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						((GroomApplication) getApplication()).prefSportSelected = isChecked;
						((GroomApplication) getApplication())
								.saveThemeDataInPref(
										GroomApplication.PREF_SPORT, isChecked);
						if (((GroomApplication) getApplication()).prefSportSelected)
							findViewById(R.id.FrameLayoutInitActivityTile2)
									.setBackgroundColor(lighter);
						else
							findViewById(R.id.FrameLayoutInitActivityTile2)
									.setBackgroundColor(Color.TRANSPARENT);

					}
				});
		((CheckBox) findViewById(R.id.FrameLayoutInitActivityTile3)
				.findViewById(R.id.CheckBoxInitActivityChoix1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						((GroomApplication) getApplication()).prefRestoSelected = isChecked;
						((GroomApplication) getApplication())
								.saveThemeDataInPref(
										GroomApplication.PREF_RESTO, isChecked);
						if (((GroomApplication) getApplication()).prefRestoSelected)
							findViewById(R.id.FrameLayoutInitActivityTile3)
									.setBackgroundColor(lighter);
						else
							findViewById(R.id.FrameLayoutInitActivityTile3)
									.setBackgroundColor(Color.TRANSPARENT);

					}
				});
		((CheckBox) findViewById(R.id.FrameLayoutInitActivityTile4)
				.findViewById(R.id.CheckBoxInitActivityChoix1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						((GroomApplication) getApplication()).prefCultureSelected = isChecked;
						((GroomApplication) getApplication())
								.saveThemeDataInPref(
										GroomApplication.PREF_CULTURE,
										isChecked);
						if (((GroomApplication) getApplication()).prefCultureSelected)
							findViewById(R.id.FrameLayoutInitActivityTile4)
									.setBackgroundColor(lighter);
						else
							findViewById(R.id.FrameLayoutInitActivityTile4)
									.setBackgroundColor(Color.TRANSPARENT);
					}
				});

		findViewById(R.id.ImageViewInitActivitySettingsValider)
				.setOnClickListener(this);

		findViewById(R.id.TextViewInitActivityChat).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(getApplicationContext(),
								ChatActivity.class));
					}
				});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.ImageViewInitActivitySettingsValider:
			((GroomApplication) getApplication()).fillThemes();
			Intent intent = new Intent(InitActivity.this,
					MainContentActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_init_2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(this, DashboardActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(InitActivity.this, DashboardActivity.class);
		startActivity(intent);
		finish();
	}

}
