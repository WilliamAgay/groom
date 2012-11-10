package com.opendata.groom.activities;


import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.opendata.groom.GroomApplication;
import com.opendata.groom.R;

public class InitActivity extends Activity implements View.OnClickListener , OnSeekBarChangeListener, OnLongClickListener
{
	
	SeekBar seekBar=null;
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
		if(((GroomApplication)getApplicationContext()).accountName!=null)
		{
			findViewById(R.id.RelativeLayoutInitActivityContainer).setVisibility(View.VISIBLE);
			findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.GONE);
			
			((TextView)findViewById(R.id.TextViewInitActivityHeader)).setText(getString(R.string.bienvenue_header_account,((GroomApplication)getApplicationContext()).accountName));
		}
		
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
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
			findViewById(R.id.RelativeLayoutInitActivityContainer).setVisibility(View.VISIBLE);
			findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.GONE);
			
		}
	}

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
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
				}
			}
			break;

		default:
			break;
		}
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
}
