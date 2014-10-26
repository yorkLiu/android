package com.demo.submenudemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MenuDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.mysubmenu, menu);
		return true;
	}
}
