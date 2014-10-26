package com.demo.sqlitedemo;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SQLiteDemo extends Activity {
	
//	SQLiteOpenHelper helper = null;
//	private Button insertBut = null;
//	private Button updateBut = null;
//	private Button deleteBut = null;
//	private Button queryBtn = null;
//	private LinearLayout myLayout = null;
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		super.setContentView(R.layout.main);
//		
//		myLayout = (LinearLayout)this.findViewById(R.id.myLayout);
//		insertBut = (Button)this.findViewById(R.id.insertBut);
//		updateBut = (Button)this.findViewById(R.id.updateBut);
//		deleteBut = (Button)this.findViewById(R.id.deleteBut);
//		deleteBut = (Button)this.findViewById(R.id.deleteBut);
//		queryBtn = (Button)this.findViewById(R.id.queryBut);
//		
//		////SQLiteOpenHelper实例
//		helper = new MySQLiteHelper(this);
//		
//		// insert listeners
//		insertBut.setOnClickListener(new InsertOnClickListenerImpl());
//		// update listeners
//		updateBut.setOnClickListener(new UpdateOnClickListenerImpl());
//		// delete listeners
//		deleteBut.setOnClickListener(new DeleteOnClickListenerImpl());
//		// query listeners
//		queryBtn.setOnClickListener(new QueryOnClickListenerImpl());
//	}
//	
//	private class InsertOnClickListenerImpl implements OnClickListener{
//		@Override
//		public void onClick(View v) {
//			MyTableOperation operation = new MyTableOperation(helper.getWritableDatabase());
//			operation.insert("York Liu", "1988-07-18");
//		}
//	}
//	
//	private class UpdateOnClickListenerImpl implements OnClickListener{
//		@Override
//		public void onClick(View v) {
//			MyTableOperation operation = new MyTableOperation(helper.getWritableDatabase());
//			operation.update(3, "York Liu", "1988-12-03");
//		}
//	}
//	
//	private class DeleteOnClickListenerImpl implements OnClickListener{
//		@Override
//		public void onClick(View v) {
//			MyTableOperation operation = new MyTableOperation(helper.getWritableDatabase());
//			operation.delete(3);
//		}
//	}
//	
//	private class QueryOnClickListenerImpl implements OnClickListener{
//		@Override
//		public void onClick(View v) {
//			MyTableCursor cursor = new MyTableCursor(helper.getReadableDatabase());
//			ListView listView = new ListView(SQLiteDemo.this);
//			listView.setAdapter(new ArrayAdapter<String>(SQLiteDemo.this, 
//					android.R.layout.simple_list_item_1, 
//					cursor.find()));
//			SQLiteDemo.this.myLayout.addView(listView) ;
//		}
//	}
}
