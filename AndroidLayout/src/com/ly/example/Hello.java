package com.ly.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Hello extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// use config file layout--> main.xm.
		super.setContentView(R.layout.main);
		
		// use dynamic create field
//		LinearLayout layout = new LinearLayout(this);
//		layout.setOrientation(LinearLayout.VERTICAL);
//		TextView textView = new TextView(this);
//		textView.setText(getString(R.string.info));
//		Button button = new Button(this);
//		button.setText(getString(R.string.msg));
//		
//		layout.addView(textView);
//		layout.addView(button);
//		super.setContentView(layout); // don't forget this
	}

}
