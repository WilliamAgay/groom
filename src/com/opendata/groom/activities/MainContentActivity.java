package com.opendata.groom.activities;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import bma.groomservice.data.Poi;
import bma.groomservice.data.PoiListener;
import bma.groomservice.data.dataprovence.DataprovenceManager;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;
import com.opendata.groom.polaris.Annotation;
import com.opendata.groom.polaris.MapCalloutView;
import com.opendata.groom.polaris.MapViewUtils;
import com.opendata.groom.polaris.PolarisMapView;
import com.opendata.groom.polaris.PolarisMapView.OnAnnotationSelectionChangedListener;
import com.opendata.groom.polaris.PolarisMapView.OnMapViewLongClickListener;

public class MainContentActivity extends MapActivity implements
		OnMapViewLongClickListener, OnAnnotationSelectionChangedListener,
		PoiListener {

	Logger logger = LoggerFactory.getLogger(MainContentActivity.class);

	private static final int SORT = 0;
	private PolarisMapView mapView;

	private final ArrayList mSelectedItems = new ArrayList(); // Where we track
																// the selected
	private final String currentTheme = "";
	private final List<Poi> currentPoiList = new ArrayList<Poi>();

	// items

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.polarismaplayout);
		mapView = (PolarisMapView) findViewById(R.id.PolarisMapViewLayoutMap);

		// lvEv = new ListView(MainContentActivity.this);
		// RelativeLayout.LayoutParams lp =new
		// RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT);
		//
		//
		// lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
		// lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 1);
		// lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
		// lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
		// lvEv.setVisibility(View.GONE);
		// lvEv.setLayoutParams(lp);
		// rlContainer.addView(lvEv);

		mapView.setUserTrackingButtonEnabled(true);
		mapView.setOnMapViewLongClickListener(this);
		mapView.setOnAnnotationSelectionChangedListener(this);
		// 43.296191,5.379792
		// mapView.getController().setCenter(new GeoPoint((int)(43.296191 *
		// 1E6), (int)(5.379792 * 1E6)));
		mapView.getController().setZoom(16);
		mapView.preLoad();

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);

		// public static final String THEME_PLEINAIR = "PLEIN AIR";
		// public static final String THEME_RESTAURATION = "RESTAURATION";
		// public static final String THEME_SPORT = "SPORT";
		// public static final String THEME_CULTURE = "CULTURE";
		((GroomApplication) getApplication()).fillThemes();
		logger.debug("themes={}",
				((GroomApplication) getApplicationContext()).themes);
		new DataprovenceManager(this, false)
				.findAll(((GroomApplication) getApplicationContext()).themes);
	}

	@Override
	public void onPoiReceived(List<Poi> pois) {
		if (pois != null && pois.size() > 0 /* && currentPoiList.size() <= 320 */) {
			currentPoiList.addAll(pois);
			addAnnotationList(createAnnotationsOverlay(currentPoiList));
			GroomApplication app = (GroomApplication) getApplication();
			app.pois.addAll(pois);
		}
	}

	private void addAnnotationList(List<Annotation> aAnnotationsList) {
		if (((GroomApplication) getApplicationContext()).prefPleinAirSelected)
			mapView.setAnnotations(aAnnotationsList, R.drawable.pleinair);
		if (((GroomApplication) getApplicationContext()).prefCultureSelected)
			mapView.setAnnotations(aAnnotationsList, R.drawable.culture);
		if (((GroomApplication) getApplicationContext()).prefRestoSelected)
			mapView.setAnnotations(aAnnotationsList, R.drawable.gastro);
		if (((GroomApplication) getApplicationContext()).prefSportSelected)
			mapView.setAnnotations(aAnnotationsList, R.drawable.sport);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mapView.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mapView.onStop();
	}

	public List<Annotation> createAnnotationsOverlay(List<Poi> aPoiSet) {
		List<Annotation> poiAnnotationList = new ArrayList<Annotation>();
		if (aPoiSet != null) {
			for (Poi poi : aPoiSet) {
				// Drawable marker = null;
				if (DataprovenceManager.THEME_CULTURE
						.equalsIgnoreCase(poi.theme)) {
					poiAnnotationList.add(new Annotation(new GeoPoint(
							(int) (poi.latitude * 1e6),
							(int) (poi.longitude * 1e6)), poi.raisonsociale,
							poi.adresseweb != null ? poi.adresseweb : "",
							MapViewUtils.boundMarkerCenterBottom(getResources()
									.getDrawable(R.drawable.culture))));
				} else if (DataprovenceManager.THEME_PLEINAIR
						.equalsIgnoreCase(poi.theme)) {
					poiAnnotationList.add(new Annotation(new GeoPoint(
							(int) (poi.latitude * 1e6),
							(int) (poi.longitude * 1e6)), poi.raisonsociale,
							poi.adresseweb != null ? poi.adresseweb : "",
							MapViewUtils.boundMarkerCenterBottom(getResources()
									.getDrawable(R.drawable.pleinair))));
				} else if (DataprovenceManager.THEME_RESTAURATION
						.equalsIgnoreCase(poi.theme)) {
					poiAnnotationList.add(new Annotation(new GeoPoint(
							(int) (poi.latitude * 1e6),
							(int) (poi.longitude * 1e6)), poi.raisonsociale,
							poi.adresseweb != null ? poi.adresseweb : "",
							MapViewUtils.boundMarkerCenterBottom(getResources()
									.getDrawable(R.drawable.gastro))));
				} else if (DataprovenceManager.THEME_SPORT
						.equalsIgnoreCase(poi.theme)) {
					poiAnnotationList.add(new Annotation(new GeoPoint(
							(int) (poi.latitude * 1e6),
							(int) (poi.longitude * 1e6)), poi.raisonsociale,
							poi.adresseweb != null ? poi.adresseweb : "",
							MapViewUtils.boundMarkerCenterBottom(getResources()
									.getDrawable(R.drawable.sport))));
				}
				// poiAnnotationList.add(new Annotation(
				// new GeoPoint((int) (poi.latitude * 1e6),
				// (int) (poi.longitude * 1e6)),
				// poi.raisonsociale,
				// poi.adresseweb != null ? poi.adresseweb : ""));
			}
		}
		return poiAnnotationList;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onAnnotationSelected(PolarisMapView mapView,
			MapCalloutView calloutView, int position, Annotation annotation) {

		calloutView.setDisclosureEnabled(true);
		calloutView.setClickable(true);
		// if (!TextUtils.isEmpty(annotation.getSnippet())) {
		// calloutView.setLeftAccessoryView(getLayoutInflater().inflate(R.layout.accessory,
		// calloutView, false));
		// } else {
		// calloutView.setLeftAccessoryView(null);
		// }
	}

	@Override
	public void onAnnotationDeselected(PolarisMapView mapView,
			MapCalloutView calloutView, int position, Annotation annotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnnotationClicked(PolarisMapView mapView,
			MapCalloutView calloutView, int position, Annotation annotation) {
		Intent intent = new Intent(MainContentActivity.this,
				PoiDetailsActivity.class);
		if (currentPoiList != null && currentPoiList.get(position) != null)
			intent.putExtra("poi", currentPoiList.get(position));
		startActivity(intent);
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
			Intent intent = new Intent(this, DashboardActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.idMenuListe:
			startListView(R.id.idMenuListe);
			return true;
			// case R.id.idMenuSort:
			// showDialog(SORT);
			// return true;
		case R.id.idMenuTheme:
			startActivity(new Intent(this, InitActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void startListView(int idToShow) {
		if (idToShow == R.id.idMenuListe) {

			Intent intent = new Intent(MainContentActivity.this,
					MainContentListActivity.class);
			startActivity(intent);
			finish();
			MainContentActivity.this.overridePendingTransition(
					R.anim.translate_map, R.anim.translate_map);

			// lvEv.setVisibility(View.VISIBLE);
			// Animation animation =
			// AnimationUtils.loadAnimation(MainContentActivity.this,
			// R.anim.translate_map);
			// animation.setAnimationListener(new AnimationListener() {
			//
			// @Override
			// public void onAnimationStart(Animation animation) {
			//
			//
			//
			// }
			//
			// @Override
			// public void onAnimationRepeat(Animation animation) {
			// // TODO Auto-generated method stub
			//
			// }
			//
			// @Override
			// public void onAnimationEnd(Animation animation) {
			// RelativeLayout.LayoutParams lp =new
			// RelativeLayout.LayoutParams(getWindow().getWindowManager().getDefaultDisplay().getWidth(),
			// getWindow().getWindowManager().getDefaultDisplay().getHeight());
			// lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
			// lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 1);
			// lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
			// lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
			// lvEv.setLayoutParams(lp);
			// animation.cancel();
			// }
			// });
			// rlContainer.startAnimation(animation);
			// int cx = list.getWidth() / 2;
			// int cy = 0;
			// Animation animation = new Flip3dAnimation(mapView, list, cx, cy,
			// true);
			// animation.setAnimationListener(new AnimationListener() {
			// @Override
			// public void onAnimationEnd(Animation animation) {
			// mapView.setVisibility(View.GONE);
			// findViewById(R.id.ListViewPolarisMapLayoutListe).setVisibility(View.VISIBLE);
			// }
			// @Override
			// public void onAnimationRepeat(Animation animation) {
			// }
			// @Override
			// public void onAnimationStart(Animation animation) {
			//
			// }
			// });
			// mapView.startAnimation(animation);

		} else {

		}
	}

	@Override
	protected Dialog onCreateDialog(int arg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Set the dialog title

		boolean[] checkedVal = { false, false, false, false };
		if (((GroomApplication) getApplicationContext()).prefPleinAirSelected)
			checkedVal[1] = true;
		if (((GroomApplication) getApplicationContext()).prefCultureSelected)
			checkedVal[0] = true;
		if (((GroomApplication) getApplicationContext()).prefRestoSelected)
			checkedVal[3] = true;
		if (((GroomApplication) getApplicationContext()).prefSportSelected)
			checkedVal[2] = true;

		builder.setTitle(getString(R.string.title_popup))

				// Specify the list array, the items to be selected by default
				// (null for none),
				// and the listener through which to receive callbacks when
				// items are selected
				.setMultiChoiceItems(R.array.labelTheme, checkedVal,
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
				.setPositiveButton(getString(R.string.ok),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								// User clicked OK, so save the mSelectedItems
								// results
								// somewhere
								// or return them to the component that opened
								// the
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

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(MainContentActivity.this,
				DashboardActivity.class);
		startActivity(intent);
		finish();
	}

}
