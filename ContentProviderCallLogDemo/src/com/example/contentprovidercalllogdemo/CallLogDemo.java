package com.example.contentprovidercalllogdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CallLogDemo extends Activity {
	
	private ListView listView =null;
	private Cursor result = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		listView = (ListView)this.findViewById(R.id.listView);
		
		result = this.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
		
		this.startManagingCursor(result) ;
		
		List<Map<String, Object>> allRecods = new ArrayList<Map<String,Object>>();
		for(result.moveToFirst(); !result.isAfterLast(); result.moveToNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("_id", result.getInt(result.getColumnIndex(CallLog.Calls._ID)));
			String tempName = result.getString(result.getColumnIndex(CallLog.Calls.CACHED_NAME));
			if(tempName == null || "".equals(tempName)){
				tempName = "未知号码";
			}
			map.put("name", tempName);
			map.put("number", result.getString(result.getColumnIndex(CallLog.Calls.NUMBER)));
			
			String callType = result.getString(result.getColumnIndex(CallLog.Calls.TYPE));
			if(callType.equals(String.valueOf(CallLog.Calls.INCOMING_TYPE))){
				callType = "Call In";
			} else if(callType.equals(String.valueOf(CallLog.Calls.OUTGOING_TYPE))){
				callType = "Call Out";
			} else if(callType.equals(String.valueOf(CallLog.Calls.MISSED_TYPE))){
				callType = "Missed";
			}
			
			map.put("type", callType);
			map.put("duration", result.getString(result.getColumnIndex(CallLog.Calls.DURATION)));
			allRecods.add(map);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, allRecods,
				R.layout.calllog_row_tpl, 
				new String[] { "_id", "number","type", "duration" }, 
				new int[] {R.id._id, R.id.number, R.id.type, R.id.duration});
		
		this.listView.setAdapter(adapter);
		
	}

}
