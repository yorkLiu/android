package com.demo.spinnerselectdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerEventDemo extends Activity {
	
	private Spinner province = null;
	private Spinner city = null;
	private TextView selected = null;
	private ArrayAdapter<CharSequence> cityAdapter = null;
	
	// 用一个二维数组来保存城市
	private static String[][] cities = {
			{"成都", "宜宾", "自贡", "达州"},
			{"沙平坝", "邻水", "梁平"},
			{"朝阳", "城本"},
			{"西安"}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		province = (Spinner) this.findViewById(R.id.province);
		city = (Spinner) this.findViewById(R.id.city);
		selected = (TextView) this.findViewById(R.id.selected);
		
		province.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
	}
	
	private class OnItemSelectedListenerImpl implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// 获得选中的Spinner 值
			// parent.getItemAtPosition(position).toString()
			SpinnerEventDemo.this.selected.setText("您选择的是: " + parent.getItemAtPosition(position).toString());
			// 动态的设置Array Adapter
			SpinnerEventDemo.this.cityAdapter = new ArrayAdapter<CharSequence>(
					SpinnerEventDemo.this, 
					android.R.layout.simple_spinner_item,
					SpinnerEventDemo.this.cities[position]);
			cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			SpinnerEventDemo.this.city.setAdapter(SpinnerEventDemo.this.cityAdapter);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			//TODO 一般不会写
		}
	}

}
