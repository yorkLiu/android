package com.demo.dialogdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class DialogDemo extends Activity {
	private Button button = null;
	private TextView info = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);

		button = (Button) this.findViewById(R.id.button);
		info = (TextView) this.findViewById(R.id.info);
		button.setOnClickListener(new OnButtonClickListenerImpl());
	}

	private class OnButtonClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			DatePickerDialog dialog = new DatePickerDialog(DialogDemo.this, new DatePickerDialog.OnDateSetListener(){
				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					DialogDemo.this.info.setText("你选择的时期为：" + year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
				}
				
			}, 2014, 7, 1);
			dialog.show();
		}
	}

}