package com.demo.showpassword;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class ShowPassword extends Activity {
	private EditText passwordText = null;
	private CheckBox showPassword = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		passwordText = (EditText) this.findViewById(R.id.passwordText);
		showPassword = (CheckBox) this.findViewById(R.id.showPassword);
		
		showPassword.setOnCheckedChangeListener(new OnShowPasswordCheckedChangeListener());
		passwordText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				passwordText.setText("");
			}
		});
		
	}
	
	private class OnShowPasswordCheckedChangeListener implements OnCheckedChangeListener{
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if(isChecked){// 显示明文
				ShowPassword.this.passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			} else {// 显示密文
				ShowPassword.this.passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
			}
		}
	}
}
