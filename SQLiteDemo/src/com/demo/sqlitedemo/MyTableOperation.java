package com.demo.sqlitedemo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class MyTableOperation {
	
	private SQLiteDatabase db = null;
	
	/**table named person**/
	private static final String TABLE_PERSON = "Person";
	
	public MyTableOperation(SQLiteDatabase db) {
		this.db = db;
	}
	
	public void insert(String name, String birthday){
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("birthday", birthday);
		this.db.insert(TABLE_PERSON, null, cv);
		this.db.close();
	}
	
	public void update(Integer id, String name, String birthday){
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("birthday", birthday);
		String whereClause = "id=?";
		String[] whereArgs = new String[]{String.valueOf(id)};
		this.db.update(TABLE_PERSON, cv, whereClause, whereArgs);
		this.db.close();
	}
	
	public void delete(Integer id){
		String whereClause = "id=?";
		String[] whereArgs = new String[]{String.valueOf(id)};
		this.db.delete(TABLE_PERSON, whereClause, whereArgs);
		this.db.close();
	}
}
