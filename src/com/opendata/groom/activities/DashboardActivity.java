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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import bma.groomservice.data.Poi;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;
import com.opendata.groom.components.SortieListAdapter;

public class DashboardActivity extends Activity implements OnClickListener {



	

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.dashboard_activity);

		findViewById(R.id.RelativeLayoutDashboardActivity1).setOnClickListener(this);
		findViewById(R.id.RelativeLayoutDashboardActivity2).setOnClickListener(this);
		findViewById(R.id.RelativeLayoutDashboardActivity3).setOnClickListener(this);

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
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.RelativeLayoutDashboardActivity1:
			 intent = new Intent(DashboardActivity.this,MainContentActivity.class);
			startActivity(intent);
			finish();
			break;
			
		case R.id.RelativeLayoutDashboardActivity2:
			 intent = new Intent(DashboardActivity.this,MainContentActivity.class);
			startActivity(intent);
			finish();
			break;
			
		case R.id.RelativeLayoutDashboardActivity3:
			 intent = new Intent(DashboardActivity.this,InitActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}

}
