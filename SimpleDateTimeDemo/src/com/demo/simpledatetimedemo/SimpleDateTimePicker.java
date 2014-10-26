package com.demo.simpledatetimedemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class SimpleDateTimePicker extends Activity {
	
	private DatePicker datePicker = null;
	private TimePicker timePicker = null;
	private EditText inputText = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		this.datePicker = (DatePicker)this.findViewById(R.id.datePicker);
		this.timePicker = (TimePicker)this.findViewById(R.id.timePicker);
		this.inputText = (EditText)this.findViewById(R.id.inputText);
		this.timePicker.setIs24HourView(true); // 24小时制
		
		this.timePicker.setOnTimeChangedListener(new OnTimeChangedListenerImpl());
		this.datePicker.init(
				datePicker.getYear(), 
				datePicker.getMonth(), 
				datePicker.getDayOfMonth(), 
				new onDateChangedListenerImpl());
		
		// 加载的时候有一个初始什值，当前时间
		this.setDateTime();
	}
	
	private void setDateTime(){
		int month = this.datePicker.getMonth()+1;
		String dateTime = this.datePicker.getYear() + 
		"-" + (month >= 10 ? month : "0" + month) + 
		"-" + this.datePicker.getDayOfMonth() + 
		" " + this.timePicker.getCurrentHour() + 
		":" + this.timePicker.getCurrentMinute(); 
		
		this.inputText.setText(dateTime);
	}
	
	private class OnTimeChangedListenerImpl implements OnTimeChangedListener{
		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			SimpleDateTimePicker.this.setDateTime();
		}
	}
	
	private class onDateChangedListenerImpl implements OnDateChangedListener{
		@Override
		public void onDateChanged(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			SimpleDateTimePicker.this.setDateTime();
			
		}
	}

}
