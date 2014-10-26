package com.demo.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MemberContentProvider extends ContentProvider {
	
	// 定义UriMatcher对象
	private static UriMatcher uriMatcher = null;
	
	// 定义查询所有数据的常量标记
	private static final int GET_MEMBER_LIST = 1;
	
	// 定义查询指定条件数据的常量标记
	private static final int GET_MEMBER_ITEM = 2;
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		// 查询所有数据的 matcher
		uriMatcher.addURI(DatabaseMetaData.AUTHORITY, MemberMetaData.TABLE_NAME, GET_MEMBER_LIST);
		// 查询指定数据的 matcher
		uriMatcher.addURI(DatabaseMetaData.AUTHORITY, MemberMetaData.TABLE_NAME + "/#", GET_MEMBER_ITEM);
		
	}
	
	private MyTableHelper helper = null;

	@Override
	public boolean onCreate() {
		helper = new MyTableHelper(super.getContext());
		return true;
	}
	
	@Override
	public String getType(Uri uri) {

		switch (uriMatcher.match(uri)) {
		case GET_MEMBER_LIST:
			return MemberMetaData.CONTENT_LIST;
		case GET_MEMBER_ITEM:
			return MemberMetaData.CONTENT_ITEM;
		default:
			throw new UnsupportedOperationException("Not Support Operation: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = helper.getWritableDatabase();
		long id = 0;
		Log.i("***matched:" ,uriMatcher.match(uri)+"");
		switch (uriMatcher.match(uri)) {
		case GET_MEMBER_LIST:
			id = db.insert(MemberMetaData.TABLE_NAME, MemberMetaData._ID, values);
			return ContentUris.withAppendedId(uri, id);
		case GET_MEMBER_ITEM:
			id = db.insert(MemberMetaData.TABLE_NAME, MemberMetaData._ID, values);
			String path = uri.toString();
			String uriPath = path.substring(0, path.lastIndexOf("/"))+ id;
			return Uri.parse(uriPath);
		default:
			throw new UnsupportedOperationException("Not Support Operation: " + uri);
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int effectRows = 0;
		long id = 0;
		Log.i("***matched:" ,uriMatcher.match(uri)+"");
		switch (uriMatcher.match(uri)) {
		case GET_MEMBER_LIST:
			effectRows = db.update(MemberMetaData.TABLE_NAME, values, selection, selectionArgs);
			return effectRows;
		case GET_MEMBER_ITEM:
			id = ContentUris.parseId(uri);
			String whereClause = "_id = " + id;
			effectRows = db.update(MemberMetaData.TABLE_NAME, values, whereClause, selectionArgs);
			return effectRows;
		default:
			throw new UnsupportedOperationException("Not Support Operation: " + uri);
		}
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int effectRows = 0;
		long id = 0;
		Log.i("***matched:" ,uriMatcher.match(uri)+"");
		switch (uriMatcher.match(uri)) {
		case GET_MEMBER_LIST:
			effectRows = db.delete(MemberMetaData.TABLE_NAME, selection, selectionArgs);
			return effectRows;
		case GET_MEMBER_ITEM:
			id = ContentUris.parseId(uri);
			String whereClause = "_id = " + id;
			effectRows = db.delete(MemberMetaData.TABLE_NAME, whereClause, selectionArgs);
			return effectRows;
		default:
			throw new UnsupportedOperationException("Not Support Operation: " + uri);
		}
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = null;
		Log.i("***matched:" ,uriMatcher.match(uri)+"");
		switch (uriMatcher.match(uri)) {
		case GET_MEMBER_LIST:
			cursor = db.query(MemberMetaData.TABLE_NAME, 
					new String[]{"_id", "name", "age", "birthday"}, 
					selection, 
					selectionArgs, null, null, MemberMetaData.SORT_FIELD);
			break;
		case GET_MEMBER_ITEM:
			long id = ContentUris.parseId(uri);
			String whereClause = "_id = " + id;
			cursor = db.query(MemberMetaData.TABLE_NAME, 
					new String[]{"_id", "name", "age", "birthday"}, 
					whereClause, 
					selectionArgs, null, null, MemberMetaData.SORT_FIELD);
		default:
			throw new UnsupportedOperationException("Not Support Operation: " + uri);
		}
		
		return cursor;
	}

}
