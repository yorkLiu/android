package com.demo.zoomcontroldemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ZoomControls;


public class ZoomControlDemo extends Activity {
	
	private ZoomControls zoomControls;
	
	private TextView textView;
	
	private int textSize = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		zoomControls = (ZoomControls)this.findViewById(R.id.zoomControls);
		textView = (TextView)this.findViewById(R.id.textView);
		
		this.zoomControls.setOnZoomInClickListener(new OnZoomInClickListenerImpl());
		this.zoomControls.setOnZoomOutClickListener(new OnZoomOutClickListenerImpl());
	}
	
	private class OnZoomInClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			ZoomControlDemo.this.textSize += 2;
			ZoomControlDemo.this.textView.setTextSize(ZoomControlDemo.this.textSize);;
		}
	}
	private class OnZoomOutClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			ZoomControlDemo.this.textSize -= 2;
			ZoomControlDemo.this.textView.setTextSize(ZoomControlDemo.this.textSize);
		}
	}
}
