package com.demo.tabhostdemo;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabHostDemo extends TabActivity {
	
	private TabHost tabHost = null;
	
	//所有的tab layout数据，定义在tab.xml文件中
	private int[] tabs = new int[] { R.id.tabForm, R.id.tabClock, R.id.tabSex };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tabHost = super.getTabHost();
		//将tab.mxl文件中的内容映射到TabHost中
		LayoutInflater.from(this).inflate(R.layout.tab, tabHost.getTabContentView(), true);
		
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
