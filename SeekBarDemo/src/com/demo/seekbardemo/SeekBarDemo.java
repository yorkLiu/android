package com.demo.seekbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SeekBarDemo extends Activity {
	
	private SeekBar seekBar = null;
	private TextView messge = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		seekBar = (SeekBar)this.findViewById(R.id.seekbar);
		messge = (TextView)this.findViewById(R.id.message);
		
		// TextView在默认情况下，是没有ScrollBar的，即使滿了一屏也不会让你往下拖
	    // 因此加上这一名代码就是说可以让用户拖动
		messge.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpl());
	}
	
	private class OnSeekBarChangeListenerImpl implements OnSeekBarChangeListener{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			SeekBarDemo.this.messge.append("正在拖动:" + seekBar.getProgress() + "\n");
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			SeekBarDemo.this.messge.append("开始拖动:" + seekBar.getProgress() + "\n");
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			SeekBarDemo.this.messge.append("结束拖动:" + seekBar.getProgress() + "\n");
		}
	}
}
