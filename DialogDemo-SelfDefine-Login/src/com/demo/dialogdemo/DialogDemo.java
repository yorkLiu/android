package com.demo.dialogdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

	private class OnButtonClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			
			// 如果想在Dialog中使用自义的布局文件，则要使用LayoutInflater 这个工厂来实现
			LayoutInflater factory =  LayoutInflater.from(DialogDemo.this);
			//创建View
			View loginView = factory.inflate(R.layout.login, null);
			
			Dialog dialog = new AlertDialog.Builder(DialogDemo.this)
			.setTitle("请登陆")
			.setNeutralButton("登陆", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO login
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO cancel
				}
			})
			.setView(loginView) //  将loginView添加到Dialog中
			.create();
			
			dialog.show();
		}
	}
}