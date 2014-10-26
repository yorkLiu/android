package com.demo.contentproviderdemo;

import android.net.Uri;
import android.provider.BaseColumns;

public interface MemberMetaData extends BaseColumns {
	
	/**
	 * 此类为何要 extends BaseColumns
	 * 因为 BaseColumns 中本身就有以下那个属性
	 * 
	 * static final String _ID
	 * 
	 * static final String _COUNT
	 * 
	 *  所有我们的 Create member表的Sql 的id特意加了下划线'_id' , 就是因为这个原因
	 * 
	 */
	
	 // 表名： member
	public static final String TABLE_NAME = "member";
	
	/// 外部访问URI地址为：content://com.demo.contentproviderdemo/member
	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ DatabaseMetaData.AUTHORITY + "/" + TABLE_NAME);
	
	//表示 member.name字段名称
	public static final String MEMBER_NAME = "name";
	
	//表示 member.age字段名称
	public static final String MEMBER_AGE = "age";
	
	//表示 member.birthday字段名称
	public static final String MEMBER_BIRTHDAY = "birthday";
	
	//表示 排序字段
	public static final String SORT_FIELD = "_id DESC";
	
	// 取得member表中所有的数据
	public static final String CONTENT_LIST = "vnd.android.cursor.dir/vnd.contentproviderdemo.member";
	
	// 取得member表中其中的一条数据, 相当于按ID查询
	public static final String CONTENT_ITEM = "vnd.android.cursor.item/vnd.contentproviderdemo.member";	

}
