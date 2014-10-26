package com.demo.myframelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MyFrameLayout extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		FrameLayout layout = new FrameLayout(this);
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, 
				ViewGroup.LayoutParams.FILL_PARENT);
		
		FrameLayout.LayoutParams viewLayoutParams = new FrameLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT);
		
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.demo2);
		
		EditText editText = new EditText(this);
		editText.setText("Please input your name...");
		
		Button button = new Button(this);
		button.setText("Click");
		
		layout.addView(imageView,viewLayoutParams);
		layout.addView(editText, viewLayoutParams);
		layout.addView(button, viewLayoutParams);
		
		super.setContentView(layout, layoutParams);
	}
}
