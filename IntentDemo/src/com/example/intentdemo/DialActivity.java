package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DialActivity extends Activity {
	
	private EditText phoneNum = null;
	private Button dial = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.dial_main);
		phoneNum = (EditText) this.findViewById(R.id.phoneNum);
		dial = (Button)this.findViewById(R.id.dial);
		dial.setOnClickListener(new OnDialClickListeners());
	}
	
	private class OnDialClickListeners implements OnClickListener{
		@Override
		public void onClick(View v) {
			String phoneNumStr = phoneNum.getText().toString();
			Intent intent = new Intent();
			Uri uri = Uri.parse("tel:" + phoneNumStr);
//			intent.setAction(Intent.ACTION_DIAL); // 设置Intent的action 为dial(拨打电话的应用程序)
			intent.setAction(Intent.ACTION_CALL); // 设置Intent的action 为dial(拨打电话的应用程序)
			intent.setData(uri);
			DialActivity.this.startActivity(intent);
		}
	}
}
