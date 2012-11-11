package com.opendata.groom.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import bma.groomservice.data.Poi;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;
import com.opendata.groom.components.SortieListAdapter;

public class MainContentListActivity extends Activity {

	private static final int SORT = 0;
	ListView lvEv = null;

	/** Affiche le détail d'un POI lorsqu'on clique dessus dans la liste */
	class DetailClickListener implements OnItemClickListener {

		List<Poi> pois;

		public DetailClickListener(List<Poi> pois) {
			this.pois = pois;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent tnt = new Intent(getApplicationContext(),
					PoiDetailsActivity.class);
			tnt.putExtra(PoiDetailsActivity.EXTRA_POI, pois.get(position));
			startActivity(tnt);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.map_list_layout);

		lvEv = (ListView) findViewById(R.id.ListViewmapListlayout);
		List<Poi> pois = new ArrayList<Poi>(
				((GroomApplication) getApplication()).pois);
		lvEv.setAdapter(new SortieListAdapter(MainContentListActivity.this,
				pois));
		lvEv.setOnItemClickListener(new DetailClickListener(pois));

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

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
			Intent intent = new Intent(this, InitActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.idMenuListe:
			startMapView(R.id.idMenuListe);
			return true;
		case R.id.idMenuSort:
			showDialog(SORT);
			return true;
		case R.id.idMenuTheme:
			startActivity(new Intent(MainContentListActivity.this,
					InitActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void startMapView(int idToShow) {
		if (idToShow == R.id.idMenuListe) {
			Intent intent = new Intent(MainContentListActivity.this,
					MainContentActivity.class);
			startActivity(intent);
			finish();
			MainContentListActivity.this.overridePendingTransition(
					R.anim.translate_map, R.anim.translate_map);

		}
	}

	ArrayList mSelectedItems = new ArrayList(); // Where we track the selected
												// items

	@Override
	protected Dialog onCreateDialog(int arg) {
		mSelectedItems = new ArrayList(); // Where we track the selected items
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Set the dialog title
		builder.setTitle("Thèmes des données")
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
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						// User clicked OK, so save the mSelectedItems results
						// somewhere
						// or return them to the component that opened the
						// dialog
						dismissDialog(0);
					}
				})
				.setNegativeButton("Annuler",
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
		Intent intent = new Intent(MainContentListActivity.this,
				MainContentActivity.class);
		startActivity(intent);
		finish();
	}

}
