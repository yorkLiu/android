package com.demo.ratingbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class RatingBarDemo extends Activity {
	
	private RatingBar ratingBar = null;
	private TextView message = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		ratingBar = (RatingBar)this.findViewById(R.id.ratingBarA);
		message = (TextView) this.findViewById(R.id.message);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangedListenerImpl());
	}
	
	private class OnRatingBarChangedListenerImpl implements OnRatingBarChangeListener{
		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			RatingBarDemo.this.message.append("分数为:" + ratingBar.getRating() + " \t步长为:" + ratingBar.getStepSize() + "\n");
		}
	}
}
