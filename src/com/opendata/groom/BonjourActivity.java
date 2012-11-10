package com.opendata.groom;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BonjourActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonjour);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bonjour, menu);
        return true;
    }
}
