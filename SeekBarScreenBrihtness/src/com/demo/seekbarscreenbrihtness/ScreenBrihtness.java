package com.demo.seekbarscreenbrihtness;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ScreenBrihtness extends Activity {
	
	private SeekBar seekBar = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		seekBar = (SeekBar)this.findViewById(R.id.seekBar);
		seekBar.setProgress(30);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListnerImpl());
	}
	
	private void changeScrrenBrihtness(float num){
		WindowManager.LayoutParams layoutParams = super.getWindow().getAttributes();
		layoutParams.screenBrightness = num;
		super.getWindow().setAttributes(layoutParams);
	}
	
	private class OnSeekBarChangeListnerImpl implements OnSeekBarChangeListener{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			ScreenBrihtness.this.changeScrrenBrihtness((float)seekBar.getProgress()/100);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			
		}
	}
}
