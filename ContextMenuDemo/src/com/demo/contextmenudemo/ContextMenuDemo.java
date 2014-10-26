package com.demo.contextmenudemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContextMenuDemo extends Activity {
	private String[] cities = new String[]{"成都", "重庆", "天津", "北京", "上海", "广州"};
	private ListView listView = null;
	private ArrayAdapter<String> adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.listView = new ListView(this);
		this.adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, this.cities);
		this.listView.setAdapter(this.adapter);
		
		this.setContentView(this.listView);
		// 为listView注册contextMenu
		this.registerForContextMenu(this.listView);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Options");
		menu.setHeaderIcon(android.R.drawable.btn_plus);
		this.getMenuInflater().inflate(R.menu.mycontextmenu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuAdd:
			Toast.makeText(this, "You clicked the Add menu.",Toast.LENGTH_SHORT).show();
			break;
		case R.id.menuDelete:
			Toast.makeText(this, "You clicked the Delete menu.",Toast.LENGTH_SHORT).show();
			break;
		case R.id.menuMore:
			Toast.makeText(this, "You clicked the more menu.", Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}
	
	@Override
	public void onContextMenuClosed(Menu menu) {
		super.onContextMenuClosed(menu);
	}
	
}
