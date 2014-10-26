package com.demo.dialogdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogDemo extends Activity {
	
	Button button = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		button = (Button)this.findViewById(R.id.button);
		button.setOnClickListener(new OnButtonClickListenerImpl());
	}
	
	private class OnButtonClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			Dialog dialog = new AlertDialog.Builder(DialogDemo.this)
			.setIcon(R.drawable.icon_info)
			.setTitle("提示对话框")
			.setMessage("这是对话框的Message")
			.create(); 
			
			dialog.show();
		}
	}

}
