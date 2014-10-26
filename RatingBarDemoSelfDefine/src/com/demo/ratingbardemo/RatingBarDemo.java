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
		
		ratingBar.setRating(2); //设置一开始选几颗星
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangedListenerImpl());
	}
	
	private class OnRatingBarChangedListenerImpl implements OnRatingBarChangeListener{
		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			int num = (int)ratingBar.getRating();
			String result = null;
			switch (num) {
			case 1:
				result = "哟西，我的非常不满意";
				break;
			case 2:
				result = "嗯，不满意";
				break;
			case 3:
				result = "一般";
				break;
			case 4:
				result = "满意";
				break;
			case 5:
				result = "亲，我非常满意哟^_^";
				break;
			}
			RatingBarDemo.this.message.setText(result);
		}
	}
}
