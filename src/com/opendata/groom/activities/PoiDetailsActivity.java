package com.opendata.groom.activities;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import bma.groomservice.data.Poi;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;

public class PoiDetailsActivity extends Activity {

	private static final Logger logger = LoggerFactory
			.getLogger(PoiDetailsActivity.class);

	public static final String EXTRA_ID = "id";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poi_details_activity);

		Intent tnt = getIntent();
		if (!tnt.hasExtra(EXTRA_ID)) {
			logger.error("Il manque l'id du POI !");
			finish();
		}
		int id = tnt.getIntExtra(EXTRA_ID, -1);
		List<Poi> pois = ((GroomApplication) getApplication()).pois;
		if (pois.size() < id) {
			logger.error("Aucun POI ne correspond à l'id {}", id);
			finish();
		}
		Poi poi = pois.get(id);
		logger.debug("Poi={}", poi);
	}

}