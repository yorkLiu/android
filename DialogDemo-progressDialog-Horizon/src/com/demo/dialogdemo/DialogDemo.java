package com.demo.dialogdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
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
			final int max = 100;
//			final ProgressDialog dialog = ProgressDialog.show(DialogDemo.this,
//					"查找网络", "正在查找网络...");
			// 只能用 new 的方式，如果用show的方式，则会报NullPointer Exception
			final ProgressDialog dialog = new ProgressDialog(DialogDemo.this);
			dialog.setTitle("查找网络");
			dialog.setMessage("正在查找网络...");
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setProgress(20);
			dialog.setMax(max);
			
			new Thread(){
				public void run() {
					try{
						for(int i=20;i<max;i++){
							Thread.sleep(500);
							dialog.incrementProgressBy(1);
						}
						dialog.dismiss();
						
					}catch(Exception e){
						
					}
				};
			}.start();
			// show dialog
			dialog.show();
		}
	}
}