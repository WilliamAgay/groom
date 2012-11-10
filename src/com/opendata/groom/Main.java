package com.opendata.groom;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {
	private Button boutonChat;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonChat = (Button) findViewById(R.id.boutonChat);
        boutonChat.setOnClickListener(new View.OnClickListener(){
        	
        @Override
        public void onClick(View v) {
    	Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
        startActivity(intent)
;        }
    });
    
    

  
    }
}
