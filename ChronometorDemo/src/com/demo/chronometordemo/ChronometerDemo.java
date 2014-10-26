package com.demo.chronometordemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;

public class ChronometerDemo extends Activity {
	
	private Chronometer chronometer = null;
	private Button startBtn = null;
	private Button stopBtn = null;
	private Button resetBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		chronometer = (Chronometer) this.findViewById(R.id.chronometer);
		startBtn = (Button)this.findViewById(R.id.startBtn); 
		stopBtn = (Button)this.findViewById(R.id.stopBtn); 
		resetBtn = (Button)this.findViewById(R.id.resetBtn); 
		
		startBtn.setOnClickListener(new OnStartButtonClickListenersImpl());
		stopBtn.setOnClickListener(new OnStopButtonClickListenersImpl());
		resetBtn.setOnClickListener(new OnResetButtonClickListenersImpl());
	}
	
	
	private class OnStartButtonClickListenersImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			ChronometerDemo.this.chronometer.start();
			
			ChronometerDemo.this.startBtn.setEnabled(false);
			ChronometerDemo.this.stopBtn.setEnabled(true);
		}
	}
	
	private class OnStopButtonClickListenersImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			ChronometerDemo.this.chronometer.stop();
			
			ChronometerDemo.this.stopBtn.setEnabled(false);
			ChronometerDemo.this.startBtn.setEnabled(true);
			ChronometerDemo.this.resetBtn.setEnabled(true);
		}
	}
	
	private class OnResetButtonClickListenersImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			ChronometerDemo.this.chronometer.setBase(SystemClock.elapsedRealtime());
			
			ChronometerDemo.this.resetBtn.setEnabled(false);
			ChronometerDemo.this.stopBtn.setEnabled(false);
			ChronometerDemo.this.startBtn.setEnabled(true);
		}
	}

}
