package com.example.contentprovidercontactdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class ContactActivity extends Activity {
	
	private ListView listView = null;
	private Cursor result = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.listView = (ListView)this.findViewById(R.id.listView);
		
		this.result = super.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, 
				null, null, null, null);
		
		this.startManagingCursor(result);
		
		
//		ArrayList<Map<String, Object>> allContacts = new ArrayList<Map<String, Object>>();
//		for(this.result.moveToFirst(); !this.result.isAfterLast(); this.result.moveToNext()){
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("_id", result.getInt(result.getColumnIndex(ContactsContract.Contacts._ID)));
//			map.put("name", result.getString(result.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
//			allContacts.add(map);
//		}
//		
//		SimpleAdapter adapter = new SimpleAdapter(this, allContacts, R.layout.contact_row_tpl,
//				new String[] { "_id", "name" },
//				new int[] { R.id._id, R.id.name });
//		this.listView.setAdapter(adapter);
		
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.contact_row_tpl, this.result, 
				new String[] { ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME }, 
				new int[] { R.id._id, R.id.name });
		this.listView.setAdapter(adapter);

	}
}
