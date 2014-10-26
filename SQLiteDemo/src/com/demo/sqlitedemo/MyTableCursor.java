package com.demo.sqlitedemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyTableCursor {
	
	private SQLiteDatabase db = null;
	
	/**table named person**/
	private static final String TABLE_PERSON = "Person";
	
	public MyTableCursor(SQLiteDatabase db){
		this.db = db;
	}

	public int getTotalCount(String keyWorld){
		int count = 0;
		String[] args = null;
		
		String sql = "SELECT COUNT(id) FROM " + TABLE_PERSON;
		if(keyWorld != null && !"".equals(keyWorld)){
			sql += " WHERE name like ? ";
			args = new String[]{"%" + keyWorld + "%"};
		}
		
		Cursor cursor =  this.db.rawQuery(sql, args);
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			count = cursor.getInt(0);
		}
		
		return count;
	}

	public List<Map<String, Object>> find(String keyWorld, int currentPage, int limit){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		String[] args = null;
		
		String sql = "SELECT id, name, birthday FROM " + TABLE_PERSON;
		if(keyWorld != null && !"".equals(keyWorld)){
			sql += " WHERE name like ? ";
			args = new String[]{
					"%" + keyWorld + "%", 
					String.valueOf((currentPage -1) * limit), 
					String.valueOf(currentPage*limit)};
		} else {
			args = new String[]{String.valueOf((currentPage -1) * limit), 
					String.valueOf(currentPage*limit)};
		}
		
		sql+= " LIMIT ? , ?";
		
		Cursor cursor = this.db.rawQuery(sql, args);
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			Map<String, Object> map = new HashMap<String, Object>(3);
			map.put("id", cursor.getInt(0));
			map.put("name", cursor.getString(1));
			map.put("birthday", cursor.getString(2));
			result.add(map);
		}
		
		this.db.close();
		return result;
	}
	
	public List<String> find(){
		List<String> result = new ArrayList<String>();
		result.add("ID\tName\tBirthDay");
		// 使用query的模糊查询
		String keyWorld = "16";
		String [] columns = new String[]{"id", "name", "birthday"};
		String selection = " name like ? OR birthday like ? ";
		String [] selectionArgs = new String[]{"%" + keyWorld + "%", "%" + keyWorld + "%" };
		Cursor cursor =  this.db.query(TABLE_PERSON, columns, selection, selectionArgs, null, null, null, "2");
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			result.add(cursor.getInt(0) + "\t" + cursor.getString(1) + "\t" + cursor.getString(2));
		}
		
		return result;
	}
	
	public List<String> findWithRawQuery(){
		List<String> result = new ArrayList<String>();
		result.add("ID\tName\tBirthDay");
		// 查询SQL
		String sql = "SELECT id, name, birthday FROM " + TABLE_PERSON;
		// run rawQuery() 返回的是指向表记录的指针
		Cursor cursor = this.db.rawQuery(sql, null);
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			result.add(cursor.getInt(0) + "\t" + cursor.getString(1) + "\t" + cursor.getString(2));
		}
		
		return result;
	}
	
	public List<String> findWithQuery(){
		List<String> result = new ArrayList<String>();
		result.add("ID\tName\tBirthDay");
		String [] columns = new String[]{"id", "name", "birthday"};
		
		// run query() 返回的同样是指向表记录的指针
		Cursor cursor =  this.db.query(TABLE_PERSON, columns, null, null, null, null, null);
		// groupBy name and orderBy birthday 
		//Cursor cursor = this.db.query(TABLE_PERSON, columns, null, null, "name", null, "birthday");
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			result.add(cursor.getInt(0) + "\t" + cursor.getString(1) + "\t" + cursor.getString(2));
		}
		
		return result;
	}
	
	/**
	 * rawQuery 的模糊查询
	 * @return
	 */
	public List<String> findWithRawQueryLike(){
		List<String> result = new ArrayList<String>();
		result.add("ID\tName\tBirthDay");
		// 查询SQL
		String sql = "SELECT id, name, birthday FROM " + TABLE_PERSON
					+ " WHERE name like ? OR birthday like ? ";
		String keyWorld = "18";
		String [] args = new String[]{"%" + keyWorld + "%", "%" + keyWorld + "%" };
		// run rawQuery() 返回的是指向表记录的指针
		Cursor cursor = this.db.rawQuery(sql, args);
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			result.add(cursor.getInt(0) + "\t" + cursor.getString(1) + "\t" + cursor.getString(2));
		}
		
		return result;
	}
	
	/**
	 * 使用query的模糊查询
	 * @return
	 */
	public List<String> findWithQueryLike(){
		List<String> result = new ArrayList<String>();
		result.add("ID\tName\tBirthDay");
		// 使用query的模糊查询
		String keyWorld = "16";
		String [] columns = new String[]{"id", "name", "birthday"};
		String selection = " name like ? OR birthday like ? ";
		String [] selectionArgs = new String[]{"%" + keyWorld + "%", "%" + keyWorld + "%" };
		Cursor cursor =  this.db.query(TABLE_PERSON, columns, selection, selectionArgs, null, null, null);
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			result.add(cursor.getInt(0) + "\t" + cursor.getString(1) + "\t" + cursor.getString(2));
		}
		
		return result;
	}
}
