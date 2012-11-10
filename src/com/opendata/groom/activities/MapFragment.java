package com.opendata.groom.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.maps.GeoPoint;
import com.opendata.groom.polaris.Annotation;
import com.opendata.groom.polaris.MapCalloutView;
import com.opendata.groom.polaris.PolarisMapView;
import com.opendata.groom.polaris.PolarisMapView.OnAnnotationSelectionChangedListener;
import com.opendata.groom.polaris.PolarisMapView.OnMapViewLongClickListener;

public class MapFragment extends Fragment implements
		OnMapViewLongClickListener, OnAnnotationSelectionChangedListener {
	private PolarisMapView mapView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return (new FrameLayout(getActivity()));
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mapView = new PolarisMapView(getActivity(),
				"05BiH1uRq25AnV6KHAZ8ZR9TLQABe7ZqQ_nIR9A");
		mapView.setUserTrackingButtonEnabled(true);
		mapView.setOnMapViewLongClickListener(this);
		mapView.setOnAnnotationSelectionChangedListener(this);

		mapView.getController().setZoom(16);
		mapView.preLoad();

		((ViewGroup) getView()).addView(mapView);
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
}
