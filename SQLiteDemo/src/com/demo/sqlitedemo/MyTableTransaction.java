package com.demo.sqlitedemo;

import android.database.sqlite.SQLiteDatabase;

public class MyTableTransaction {

	private SQLiteDatabase db = null;

	/** table named person **/
	private static final String TABLE_PERSON = "Person";

	public MyTableTransaction(SQLiteDatabase db) {
		this.db = db;
	}

	public void insertBatch() {
		
		this.db.beginTransaction(); //开始事务
		try{
			
			this.db.execSQL("INSERT INTO " + TABLE_PERSON + " (name, birthday) values(?, ?)",  new Object[]{"Lily", "1980-12-20"});
			this.db.execSQL("INSERT INTO " + TABLE_PERSON + " (name, birthday) values(?, ?)",  new Object[]{"Lucy", "2002-03-12"});
			this.db.execSQL("INSERT INTO " + TABLE_PERSON + " (name, birthday) values(?, ?)",  new Object[]{"Tonny", "2005-09-10"});
			this.db.setTransactionSuccessful(); //事务成功
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			this.db.endTransaction(); //结束事务
		}
		this.db.close();
	}
}
