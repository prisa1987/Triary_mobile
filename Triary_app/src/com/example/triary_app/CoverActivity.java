package com.example.triary_app;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CoverActivity extends Activity{
	
	private static final long DELAY = 3000;
    private boolean scheduled = false;
    private Timer splashTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover);
		
		
		getActionBar().hide();
		
		splashTimer = new Timer();
        splashTimer.schedule(new TimerTask()
        {
            public void run()
            {
            	CoverActivity.this.finish();
                startActivity(new Intent(CoverActivity.this, MainActivity.class));
            }
         }, DELAY);
       scheduled = true;
		
	}
	
}
