package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OpenWebPageActivity extends Activity {
	
	private Button visitButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.web_page_main);
		visitButton = (Button)this.findViewById(R.id.visit);
		visitButton.setOnClickListener(new OnVisitButtonClickListeners());
	}
	
	private class OnVisitButtonClickListeners implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			Uri uri = Uri.parse("http://wap.3ggpw.cn/");
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			OpenWebPageActivity.this.startActivity(intent);
		}
		
	}

}
