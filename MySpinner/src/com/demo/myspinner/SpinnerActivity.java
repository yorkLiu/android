package com.demo.myspinner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class SpinnerActivity extends Activity {
	
	//--------第二种方式创建Spinner -----------------------------
	private Spinner colorSpinner = null;
	private ArrayAdapter<CharSequence> colorAdapter = null;
	//!--------------------------------------------------------
	
	//--------第二种方式创建Spinner -----------------------------
	private Spinner eduSpinner = null;
	private ArrayAdapter<CharSequence> eduAdapter = null;
	private List<CharSequence> eduList = null;
	//!--------------------------------------------------------
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		//--------第二种方式创建Spinner --------------------------------------------------------------------
		this.colorSpinner = (Spinner)this.findViewById(R.id.color_spinner);
		this.colorSpinner.setPrompt("请选择你喜欢的颜色");
		// android.R.layout.simple_spinner_dropdown_item
		// android.R.layout.simple_spinner_item
		this.colorAdapter = ArrayAdapter.createFromResource(this, 
				R.array.color_labes, android.R.layout.simple_spinner_item);
		// 换一种风格
		this.colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.colorSpinner.setAdapter(this.colorAdapter);
		//!----------------------------------------------------------------------------------------------
		
		//--------第二种方式创建Spinner --------------------------------------------------------------------
		this.eduList = new ArrayList<CharSequence>();
		this.eduList.add("博士");
		this.eduList.add("研究生");
		this.eduList.add("大学");
		this.eduList.add("中学");
		
		this.eduAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, eduList);
		this.eduAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		
		this.eduSpinner = (Spinner)this.findViewById(R.id.edu_spinner);
		this.eduSpinner.setPrompt("请选择您的最高学历:");
		
		this.eduSpinner.setAdapter(this.eduAdapter);
		//!----------------------------------------------------------------------------------------------
		
	}
}
