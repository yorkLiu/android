package com.example.intentdemo;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

public class ReadContactActivity extends Activity {
	
	private static final int RESULT_READ_CONTACT = 5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Uri uri = Uri.parse("content://contacts/people");
		Intent intent = new Intent(Intent.ACTION_PICK, uri);
		this.startActivityForResult(intent, RESULT_READ_CONTACT);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case RESULT_READ_CONTACT:
			Uri ret = data.getData();
			String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?";
			String[] selectionArgs = {String.valueOf(ContentUris.parseId(ret))};
			
			Cursor cursor = super.managedQuery(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					selection, selectionArgs, null);
			
			StringBuffer sb = new StringBuffer();
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				String phone = cursor
						.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				
				sb.append(phone);
			}
			System.out.println("--sb:" + sb);
			Toast.makeText(ReadContactActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
			break;
		}
	}
}
