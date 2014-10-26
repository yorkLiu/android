package com.demo.tabhost;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabHostDemo extends Activity {
	
	private TabHost tabHost = null;
	private int[] tabs = new int[] { R.id.tabForm, R.id.tabClock, R.id.tabSex };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		this.tabHost = (TabHost) this.findViewById(R.id.tabHost);
		this.tabHost.setup(); //建立TabHost对象，此方法对于继承于TabActivity的Activity程序无需调用
		
		for(int i = 0, len = tabs.length; i<len ;i++){
			// 创建一个Tab
			TabSpec tab = tabHost.newTabSpec("tab"+i);
			// 设置Tab的Title
			tab.setIndicator("tab-"+i);
			// 设置Tab的内容
			tab.setContent(this.tabs[i]);
			// 将此Tab添加到TabHost中
			this.tabHost.addTab(tab);
		}
	}

}
