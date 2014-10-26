package com.example.intentsimpledemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ReceiveActivity extends Activity {
	
	private TextView msgInfo = null;
	
	private Button backBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.receive_main);
		msgInfo = (TextView)this.findViewById(R.id.msgInfo);
		backBtn = (Button)this.findViewById(R.id.backBtn); 
		
		Intent intent = this.getIntent();
		String message = intent.getStringExtra("params0");
		msgInfo.setText(message);
		
		backBtn.setOnClickListener(new BackButtonClickListener());
	}
	
	private class BackButtonClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = ReceiveActivity.this.getIntent();
			intent.putExtra("retInfo", "This message is back from ReceiveActivity.");
			ReceiveActivity.this.setResult(RESULT_OK, intent);
			ReceiveActivity.this.finish();
		}
	}

}
