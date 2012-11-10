package com.opendata.groom.activities;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.R.bool;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.opendata.groom.R;

public class InitActivity extends Activity implements View.OnClickListener
{

	int posQuestion=-1;
	List<String> questions = Arrays.asList("Votre prénom?", "Situation : ", "Votre budget?");

	Handler mHandler = new Handler();
	
	boolean hasDone = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.init_activity);
		findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.GONE);
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(!hasDone)
		{
			mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					posQuestion=0;
					findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.TextViewQuestionLayoutQ)).setText(questions.get(posQuestion));
					findViewById(R.id.ButtonQuestionLayoutGo).setOnClickListener(InitActivity.this);
					
					Animation scaleDimAnimation = AnimationUtils.loadAnimation(InitActivity.this, R.anim.translate_in);
					findViewById(R.id.FrameLayoutQuestionLayout).startAnimation(scaleDimAnimation);
					hasDone=true;
				}
			}, 1000);
		}
	}

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ButtonQuestionLayoutGo:
			if(posQuestion==0)
			{
				String val = ((EditText)findViewById(R.id.EditTextQuestionLayoutR)).getText().toString();
				if(val==null || val.equals(""))
				{
					mHandler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							posQuestion=1;
							findViewById(R.id.FrameLayoutQuestionLayout).setVisibility(View.VISIBLE);
							((TextView)findViewById(R.id.TextViewQuestionLayoutQ)).setText(questions.get(posQuestion));
							findViewById(R.id.ButtonQuestionLayoutGo).setOnClickListener(InitActivity.this);
							
							Animation scaleDimAnimation = AnimationUtils.loadAnimation(InitActivity.this, R.anim.translate_in);
							findViewById(R.id.FrameLayoutQuestionLayout).startAnimation(scaleDimAnimation);
							hasDone=true;
						}
					}, 1000);
				}
				
			}
			break;

		default:
			break;
		}
	}
	
	

}
