package com.demo.mytablelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MyTableLayoutActivity extends Activity {
	
	private String[][] data = {
			{"ID", " name", " email", "address"},
			{"1", "York", "york@dev.com", "成都"},
			{"2", "Jac", "jac@dev.com", "宇宙地球村中国四川成都天府软件园G区Android开发有限责任公司"}
			};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TableLayout layout = new TableLayout(this);
		TableLayout.LayoutParams layoutParams =  new TableLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
		
		for(int i = 0;i<data.length;i++){
			TableRow row = new TableRow(this);
			for(int j = 0;j<data[i].length; j++){
				TextView view = new TextView(this);
				view.setText(data[i][j]);
				row.addView(view, j);
			}
			layout.addView(row);
		}
		
		super.setContentView(layout, layoutParams);
	}
}
