package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends Activity {
	
	private EditText receiver = null;
	private EditText messageInfo = null;
	private Button sendSms = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.sms_main);
		receiver = (EditText) this.findViewById(R.id.receiver);
		messageInfo = (EditText) this.findViewById(R.id.messageInfo);
		sendSms = (Button)this.findViewById(R.id.sendSmsBtn);
		sendSms.setOnClickListener(new OnDialClickListeners());
	}
	
	private class OnDialClickListeners implements OnClickListener{
		@Override
		public void onClick(View v) {
			String receiverStr = receiver.getText().toString();
			String smsMessage = messageInfo.getText().toString();
			Intent intent = new Intent();
			Uri uri = Uri.parse("smsto:" + receiverStr);
			intent.setAction(Intent.ACTION_SENDTO); // 设置Intent的action
			intent.putExtra("sms_body", smsMessage); //设置短信的内容文本 固定为：sms_body
			intent.setType("vnd.android-dir/mms-sms"); //设置短信的MIME类型
			intent.setData(uri);
			SmsActivity.this.startActivity(intent);
		}
	}
}
