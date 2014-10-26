package com.demo.mynestedlayout;

import android.app.Activity;
import android.os.Bundle;

public class NestedLayout extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
	}
}
