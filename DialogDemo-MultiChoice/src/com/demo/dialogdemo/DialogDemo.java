package com.demo.dialogdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DialogDemo extends Activity {
	private Button button = null;
	private TextView info = null;
	
	private String[] items = {"北京", "天津", "重庆", "成都", "西安"};
	private boolean[] selectedItems = {true, true, false, false, false};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		button = (Button)this.findViewById(R.id.button);
		info = (TextView)this.findViewById(R.id.info);
		
		button.setOnClickListener(new OnButtonClickListenerImpl());
	}
	
	private class OnButtonClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			Dialog dialog = new AlertDialog.Builder(DialogDemo.this)
			.setIcon(R.drawable.icon_info)
			.setTitle("请选择你最喜欢的城市")
			.setNegativeButton("取消", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO something on click cancel
				}
			})
			.setPositiveButton("确定", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String message = "";
					for(int i = 0, len=DialogDemo.this.selectedItems.length;i<len ; i++){
						if(DialogDemo.this.selectedItems[i]){
							message +="," + DialogDemo.this.items[i];
						}
					}
					info.setText("您最喜欢的城市是：" + (message.length()>0 ? message.substring(1) : ""));
				}
			})
			.setMultiChoiceItems(DialogDemo.this.items, selectedItems , new DialogInterface.OnMultiChoiceClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which,
						boolean isChecked) {
					DialogDemo.this.selectedItems[which] = isChecked; //改变
				}
				
			}) .create(); 
			
			dialog.show();
		}
	}

}
