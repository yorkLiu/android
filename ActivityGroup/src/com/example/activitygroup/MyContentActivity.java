package com.example.activitygroup;

import android.app.Activity;
import android.os.Bundle;

public class MyContentActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.mycontentlayout);
	}

}
