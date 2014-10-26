package com.demo.seekbarpictrue;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SeekBarViewPicture extends Activity {
	
	private ImageView picture = null;
	private SeekBar picSeekBar = null;
	
	private int[] picData = new int[]{R.drawable.icon_bird, R.drawable.icon_evernote,
			R.drawable.icon_fox, R.drawable.icon_frog, R.drawable.icon_pig, R.drawable.icon_sheep};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		picture = (ImageView)this.findViewById(R.id.picture);
		picSeekBar = (SeekBar)this.findViewById(R.id.picSeekBar);
		picSeekBar.setMax(5); // 0~5
		picSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImple());
	}

	private class OnSeekBarChangeListenerImple implements OnSeekBarChangeListener{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			SeekBarViewPicture.this.picture.setImageResource(SeekBarViewPicture.this.picData[seekBar.getProgress()]);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			
		}
	}
}
