package com.demo.scrollviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollViewDemo extends Activity {
	
	private LinearLayout linearLayout = null;
	
	private String[] labels = {"First Name", "Middle Name", "Last Name",
								"Age", "Email", "Home Address", "Work Address",
								 "Other Address", "Home Phone","Work Phone", 
								 "Country", "Province", "City", "Card Number", 
								 "Sex","Have House", "Have Car", "Married"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		linearLayout = (LinearLayout)this.findViewById(R.id.linearLayout);
		
		for(int i = 0, len = labels.length; i<len; i++){
			//// 定义一个horizon的Linear Layout
			LinearLayout horizonLayout = new LinearLayout(this);
			LinearLayout.LayoutParams horizonLayoutParams = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			horizonLayout.setOrientation(LinearLayout.HORIZONTAL);
			//// end
			
			//// 一个TextView
			TextView label = new TextView(this);
			label.setText(labels[i]+": ");
			
			/// 一个 EditText
			EditText editText = new EditText(this);
			editText.setWidth(200);
			
			//// add text view to horizon layout
			horizonLayout.addView(label, 0);
			//// add edit text to horizon layout
			horizonLayout.addView(editText, 1);
			
			linearLayout.addView(horizonLayout);
		}
	}
}
