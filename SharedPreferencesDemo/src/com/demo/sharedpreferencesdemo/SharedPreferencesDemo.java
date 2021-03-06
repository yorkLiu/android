package com.demo.sharedpreferencesdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.TextView;

public class SharedPreferencesDemo extends Activity {
	
	private static final String FILENAME="demo";
	private static final String NO_VALUE="N/A";
	
	private TextView authorInfo = null;
	private TextView ageInfo = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		authorInfo = (TextView)this.findViewById(R.id.authorInfo);
		ageInfo = (TextView)this.findViewById(R.id.ageInfo);
		
		// Save data to SharedPreferences with file name 'demo'
		SharedPreferences shared = this.getSharedPreferences(this.FILENAME, Activity.MODE_PRIVATE);
		Editor editor = shared.edit();
		editor.putString("author", "York Yong");
		editor.putInt("age", 26);
		editor.commit();
		
		// read the file named 'demo' SharedPreferences data
		authorInfo.setText("作者: " + shared.getString("author", this.NO_VALUE));
		ageInfo.setText("年龄: " + shared.getInt("age", 0));
		
	}

}
