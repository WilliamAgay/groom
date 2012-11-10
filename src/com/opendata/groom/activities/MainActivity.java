package com.opendata.groom.activities;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;
import com.opendata.groom.R.id;
import com.opendata.groom.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button boutonChat;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		if( ((GroomApplication)getApplicationContext()).accountName==null)
		{
			Intent intent = new Intent(MainActivity.this,InitActivity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(0, 0);
		}
		setContentView(R.layout.activity_main);
		boutonChat = (Button) findViewById(R.id.boutonChat);
		boutonChat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						ChatActivity.class);
				startActivity(intent);
			}
		});

	}

	public void OnClickButton(View v) {
		switch (v.getId()) {
		case R.id.button1:
			startActivity(new Intent(getApplicationContext(),
					InitActivity.class));
			break;
		case R.id.button2:
			startActivity(new Intent(getApplicationContext(), TabActivity.class));
			break;

		default:
			break;
		}
	}
}
