package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MutipleSmsActivity extends Activity {
	
	private Button sendMutipleSmsBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.mutiple_sms_main);
		sendMutipleSmsBtn = (Button)this.findViewById(R.id.sendMutipleSmsBtn);
		sendMutipleSmsBtn.setOnClickListener(new OnVisitButtonClickListeners());
	}
	
	private class OnVisitButtonClickListeners implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			Uri uri = Uri.parse("file:///sdcard/mypic.png");
			intent.setAction(Intent.ACTION_SEND);
			intent.putExtra("address", "1245676");
			intent.putExtra("sms_body", "这是一个彩信，对于彩信来是不能将它直接发送，必须通过本身的短信平台来发送");
			intent.putExtra(Intent.EXTRA_STREAM, uri);
			intent.setType("image/png");
			MutipleSmsActivity.this.startActivity(intent);
		}
		
	}

}
