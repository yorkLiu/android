package com.demo.expandablelistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyBaseExpandableAdapter extends BaseExpandableListAdapter {
	
	private Context context = null;
	
	private String[] groups = new String[]{"家人", "亲人", "朋友", "同事", "黑名单"};
	
	private String[][] childrend = new String[][] {
			{"Father","Monther","Daliny","Sister"},
			{"阿初","阿勇","阿能"},
			{"张三","李四","王五","六麻子"},
			{"狗一","猫二","毛四","罗五"},
			{"熊六","张七","小八"}
	};
	
	
	public MyBaseExpandableAdapter(Context context){
		this.context = context;
	}

	@Override
	public int getGroupCount() {
		return this.groups.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.childrend[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.groups[groupPosition];
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.childrend[groupPosition][childPosition];
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView textView = new TextView(this.context);
		textView.setTextSize(14);
		textView.setPadding(40, 10, 10, 10);
		textView.setText(this.getGroup(groupPosition).toString());
		return textView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView textView = this.buildTextView();
		textView.setText(this.getChild(groupPosition, childPosition).toString());
		return textView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	private TextView buildTextView(){
		TextView textView = new TextView(this.context);
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, 30);
		textView.setTextSize(12);
		textView.setPadding(40, 8, 3, 3);
		textView.setLayoutParams(params);
		
		return textView;
	}

}
