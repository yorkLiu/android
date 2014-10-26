package com.demo.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyTableHelper extends SQLiteOpenHelper {

	public MyTableHelper(Context context) {
		super(context, DatabaseMetaData.DATABASE_NAME, null, DatabaseMetaData.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE "+MemberMetaData.TABLE_NAME+"("
				+ "_id INTEGER	PRIMARY KEY, "
				+ "name VARCHAR(50) NOT NULL, "
				+ "age INTEGER NOT NULL, "
				+ "birthday DATE NOT NULL"
				+")";
		
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE "+MemberMetaData.TABLE_NAME+" IF EXISTS";
		db.execSQL(sql);
		this.onCreate(db);
	}

}
