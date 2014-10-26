package com.demo.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "york.db";
	private static final int DATABASE_VERSION = 1;
	
	/**table named person**/
	private static final String TABLE_PERSON = "Person";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_PERSON
					+ "(id INTEGER PRIMARY KEY, "
					+ "name VARCHAR(100) NOT NULL, "
					+ "birthday DATE NOT NULL)";
		
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE " + TABLE_PERSON + " IF EXISTS";
		db.execSQL(sql);
		onCreate(db);
	}

}
