package com.demo.optionmenudemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuDemo extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		menu.add(Menu.NONE, Menu.FIRST + 1, 1, "Add").setIcon(android.R.drawable.ic_input_add);
//		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "Edit").setIcon(android.R.drawable.ic_input_get);
//		menu.add(Menu.NONE, Menu.FIRST + 3, 3, "Delete").setIcon(android.R.drawable.ic_input_delete);
//		menu.add(Menu.NONE, Menu.FIRST + 4, 4, "Back Up").setIcon(android.R.drawable.ic_dialog_email);
//		menu.add(Menu.NONE, Menu.FIRST + 5, 5, "Restore").setIcon(android.R.drawable.ic_menu_agenda);
//		menu.add(Menu.NONE, Menu.FIRST + 6, 6, "Save As").setIcon(android.R.drawable.ic_secure);
//		menu.add(Menu.NONE, Menu.FIRST + 7, 7, "Reopen").setIcon(android.R.drawable.ic_dialog_alert);
//		menu.add(Menu.NONE, Menu.FIRST + 8, 8, "Close").setIcon(android.R.drawable.ic_media_play);
		
		
		// use xml
		this.getMenuInflater().inflate(R.menu.mymenu, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			Toast.makeText(this, "You clicked the Add menu.",Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 2:
			Toast.makeText(this, "You clicked the Edit menu.",Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 3:
			Toast.makeText(this, "You clicked the Delete menu.", Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 4:
			Toast.makeText(this, "You clicked the Back Up menu.",Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 5:
			Toast.makeText(this, "You clicked the Restore menu.",Toast.LENGTH_SHORT).show();
			break;
			
		case Menu.FIRST + 6:
			Toast.makeText(this, "You clicked the Save as menu.",Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 7:
			Toast.makeText(this, "You clicked the Reopen menu.",Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 8:
			Toast.makeText(this, "You clicked the close menu.",Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		Toast.makeText(this, "Menu closed", Toast.LENGTH_SHORT).show();
		super.onOptionsMenuClosed(menu);
	}
}
