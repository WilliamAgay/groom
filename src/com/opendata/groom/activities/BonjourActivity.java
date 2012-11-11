package com.opendata.groom.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;

public class BonjourActivity extends Activity {

	EditText nomEDT = null;
	EditText dureeEDT = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bonjour);
		nomEDT = (EditText) findViewById(R.id.EditTextActivityBonjourNom);
		dureeEDT = (EditText) findViewById(R.id.EditTextActivityBonjourDureeSejour);
		Typeface androgyne = Typeface.createFromAsset(getAssets(),
				"Androgyne_TB.otf");
		((TextView) findViewById(R.id.bonjour_question1))
				.setTypeface(androgyne);
		((TextView) findViewById(R.id.bonjour_question2))
				.setTypeface(androgyne);
		findViewById(R.id.TextViewActivityBonjourFooter).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						if (nomEDT.getText() != null
								&& dureeEDT.getText() != null) {
							((GroomApplication) BonjourActivity.this
									.getApplicationContext()).accountName = nomEDT
									.getText().toString();
							((GroomApplication) BonjourActivity.this
									.getApplicationContext()).accountNbJour = Integer
									.parseInt(dureeEDT.getText().toString());

							Intent intent = new Intent(BonjourActivity.this,
									InitActivity.class);
							startActivity(intent);
							finish();
						} else {
							AlertDialog.Builder builder = new AlertDialog.Builder(
									BonjourActivity.this);
							builder.setTitle("Attention!");
							builder.setMessage("Veuillez compléter les champs SVP!");
							builder.setIcon(R.drawable.ic_launcher);
							builder.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									});
							builder.show();
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_bonjour, menu);
		return true;
	}
}
