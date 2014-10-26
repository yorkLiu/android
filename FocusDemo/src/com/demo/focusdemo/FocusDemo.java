package com.demo.focusdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class FocusDemo extends Activity {
	
	private TextView msg = null;
	private EditText username = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		msg = (TextView) this.findViewById(R.id.msg);
		username = (EditText) this.findViewById(R.id.username);
		
		username.setOnFocusChangeListener(new OnFocusChangeListenerImpl());
		username.setOnClickListener(new OnClickListenerImpl());
	}
	
	private class OnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			username.setText("");
		}
	}
	
	private class OnFocusChangeListenerImpl implements OnFocusChangeListener{

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if(hasFocus){
				// change the text color to blue
				FocusDemo.this.msg.setTextColor(Color.BLUE);
				FocusDemo.this.msg.setText("Focus in username field");
			} else {
				if(FocusDemo.this.username.getText().length() == 0){
					// change the text color to red
					FocusDemo.this.msg.setTextColor(Color.RED);
					FocusDemo.this.msg.setText("The User name can not be null.");
				}
			}
		}
	}
}
