package com.example.intentsimpledemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SendActivity extends Activity {
	
	private Button sendBtn = null;
	private TextView retInfo = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.send_main);
		sendBtn = (Button)this.findViewById(R.id.sendBtn);
		retInfo = (TextView)this.findViewById(R.id.retInfo);
		
		sendBtn.setOnClickListener(new OnSendClickListener());
	}
	
	private class OnSendClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(SendActivity.this, ReceiveActivity.class);
			intent.putExtra("params0", "This message is from SendActivity.");
//			SendActivity.this.startActivity(intent);
			SendActivity.this.startActivityForResult(intent, 1);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case RESULT_OK:
			retInfo.setText(data.getStringExtra("retInfo"));
			break;
		case RESULT_CANCELED:
			retInfo.setText("Cancelled");
			break;
		default:
			retInfo.setText("User self Defined.");
			break;
		}
	}
}
