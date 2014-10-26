package com.demo.toastdemoselfdefine;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ToastDemo extends Activity {
	
	private Button info = null;
	private Button warning = null;
	private Button error = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		info = (Button)this.findViewById(R.id.info); 
		warning = (Button)this.findViewById(R.id.warning); 
		error = (Button)this.findViewById(R.id.error); 
		
		info.setOnClickListener(new OnClickListenerImpl(
				OnClickListenerImpl.TOOLTIP_INFO,
				"This is an info message."));
		
		warning.setOnClickListener(new OnClickListenerImpl(
				OnClickListenerImpl.TOOLTIP_WARNING,
				"This is a warning message."));
		
		error.setOnClickListener(new OnClickListenerImpl(
				OnClickListenerImpl.TOOLTIP_ERROR,
				"This is an error message."));
	}
	
	private class OnClickListenerImpl implements OnClickListener{
		public static final int TOOLTIP_INFO=0;
		public static final int  TOOLTIP_WARNING=1;
		public static final int  TOOLTIP_ERROR=2;
		
		private int type = 0;
		private String message = null;
		
		public OnClickListenerImpl(int type,  String message){
			this.type = type;
			this.message = message;
		}

		@Override
		public void onClick(View v) {
			new ToolTip(type, message).show();
		}
	}
	
	private class ToolTip {
		private String message = null;
		private int type = 0;
		
		public ToolTip(int type, String message){
			this.type = type;
			this.message = message;
		}
		
		private int getIcon(){
			switch (this.type) {
			case 0:
				return R.drawable.icon_info;
			case 1:
				return R.drawable.icon_warning;
			case 2:
				return R.drawable.icon_error;
			}
			return 0;
		}
		
		public void show(){
			Toast toast = Toast.makeText(ToastDemo.this, this.message, Toast.LENGTH_SHORT);
			// 显示在屏幕上的位置
			toast.setGravity(Gravity.CENTER, 3, 3);
		    // 获得Toast的layout
			LinearLayout layout = (LinearLayout)toast.getView();
			// 让原有的LinearLayout 以水平方式布局
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			// 改变Toast layout的排列方式
			layout.setOrientation(LinearLayout.HORIZONTAL);
			
			// new  Image
			ImageView icon = new ImageView(ToastDemo.this);
			icon.setPadding(3, 3, 10, 3);
			icon.setImageResource(getIcon());
			
			// add icon to toast
			layout.addView(icon, 0, params);
			
			// 显示信息提示框
			toast.show();
		}
	}
}
