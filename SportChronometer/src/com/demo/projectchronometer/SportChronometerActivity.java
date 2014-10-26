package com.demo.projectchronometer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SportChronometerActivity extends Activity {

//	private EditText sportSeconds = null;
//	private EditText restSeconds = null;
	private NumberPicker sportSeconds = null;
	private NumberPicker restSeconds = null;
	private Chronometer sportChronometer = null;
	private Chronometer restChronometer = null;
	private Chronometer totalTimeChronometer = null;
	private TextView totalTimeMessage = null;
	private Button startBtn = null;
	private Button endBtn = null;
	private int runTimes = 1;
	private int backPressCount = 0;
	private Toast toolTip = null;

	// media player
	private MediaPlayer titaAudio = null;
	private MediaPlayer dingAudio = null;
	private MediaPlayer readyAudio = null;
	
	private Handler repeatUpdateHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		// find view
		this.findView();
		
		// update the total time cost message.
		repeatUpdateHandler.post(new UpdateMessage(0));

		startBtn.setOnClickListener(new OnStartClickListenerImpl());
		endBtn.setOnClickListener(new OnEndClickListenerImpl());

		sportChronometer.setOnChronometerTickListener(new OnSportChronometerTickListenerImpl());
		restChronometer.setOnChronometerTickListener(new OnRestChronometerTickListenerImpl());
	}

	private void findView() {
		sportSeconds = (NumberPicker) this.findViewById(R.id.sportSeconds);
		restSeconds = (NumberPicker) this.findViewById(R.id.restSeconds);
		sportChronometer = (Chronometer) this.findViewById(R.id.sportChronometer);
		restChronometer = (Chronometer) this.findViewById(R.id.restChronometer);
		totalTimeChronometer = (Chronometer) this.findViewById(R.id.totalTimeChronometer);
		totalTimeMessage = (TextView)this.findViewById(R.id.totalTimeMessage);
		startBtn = (Button) this.findViewById(R.id.startBtn);
		endBtn = (Button) this.findViewById(R.id.endBtn);
		
		// finished app tooltip
		this.toolTip = Toast.makeText(this, R.string.finish_app_message, Toast.LENGTH_SHORT);

		// create audio
		this.titaAudio = MediaPlayer.create(this, R.raw.tita);
		this.dingAudio = MediaPlayer.create(this, R.raw.ding);
		this.readyAudio = MediaPlayer.create(this, R.raw.ready_go);
		
		//--- set focus to start button-------------------------
		startBtn.setFocusable(true);
		startBtn.setFocusableInTouchMode(true);///add this line
		startBtn.requestFocus();
		//!-----------------------------------------------------
	}
	
	public class UpdateMessage implements Runnable{
		private Integer totalTimes = null;
		public UpdateMessage(){}
		public UpdateMessage(Integer totalTimes){
			this.totalTimes = totalTimes;
		}
		@Override
		public void run() {
			this.totalTimes = this.totalTimes != null ? this.totalTimes : runTimes;
			String message = getString(R.string.total_time_message).replace("{0}", this.totalTimes+"");
			totalTimeMessage.setText(message);
		}
		
	}

	public class OnStartClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			// reset the @backPressCount
			SportChronometerActivity.this.backPressCount = 0;
			SportChronometerActivity.this.sportChronometer.setBase(SystemClock.elapsedRealtime());
			SportChronometerActivity.this.restChronometer.setBase(SystemClock.elapsedRealtime());
			SportChronometerActivity.this.totalTimeChronometer.setBase(SystemClock.elapsedRealtime());
			SportChronometerActivity.this.sportChronometer.start();
			// start the total time chronometer
			SportChronometerActivity.this.totalTimeChronometer.start();
			// play the tita audio
			SportChronometerActivity.this.titaAudio.start();

			// disable the start button
			SportChronometerActivity.this.startBtn.setEnabled(false);
			SportChronometerActivity.this.endBtn.setEnabled(true);
			
			// update the total time cost message.
			repeatUpdateHandler.post(new UpdateMessage());
		}

	}

	public class OnEndClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			runTimes = 1;
			// update the total time cost message.
			repeatUpdateHandler.post(new UpdateMessage(0));
			
			SportChronometerActivity.this.sportChronometer.stop();
			SportChronometerActivity.this.restChronometer.stop();
			SportChronometerActivity.this.totalTimeChronometer.stop();
			SportChronometerActivity.this.sportChronometer.setBase(SystemClock.elapsedRealtime());
			SportChronometerActivity.this.restChronometer.setBase(SystemClock.elapsedRealtime());
			SportChronometerActivity.this.totalTimeChronometer.setBase(SystemClock.elapsedRealtime());
			SportChronometerActivity.this.sportChronometer.stop();
			SportChronometerActivity.this.restChronometer.stop();
			SportChronometerActivity.this.totalTimeChronometer.stop();

			// stop the media player
			SportChronometerActivity.this.titaAudio.pause();
			SportChronometerActivity.this.dingAudio.pause();
			SportChronometerActivity.this.readyAudio.pause();

			// disable the start button
			SportChronometerActivity.this.startBtn.setEnabled(true);
			SportChronometerActivity.this.endBtn.setEnabled(false);
			
		}
	}

	public class OnSportChronometerTickListenerImpl implements OnChronometerTickListener {
		@Override
		public void onChronometerTick(Chronometer chronometer) {
			String chronometerValue = chronometer.getText().toString();
			String sportTime = SportChronometerActivity.this.parseSecondToMMSS(
					SportChronometerActivity.this.sportSeconds.getValue() + "", false);

			if (chronometerValue.equals(sportTime)) {
				// add end (ding) audio
				SportChronometerActivity.this.titaAudio.pause();
				SportChronometerActivity.this.dingAudio.start();

				// stop the sport chronometer and clear the sport chronometer value
				SportChronometerActivity.this.sportChronometer.stop();

				// increase the runTimes
				runTimes++;
				
				// update the total time cost message.
				repeatUpdateHandler.post(new UpdateMessage());

				SportChronometerActivity.this.restChronometer.setBase(SystemClock.elapsedRealtime());
				SportChronometerActivity.this.restChronometer.setOnChronometerTickListener(new OnRestChronometerTickListenerImpl());
				// start the rest chronometer
				SportChronometerActivity.this.restChronometer.start();
				SportChronometerActivity.this.sportChronometer.stop();
				// set the sport chronometer tick listener to null
				SportChronometerActivity.this.sportChronometer.setOnChronometerTickListener(null);
			}
		}
	}

	public class OnRestChronometerTickListenerImpl implements OnChronometerTickListener {

		@Override
		public void onChronometerTick(Chronometer chronometer) {
			String chronometerValue = chronometer.getText().toString();
			String restTime = SportChronometerActivity.this.parseSecondToMMSS(
					SportChronometerActivity.this.restSeconds.getValue()+"", false);

			String readyGoTime = SportChronometerActivity.this
					.parseSecondToMMSS(
							(Integer.parseInt(SportChronometerActivity.this.restSeconds
									.getValue() + "") - 4)
									+ "", false);

			// ready go audio
			if (chronometerValue.equals(readyGoTime)) {
				SportChronometerActivity.this.readyAudio.start();
			} else if (chronometerValue.equals(restTime)) {

				// stop the sport chronometer and clear the sport chronometer
				// value
				SportChronometerActivity.this.restChronometer.stop();

				// add start (go) audio
				SportChronometerActivity.this.dingAudio.pause();
				
				// start the tita audio
				SportChronometerActivity.this.titaAudio.start();
				// if tita audio is play completed the restart it
				// need implements OnCompletionListener#onCompletion method
				SportChronometerActivity.this.titaAudio.setOnCompletionListener(new OnCompletionListener(){
					@Override
					public void onCompletion(MediaPlayer mp) {
						mp.start();
					}
				});
				// end restart tita audio.
				
				SportChronometerActivity.this.sportChronometer.setBase(SystemClock.elapsedRealtime());
				SportChronometerActivity.this.sportChronometer.setOnChronometerTickListener(
						new OnSportChronometerTickListenerImpl());
				// start the sport chronometer
				SportChronometerActivity.this.sportChronometer.start();
				SportChronometerActivity.this.restChronometer.stop();
				// set the rest chronometer tick listener to null
				SportChronometerActivity.this.restChronometer.setOnChronometerTickListener(null);
			}
		}
	}

	public String parseSecondToMMSS(String seconds, boolean flag) {
		if (seconds != null && !"".equals(seconds.trim())) {
			// int secs = Integer.parseInt(seconds) * (flag ? runTimes : 1);
			int secs = Integer.parseInt(seconds);
			int m = (int) Math.floor(secs / 60);
			int s = secs % 60;
			String mm = m < 10 ? "0" + m : "" + m;
			String ss = s < 10 ? "0" + s : "" + s;
			Log.i("ret mm:ss :", mm + ":" + ss);
			return mm + ":" + ss;
		}
		return "00:00";
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(KeyEvent.KEYCODE_BACK == keyCode){
			Log.i("backPressCount:", backPressCount+"");
			this.backPressCount ++ ;
			Log.i("backPressCount==2:", (backPressCount==2)+"");
			if(this.backPressCount == 2){
				this.finish();
			}else {
				this.toolTip.show();
			}
		}
		return false;
	}
	
}
