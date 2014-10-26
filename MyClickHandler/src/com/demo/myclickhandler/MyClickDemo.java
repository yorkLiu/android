package com.demo.myclickhandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyClickDemo extends Activity {
	
	private EditText number1 = null;
	private EditText number2 = null;
	private TextView operationFlag = null;
	private TextView result = null;
	private Button addBtn = null;
	private Button subBtn = null;
	private Button multiBtn = null;
	private Button divBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		number1 = (EditText) this.findViewById(R.id.numberOne);
		number2 = (EditText) this.findViewById(R.id.numberTwo);
		operationFlag = (TextView) this.findViewById(R.id.operationFlag);
		result = (TextView) this.findViewById(R.id.result);
		addBtn = (Button) this.findViewById(R.id.addBtn);
		subBtn = (Button) this.findViewById(R.id.subBtn);
		multiBtn = (Button) this.findViewById(R.id.multiBtn);
		divBtn = (Button) this.findViewById(R.id.divBtn);
		
		addBtn.setOnClickListener(new OnClickListenerHandler(addBtn.getText().toString()));
		subBtn.setOnClickListener(new OnClickListenerHandler(subBtn.getText().toString()));
		multiBtn.setOnClickListener(new OnClickListenerHandler(multiBtn.getText().toString()));
		divBtn.setOnClickListener(new OnClickListenerHandler(divBtn.getText().toString()));
		
		
	}
	
	private class OnClickListenerHandler implements OnClickListener{
		private String operationFlag = null;
		public OnClickListenerHandler(){}
		
		public OnClickListenerHandler(String operationFlag){
			this.operationFlag = operationFlag;
		}
		@Override
		public void onClick(View v) {
			MyClickDemo.this.operationFlag.setText(operationFlag);
			MyClickDemo.this.result.setText(this.caculate().toString());
		}
		
		private Float caculate() {
			Float result = 0f;
			String num1 = MyClickDemo.this.number1.getText().toString();
			String num2 = MyClickDemo.this.number2.getText().toString();
			Float numberFloat1 = 0f;
			Float numberFloat2 = 0f;
			if(num1 != null){
				numberFloat1= Float.parseFloat(num1);
				numberFloat2= Float.parseFloat(num2);
			}
			
			if("+".equals(this.operationFlag.trim())){
				result = numberFloat1 + numberFloat2;
			}else if("-".equals(this.operationFlag.trim())){
				result = numberFloat1 - numberFloat2;
			}
			else if("x".equals(this.operationFlag.trim())){
				result = numberFloat1 * numberFloat2;
			}else if("Ö".equals(this.operationFlag.trim())){
				result = numberFloat1/numberFloat2;
			}
			
			return result;
		}
	}
}
