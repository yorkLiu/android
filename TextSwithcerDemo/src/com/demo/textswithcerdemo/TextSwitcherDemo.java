package com.demo.textswithcerdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class TextSwitcherDemo extends Activity {

	private TextSwitcher textSwitcher = null;
	private Button button = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		textSwitcher = (TextSwitcher) this.findViewById(R.id.textSwitcher);
		button = (Button)this.findViewById(R.id.button); 
		
		button.setOnClickListener(new OnClickListenerImpl());
		// set factory
		textSwitcher.setFactory(new TextSwitcherViewFactory());
		// set animation
		textSwitcher.setInAnimation(AnimationUtils.loadAnimation(TextSwitcherDemo.this, android.R.anim.fade_in));
		textSwitcher.setOutAnimation(AnimationUtils.loadAnimation(TextSwitcherDemo.this, android.R.anim.fade_out));
		// 一进入这个程序就显示当前的是期时间
		this.showCurrentDateTime();
	}
	
	private void showCurrentDateTime(){
		TextSwitcherDemo.this.textSwitcher.setText(
				new SimpleDateFormat("yyyy-mm-dd hh:MM:ss.SSS").format(new Date()));
	}
	
	private class OnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			TextSwitcherDemo.this.showCurrentDateTime();
		}
	}
	
	private class TextSwitcherViewFactory implements ViewFactory{

		@Override
		public View makeView() {
			TextView view = new TextView(TextSwitcherDemo.this);
			view.setBackgroundColor(0xffffffff);
			view.setTextColor(0xff000000);
			view.setTextSize(30);
			return view;
		}
		
	}
}
