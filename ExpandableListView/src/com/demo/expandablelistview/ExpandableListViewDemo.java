package com.demo.expandablelistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class ExpandableListViewDemo extends Activity {
	
	private ExpandableListView elistView = null;
	private BaseExpandableListAdapter eAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		// 初始化自定义的BaseExpandableAdapter
		this.eAdapter = new MyBaseExpandableAdapter(this);
		elistView = (ExpandableListView)this.findViewById(R.id.elistView);
		// 为ExpandableListView 设置Adapter
		elistView.setAdapter(this.eAdapter);
		
		// add listeners for  group expand
		this.elistView.setOnGroupExpandListener(new OnGroupExpandListenerImpl());
		// add listeners for group collapse
		this.elistView.setOnGroupCollapseListener(new OnGroupCollapseListenerImpl());
		// add listeners for child clicked
		this.elistView.setOnChildClickListener(new OnChildClickListenerImpl());
		
		// add context menu when long click the ExpandableListView(group or child)
		this.registerForContextMenu(this.elistView);
	}
	
	private class OnGroupExpandListenerImpl implements OnGroupExpandListener{
		@Override
		public void onGroupExpand(int groupPosition) {
			Toast.makeText(ExpandableListViewDemo.this, 
					ExpandableListViewDemo.this.eAdapter.getGroup(groupPosition).toString() + "-->已被展开", 
					Toast.LENGTH_SHORT).show();
		}
	}
	
	private class OnGroupCollapseListenerImpl implements OnGroupCollapseListener{

		@Override
		public void onGroupCollapse(int groupPosition) {
			Toast.makeText(ExpandableListViewDemo.this, 
					ExpandableListViewDemo.this.eAdapter.getGroup(groupPosition).toString() + "-->已被收起", 
					Toast.LENGTH_SHORT).show();
		}
	}
	
	private class OnChildClickListenerImpl implements OnChildClickListener{
		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			Toast.makeText(ExpandableListViewDemo.this, 
					"您点击了：" + ExpandableListViewDemo.this.eAdapter.getChild(groupPosition, childPosition).toString() ,
					Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		ExpandableListView.ExpandableListContextMenuInfo  info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
		int type = ExpandableListView.getPackedPositionType(info.packedPosition);//取得菜单项
		int group = ExpandableListView.getPackedPositionGroup(info.packedPosition); // 取得组项索引
		int child = ExpandableListView.getPackedPositionChild(info.packedPosition); // 取得菜单项索引 
		Toast.makeText(ExpandableListViewDemo.this, 
				"type=" + type + 
				", group="+ExpandableListViewDemo.this.eAdapter.getGroup(group).toString()+
				", child=" + ExpandableListViewDemo.this.eAdapter.getChild(group, child).toString()
				, Toast.LENGTH_SHORT).show();
	}
}
