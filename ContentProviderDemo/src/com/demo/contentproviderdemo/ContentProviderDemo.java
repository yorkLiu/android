package com.demo.contentproviderdemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URIUtils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ContentProviderDemo extends Activity {
	
	private Button insertBtn = null;
	private Button updateBtn = null;
	private Button deleteBtn = null;
	private Button queryBtn = null;
	private ListView listView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		insertBtn = (Button)this.findViewById(R.id.insertBtn); 
		updateBtn = (Button)this.findViewById(R.id.updateBtn); 
		deleteBtn = (Button)this.findViewById(R.id.deleteBtn); 
		queryBtn = (Button)this.findViewById(R.id.queryBtn); 
		listView = (ListView)this.findViewById(R.id.listView); 
		
		insertBtn.setOnClickListener(new InserClickListner());
		updateBtn.setOnClickListener(new UpdateClickListner());
		deleteBtn.setOnClickListener(new DeleteClickListner());
		queryBtn.setOnClickListener(new QueryClickListner());
	}
	
	public class InserClickListner implements OnClickListener{
		@Override
		public void onClick(View v) {
			long id = testInsert("York", 20, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		}
	}
	public class UpdateClickListner implements OnClickListener{
		@Override
		public void onClick(View v) {
			testUpdate("2", "York", 12, "2001-10-01");
		}
	}
	public class DeleteClickListner implements OnClickListener{
		@Override
		public void onClick(View v) {
			testDelete("8");
		}
	}
	public class QueryClickListner implements OnClickListener{
		@Override
		public void onClick(View v) {
			testQuery();
		}
	}
	
	
	private long testInsert(String name, int age, String birthday){
		ContentResolver resolver = super.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(MemberMetaData.MEMBER_NAME, name);
		values.put(MemberMetaData.MEMBER_AGE, age);
		values.put(MemberMetaData.MEMBER_BIRTHDAY, birthday);
		
		Uri result = resolver.insert(MemberMetaData.CONTENT_URI, values);
		Log.i("insert successfully: " , result+"");
		return ContentUris.parseId(result);
	}
	
	private int testUpdate(String _id, String name, int age, String birthday){
		ContentResolver resolver = super.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(MemberMetaData.MEMBER_NAME, name);
		values.put(MemberMetaData.MEMBER_AGE, age);
		values.put(MemberMetaData.MEMBER_BIRTHDAY, birthday);
		
		int effectRows = 0;
		if(_id != null && !"".equals(_id)){
			effectRows = resolver.update(Uri.withAppendedPath(MemberMetaData.CONTENT_URI, _id), values, null, null);
		} else {
			effectRows = resolver.update(MemberMetaData.CONTENT_URI, values, null, null);
		}
		Log.i("update successfully: " , effectRows +"");
		return effectRows ;
	}
	
	private int testDelete(String _id){
		ContentResolver resolver = super.getContentResolver();
		int effectRows = 0;
		if(_id !=null && !"".equals(_id)){
			effectRows = resolver.delete(Uri.withAppendedPath(MemberMetaData.CONTENT_URI, _id), null, null);
		} else {
			effectRows = resolver.delete(MemberMetaData.CONTENT_URI, null, null);
		}
		Log.i("delete rows:" , "" + effectRows);
		return effectRows;
	}
	
	private void testQuery(){
		ContentResolver resolver = super.getContentResolver();
		List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
		Cursor cursor = resolver.query(MemberMetaData.CONTENT_URI, null, null, null, null);
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			Map<String,Object> map = new HashMap<String, Object>(4);
			map.put("_id", cursor.getInt(0));
			map.put("name", cursor.getString(1));
			map.put("age", cursor.getInt(2));
			map.put("birthday", cursor.getString(3));
			results.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, results, R.layout.member_row_tpl,
				new String[]{"_id", "name", "age", "birthday"}, new int[]{R.id._id, R.id.name, R.id.age, R.id.birthday});
		listView.setAdapter(adapter);
	}

}
