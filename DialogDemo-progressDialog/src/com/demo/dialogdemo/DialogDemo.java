package com.demo.dialogdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogDemo extends Activity {
	private Button button = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);

		button = (Button) this.findViewById(R.id.button);
		button.setOnClickListener(new OnButtonClickListenerImpl());
	}
	
	private class OnButtonClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			final ProgressDialog dialog = ProgressDialog.show(DialogDemo.this,
					"查找网络", "正在查找网络...");
			new Thread(){
				public void run() {
					try{
						Thread.sleep(3000);
					}catch(Exception e){
						
					}finally{
						dialog.dismiss();
					}
				};
			}.start();
		}
	}
}