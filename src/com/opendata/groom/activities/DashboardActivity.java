package com.opendata.groom.activities;

import java.util.ArrayList;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import bma.groomservice.data.dataprovence.DataprovenceManager;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;

public class DashboardActivity extends Activity implements OnClickListener,OnInitListener {

	
	  // Speech recognition
    private static final int VOICE_RECOGNITION_REQUEST = 0x10101;

	private TextToSpeech mTextToSpeech = null;
	private boolean speechSynthReady = false;
    private boolean needQuitAfterSpeach = false;
    
    String valueForQuitAfterSpeach = null;
	
    ImageView speek = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{

		super.onCreate(savedInstanceState);

		setContentView(R.layout.dashboard_activity);
		
		if(((GroomApplication)getApplicationContext()).accountName==null)
		{
			Intent intent = new Intent(DashboardActivity.this,BonjourActivity.class);
			startActivity(intent);

		}
		speek = (ImageView) findViewById(R.id.ImageViewDashboardActivitySpeek);
		speek.setVisibility(View.GONE);
		

		findViewById(R.id.RelativeLayoutDashboardActivity1).setOnClickListener(
				this);
		findViewById(R.id.RelativeLayoutDashboardActivity2).setOnClickListener(
				this);
		findViewById(R.id.RelativeLayoutDashboardActivity3).setOnClickListener(
				this);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

	}

	
	
	 public void onInit(int status) {
	        if (status == TextToSpeech.SUCCESS) {
	            speechSynthReady = true;
	            speek.setVisibility(View.VISIBLE);
	            speek.setOnClickListener(this);
	            if(valueForQuitAfterSpeach!=null)
	            {
	            	listenToMe(valueForQuitAfterSpeach);
	            }
	        }
	    }
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		
		case R.id.ImageViewDashboardActivitySpeek:

			listenToMe(getResources().getString(R.string.question_tts));

			break;
		case R.id.RelativeLayoutDashboardActivity1:
			intent = new Intent(DashboardActivity.this,
					MainContentActivity.class);
			startActivity(intent);
			finish();
			break;

		case R.id.RelativeLayoutDashboardActivity2:
			intent = new Intent(DashboardActivity.this,
					MainContentActivity.class);
			startActivity(intent);
			finish();
			break;

		case R.id.RelativeLayoutDashboardActivity3:
			intent = new Intent(DashboardActivity.this, InitActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		 mTextToSpeech = new TextToSpeech(getApplicationContext(), this);
		
	}
	
	 @Override
	    protected void onPause() {
	        super.onPause();
	        mTextToSpeech.shutdown();
	        mTextToSpeech = null;
	    }

	 
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == VOICE_RECOGNITION_REQUEST && resultCode == RESULT_OK) 
	        {
	            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	            if(matches.size()==0)
	            {
	            	listenToMe(getResources().getString(R.string.desole_je_nai_pas_compris));
	            }
	            else
	            {
	    			((GroomApplication)getApplicationContext()).themes=new ArrayList<String>();

	            	for(String m : matches)
	            	{
	            		if(((GroomApplication)getApplicationContext()).theme_pleinAirTTS.indexOf(m)!=-1)
	            		{
	            			((GroomApplication)getApplicationContext()).themes.add(DataprovenceManager.THEME_PLEINAIR);
	            		}
	            		if(((GroomApplication)getApplicationContext()).theme_cultureTTS.indexOf(m)!=-1)
	            		{
	            			((GroomApplication)getApplicationContext()).themes.add(DataprovenceManager.THEME_CULTURE);

	            		}
	            		if(((GroomApplication)getApplicationContext()).theme_restaurationTTS.indexOf(m)!=-1)
	            		{
	            			((GroomApplication)getApplicationContext()).themes.add(DataprovenceManager.THEME_RESTAURATION);

	            		}
	            		if(((GroomApplication)getApplicationContext()).theme_sportTTS.indexOf(m)!=-1)
	            		{
	            			((GroomApplication)getApplicationContext()).themes.add(DataprovenceManager.THEME_SPORT);

	            		}
	            	}
	            	
	            	if(((GroomApplication)getApplicationContext()).themes.size()==0)
	            	{
	            		needQuitAfterSpeach=false;
	            		valueForQuitAfterSpeach=getResources().getString(R.string.desole_je_nai_pas_compris);
	            	}
	            	else
	            	{
	            		
	            		valueForQuitAfterSpeach= "";
	            		for(String m : ((GroomApplication)getApplicationContext()).themes)
		            	{
	            			valueForQuitAfterSpeach += m+", ";
		            	}
	            		
	            		needQuitAfterSpeach=true;
	            	}
	            	mTextToSpeech = new TextToSpeech(getApplicationContext(), this);
	            	
	            	
	            }
//	            TextView textView = (TextView) findViewById(R.id.speech_io_text);
//	            String firstMatch = matches.get(0);
//	            textView.setText(firstMatch);
	            
	            
	            
	        }
	    }

	
	public void listenToMe( String textToSpeak) {
     if (!speechSynthReady) {
         Toast.makeText(getApplicationContext(),
                 "Speech Synthesis not ready.", Toast.LENGTH_SHORT).show();
         return;
     }
     if(mTextToSpeech==null)
     {
     	mTextToSpeech = new TextToSpeech(getApplicationContext(), this);
     	return;
     }
     int result = mTextToSpeech.setLanguage(Locale.FRANCE);
     if (result == TextToSpeech.LANG_MISSING_DATA
             || result == TextToSpeech.LANG_NOT_SUPPORTED) {
         Toast.makeText(
                 getApplicationContext(),
                 "Language not available. Check code or config in settings.",
                 Toast.LENGTH_SHORT).show();
     } else {
//         TextView textView = (TextView) findViewById(R.id.speech_io_text);
//         String textToSpeak = textView.getText().toString();
     	
         mTextToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
         Handler h = new Handler();
         h.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					speakToMe();
				}
			}, 3000);
        
     }
     if(needQuitAfterSpeach)
     {
    	 Handler mHandler = new Handler();
     	mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					 Intent intent = new Intent(DashboardActivity.this,MainContentActivity.class);
						startActivity(intent);
						finish();
				}
			}, 10000);
     }
     
 }
	  public void speakToMe() {
	        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
	                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
	        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
	                "Please speak slowly and enunciate clearly.");
	        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST);
	    }
	  
	  @Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			case R.id.idMenuTts:
				listenToMe(getResources().getString(R.string.question_tts));
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
		}
	  
	  @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.menu_init, menu);
			return true;
		}

}
