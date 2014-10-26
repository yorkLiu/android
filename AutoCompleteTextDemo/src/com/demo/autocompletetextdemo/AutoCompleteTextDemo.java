package com.demo.autocompletetextdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTextDemo extends Activity {
	
	private AutoCompleteTextView autoCompleteTextView;
	
	private String[] DATA = new String[]{"York", "You", "You are good", "Are you ready", "You are so smart", "Can you"}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		autoCompleteTextView = (AutoCompleteTextView) this.findViewById(R.id.autoCompleteTextView);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DATA);
		autoCompleteTextView.setAdapter(adapter);
	}

}
