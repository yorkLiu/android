package com.demo.listviewarrayadapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewDemo extends Activity {

	private String[] items = { "First Name", "Middle Name", "Last Name",
			"Age", "Email", "Home Address", "Work Address", "Other Address",
			"Home Phone", "Work Phone", "Country", "Province", "City",
			"Card Number", "Sex", "Have House", "Have Car", "Married" };
	
	private ListView listView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listView = new ListView(this);
		listView.setAdapter(new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, items));
		
		this.setContentView(listView);
	}
}
