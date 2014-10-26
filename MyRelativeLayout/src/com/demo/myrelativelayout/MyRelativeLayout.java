package com.demo.myrelativelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		//向现有的布局当中添加控件
		RelativeLayout layout = (RelativeLayout) this.findViewById(R.id.relativeLayout);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, 
				ViewGroup.LayoutParams.FILL_PARENT);
		
		// add Rules --> 相对位置
		params.addRule(RelativeLayout.BELOW, R.id.myText);
		params.addRule(RelativeLayout.RIGHT_OF, R.id.myText);
		
		EditText text = new EditText(this);
		layout.addView(text, params);
	}
}
