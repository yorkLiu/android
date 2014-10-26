package com.demo.keyeventdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ImageView;

public class KeyEventDemo extends Activity {
	
	private EditText email = null;
	private ImageView imgInfo = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		email = (EditText)this.findViewById(R.id.email);
		imgInfo = (ImageView)this.findViewById(R.id.imgInfo);
		
		email.setOnKeyListener(new OnKeyListenerImpl());
	}
	
	private class OnKeyListenerImpl implements OnKeyListener{
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			switch (event.getAction()) {
			case KeyEvent.ACTION_UP:
				if(KeyEventDemo.this.email.getText().toString().matches("\\w+@\\w+.\\w+")){
					KeyEventDemo.this.imgInfo.setImageResource(R.drawable.correct);
				} else {
					KeyEventDemo.this.imgInfo.setImageResource(R.drawable.error);
				}
				break;
				
			case KeyEvent.ACTION_DOWN:
				break;

			default:
				break;
			}
			return false;
		}
	}
}
