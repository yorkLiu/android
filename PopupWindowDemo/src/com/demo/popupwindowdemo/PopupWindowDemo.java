package com.demo.popupwindowdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class PopupWindowDemo extends Activity {
	
	private TextView onlineInfo;
	private Button statusBtn;
	private View popView;
	private Button cancelBtn;
	private RadioGroup chooseStatusRadios;
	private PopupWindow popupWindow;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		onlineInfo = (TextView)this.findViewById(R.id.onlineInfo);
		statusBtn = (Button)this.findViewById(R.id.statusBtn);
		
		statusBtn.setOnClickListener(new OnClickListenerImpl());
	}
	
	private class OnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			LayoutInflater inflater = LayoutInflater.from(PopupWindowDemo.this);
			PopupWindowDemo.this.popView = inflater.inflate(R.layout.popup_window, null); // 找到布局文件的View
			PopupWindowDemo.this.popupWindow = new PopupWindow(PopupWindowDemo.this.popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			PopupWindowDemo.this.chooseStatusRadios = (RadioGroup)popView.findViewById(R.id.statusRadioGroup);// 取得弹出界面的组件
			PopupWindowDemo.this.cancelBtn = (Button)popView.findViewById(R.id.cancel);
			PopupWindowDemo.this.chooseStatusRadios.setOnCheckedChangeListener(new OnCheckedChangeListenerImpl());
			
			PopupWindowDemo.this.cancelBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					PopupWindowDemo.this.popupWindow.dismiss();
				}
			});
			
			PopupWindowDemo.this.popupWindow.showAtLocation(PopupWindowDemo.this.statusBtn, Gravity.CENTER_HORIZONTAL, 0, 0);
		}
	}
	
	private class OnCheckedChangeListenerImpl implements OnCheckedChangeListener{
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			RadioButton radioBtn = (RadioButton)popView.findViewById(group.getCheckedRadioButtonId());
			PopupWindowDemo.this.onlineInfo.setText("您当前的状态: " + radioBtn.getText());
		}
	}
}
