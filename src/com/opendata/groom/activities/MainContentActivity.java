package com.opendata.groom.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;
import com.opendata.groom.polaris.Annotation;
import com.opendata.groom.polaris.MapCalloutView;
import com.opendata.groom.polaris.PolarisMapView;
import com.opendata.groom.polaris.PolarisMapView.OnAnnotationSelectionChangedListener;
import com.opendata.groom.polaris.PolarisMapView.OnMapViewLongClickListener;

public class MainContentActivity extends MapActivity implements
		OnMapViewLongClickListener, OnAnnotationSelectionChangedListener {

	private static final int SORT = 0;
	private PolarisMapView mapView;
	private ArrayList mSelectedItems = new ArrayList(); // Where we track the selected
	private String currentTheme = "";
	// items

	public void onCreate(Bundle savedInstanceState) {

		Log.e("TAG", "onCreate");
		super.onCreate(savedInstanceState);
		

		setContentView(R.layout.polarismaplayout);

		mapView = (PolarisMapView) findViewById(R.id.mapview);
		mapView.setUserTrackingButtonEnabled(true);
		mapView.setOnMapViewLongClickListener(this);
		mapView.setOnAnnotationSelectionChangedListener(this);

		mapView.getController().setZoom(16);
		mapView.preLoad();

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onAnnotationSelected(PolarisMapView mapView,
			MapCalloutView calloutView, int position, Annotation annotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnnotationDeselected(PolarisMapView mapView,
			MapCalloutView calloutView, int position, Annotation annotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnnotationClicked(PolarisMapView mapView,
			MapCalloutView calloutView, int position, Annotation annotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLongClick(PolarisMapView mapView, GeoPoint geoPoint) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.idMenuListe:
			startListView(1);
			return true;
		case R.id.idMenuSort:
			showDialog(SORT);
			return true;
		case R.id.idMenuTheme:
			startActivity(new Intent(this, InitActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void startListView(int arg) {
		if (arg == 1) {
			Animation animeMe = AnimationUtils.loadAnimation(this,
					R.anim.translate_map);
			mapView.startAnimation(animeMe);
			animeMe.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation animation) {

				}
			});
		} else {

		}
	}



	@Override
	protected Dialog onCreateDialog(int arg) {
		mSelectedItems.add(3);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Set the dialog title
		builder.setTitle(getString(R.string.title_popup))
				// Specify the list array, the items to be selected by default
				// (null for none),
				// and the listener through which to receive callbacks when
				// items are selected
				.setMultiChoiceItems(R.array.labelTheme, null,
						new DialogInterface.OnMultiChoiceClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which, boolean isChecked) {
								if (isChecked) {
									// If the user checked the item, add it to
									// the selected items
									mSelectedItems.add(which);
								} else if (mSelectedItems.contains(which)) {
									// Else, if the item is already in the
									// array, remove it
									mSelectedItems.remove(Integer
											.valueOf(which));
								}
							}
						})
				// Set the action buttons
				.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						// User clicked OK, so save the mSelectedItems results
						// somewhere
						// or return them to the component that opened the
						// dialog
						dismissDialog(0);
					}
				})
				.setNegativeButton(getString(R.string.cancel),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dismissDialog(0);
							}
						});

		return builder.create();
	}

}
