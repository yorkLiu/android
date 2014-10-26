package com.demo.toastdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ToastDemo extends Activity {
	
	private Button buttonA = null;
	private Button buttonB = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		buttonA = (Button)this.findViewById(R.id.buttonA); 
		buttonB = (Button)this.findViewById(R.id.buttonB);
		
		buttonA.setOnClickListener(new OnClickListenerImpl("这是一个长提示信息", Toast.LENGTH_LONG));
		buttonB.setOnClickListener(new OnClickListenerImpl("这是一个短提示信息", Toast.LENGTH_SHORT));
	}
	
	private class OnClickListenerImpl implements OnClickListener {
		private int duration = Toast.LENGTH_LONG;
		private String message = null;
		
		public OnClickListenerImpl(){}
		
		public OnClickListenerImpl(String message ,int duration){
			this.message = message;
			this.duration = duration;
		}

		@Override
		public void onClick(View v) {
			Toast.makeText(ToastDemo.this, this.message, this.duration).show();;
		}
	}
}
