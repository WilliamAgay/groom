package com.opendata.groom.activities;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.text.AbstractDocument.BranchElement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import bma.groomservice.data.dataprovence.DataprovenceManager;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;

public class InitActivity extends Activity implements View.OnClickListener , OnSeekBarChangeListener, OnLongClickListener, OnInitListener
{
	  // Speech recognition
    private static final int VOICE_RECOGNITION_REQUEST = 0x10101;

	private TextToSpeech mTextToSpeech = null;
    private boolean speechSynthReady = false;
	SeekBar seekBar=null;
	
	Button speek = null;
	Spinner spinnerSituation=null;
	int posQuestion=-1;
	List<String> questions = Arrays.asList("Votre prénom?", "Situation : ", "Votre budget?");

	Handler mHandler = new Handler();
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.init_activity);
		setTheme(android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

		
		
		
		
		
		findViewById(R.id.RelativeLayoutInitActivityContainer).setVisibility(View.GONE);
		findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.GONE);
		speek = (Button) findViewById(R.id.ButtonInitActivitySpeek);
		speek.setVisibility(View.GONE);
		if(((GroomApplication)getApplicationContext()).accountName!=null)
		{
			findViewById(R.id.RelativeLayoutInitActivityContainer).setVisibility(View.VISIBLE);
			findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.GONE);
			
			((TextView)findViewById(R.id.TextViewInitActivityHeader)).setText(getString(R.string.bienvenue_header_account,((GroomApplication)getApplicationContext()).accountName));
		}
		
		View boutonChat = findViewById(R.id.RelativeLayoutInitActivityFooterContainer);
		boutonChat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						ChatActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		 mTextToSpeech = new TextToSpeech(getApplicationContext(), this);
		if(((GroomApplication)getApplicationContext()).accountName==null)
		{
			 posQuestion=-1;
			mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					posQuestion=0;
					findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.VISIBLE);
					findViewById(R.id.TextViewQuestionLayoutQ).setVisibility(View.VISIBLE);
					findViewById(R.id.EditTextQuestionLayoutR).setVisibility(View.VISIBLE);
					findViewById(R.id.SpinnerQuestionLayoutR).setVisibility(View.GONE);
					findViewById(R.id.RelativeLayoutQuestionLayoutSeekerContainer).setVisibility(View.GONE);
					((TextView)findViewById(R.id.TextViewQuestionLayoutQ)).setText(questions.get(posQuestion));
					findViewById(R.id.ButtonQuestionLayoutGo).setOnClickListener(InitActivity.this);
					
					Animation scaleDimAnimation = AnimationUtils.loadAnimation(InitActivity.this, R.anim.translate_in);
					findViewById(R.id.FrameLayoutQuestionLayout).startAnimation(scaleDimAnimation);

				}
			}, 1000);
		}
		else
		{
			initTileCLick();
			findViewById(R.id.RelativeLayoutInitActivityContainer).setVisibility(View.VISIBLE);
			findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.GONE);
			
		}
	}

	
	 @Override
	    protected void onPause() {
	        super.onPause();
	        mTextToSpeech.shutdown();
	        mTextToSpeech = null;
	    }

	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId()) {
		
		
		case R.id.ButtonInitActivitySpeek:
//			Intent intent = new Intent(InitActivity.this,MainContentActivity.class);
//			startActivity(intent);
//			finish();
			listenToMe(getResources().getString(R.string.question_tts));
			if(true)
			{
				//TODO
			}
//			return;
//			 intent = new Intent(InitActivity.this,MainContentActivity.class);
//			startActivity(intent);
//			finish();
			break;
		case R.id.FrameLayoutInitActivityTile1:
			((GroomApplication)getApplicationContext()).themes=new ArrayList<String>();
			((GroomApplication)getApplicationContext()).themes.add(DataprovenceManager.THEME_CULTURE);
			 intent = new Intent(InitActivity.this,MainContentActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.FrameLayoutInitActivityTile2:
			((GroomApplication)getApplicationContext()).themes=new ArrayList<String>();
			((GroomApplication)getApplicationContext()).themes.add(DataprovenceManager.THEME_PLEINAIR);
			 intent = new Intent(InitActivity.this,MainContentActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.FrameLayoutInitActivityTile3:
			((GroomApplication)getApplicationContext()).themes=new ArrayList<String>();
			((GroomApplication)getApplicationContext()).themes.add(DataprovenceManager.THEME_RESTAURATION);
			 intent = new Intent(InitActivity.this,MainContentActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.FrameLayoutInitActivityTile4:
			((GroomApplication)getApplicationContext()).themes=new ArrayList<String>();
			((GroomApplication)getApplicationContext()).themes.add(DataprovenceManager.THEME_SPORT);
			 intent = new Intent(InitActivity.this,MainContentActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.FrameLayoutInitActivityTile5:
			break;
		case R.id.FrameLayoutInitActivityTile6:
			break;

		case R.id.ButtonQuestionLayoutGo:
			if(posQuestion==0)
			{
				String val = ((EditText)findViewById(R.id.EditTextQuestionLayoutR)).getText().toString();
				if(val!=null && !val.equals(""))
				{
					((GroomApplication)getApplicationContext()).accountName=val;
					posQuestion=1;
					doAnimationtranslateIn();
				}
				
			}
			if(posQuestion==1)
			{
				if(((GroomApplication)getApplicationContext()).accountStatus!=null && !((GroomApplication)getApplicationContext()).accountStatus.equals(""))
				{
					posQuestion=2;
					doAnimationtranslateIn();
				}
				
			}
			if(posQuestion==2)
			{
				if(((GroomApplication)getApplicationContext()).accountBudget!=-1)
				{
					((GroomApplication)getApplicationContext()).saveAccountDataInPref();
					((TextView)findViewById(R.id.TextViewInitActivityHeader)).setText(getString(R.string.bienvenue_header_account,((GroomApplication)getApplicationContext()).accountName));

					Animation growAnimation = AnimationUtils.loadAnimation(InitActivity.this, R.anim.grow_from_middle);
					
					findViewById(R.id.RelativeLayoutInitActivityContainer).setVisibility(View.VISIBLE);
					findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.GONE);
					findViewById(R.id.RelativeLayoutInitActivityContainer).startAnimation(growAnimation);
					initTileCLick();
				}
			}
			break;

		default:
			break;
		}
	}
	
	
	public void initTileCLick()
	{
		speek.setVisibility(View.VISIBLE);
		speek.setOnClickListener(this);
		findViewById(R.id.FrameLayoutInitActivityTile1).setOnClickListener(this);
		findViewById(R.id.FrameLayoutInitActivityTile2).setOnClickListener(this);
		findViewById(R.id.FrameLayoutInitActivityTile3).setOnClickListener(this);
		findViewById(R.id.FrameLayoutInitActivityTile4).setOnClickListener(this);
		findViewById(R.id.FrameLayoutInitActivityTile5).setOnClickListener(this);
		findViewById(R.id.FrameLayoutInitActivityTile6).setOnClickListener(this);
	}
	public void doAnimationtranslateIn()
	{
		if(posQuestion==1)
		{
			findViewById(R.id.EditTextQuestionLayoutR).setVisibility(View.GONE);
			
			 spinnerSituation = (Spinner) findViewById(R.id.SpinnerQuestionLayoutR );
			 findViewById(R.id.RelativeLayoutQuestionLayoutSeekerContainer).setVisibility(View.GONE);
				 spinnerSituation.setVisibility(View.VISIBLE);
			spinnerSituation.setOnItemSelectedListener(new CustomOnItemSelectedListener());
			((TextView)findViewById(R.id.TextViewQuestionLayoutQ)).setText(questions.get(posQuestion));
			
		}
		else if(posQuestion==2)
		{
			findViewById(R.id.EditTextQuestionLayoutR).setVisibility(View.GONE);
			findViewById(R.id.RelativeLayoutQuestionLayoutSeekerContainer).setVisibility(View.VISIBLE);
			findViewById(R.id.SpinnerQuestionLayoutR).setVisibility(View.GONE);
			
			seekBar = (SeekBar)findViewById(R.id.SeekBarQuestionLayoutR); // make seekbar object
			seekBar.setOnSeekBarChangeListener(InitActivity.this); // set seekbar listener.
			((TextView)findViewById(R.id.TextViewQuestionLayoutQ)).setText(questions.get(posQuestion));
			
		}
		
		Animation translateInAfterAnimation = AnimationUtils.loadAnimation(InitActivity.this, R.anim.translate_in_after);
		translateInAfterAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {

			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {

			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Animation translateInAnimation = AnimationUtils.loadAnimation(InitActivity.this, R.anim.translate_in);
				findViewById(R.id.FrameLayoutQuestionLayout).startAnimation(translateInAnimation);
				
//				
//				findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.VISIBLE);
//				((TextView)findViewById(R.id.TextViewQuestionLayoutQ)).setText(questions.get(posQuestion));
//				findViewById(R.id.ButtonQuestionLayoutGo).setOnClickListener(InitActivity.this);
//				
//				Animation scaleDimAnimation = AnimationUtils.loadAnimation(InitActivity.this, R.anim.translate_in);
//				findViewById(R.id.FrameLayoutQuestionLayout).startAnimation(scaleDimAnimation);
//				hasDone=true;
			}
		});
		findViewById(R.id.FrameLayoutQuestionLayout).startAnimation(translateInAfterAnimation);
		
	}

	
	public class CustomOnItemSelectedListener implements OnItemSelectedListener {
		 
		  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
//			Toast.makeText(parent.getContext(), 
//				"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
//				Toast.LENGTH_SHORT).show();
			  ((GroomApplication)getApplicationContext()).accountStatus=parent.getItemAtPosition(pos).toString();
		  }
		 
		  @Override
		  public void onNothingSelected(AdapterView<?> arg0) {
		  }
		 
		}


	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		((GroomApplication)getApplicationContext()).accountBudget = progress;

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            speechSynthReady = true;
        }
    }
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == VOICE_RECOGNITION_REQUEST && resultCode == RESULT_OK) 
	        {
	            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
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
//            TextView textView = (TextView) findViewById(R.id.speech_io_text);
//            String textToSpeak = textView.getText().toString();
        	
            mTextToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					speakToMe();
				}
			}, 3000);
           
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

}
