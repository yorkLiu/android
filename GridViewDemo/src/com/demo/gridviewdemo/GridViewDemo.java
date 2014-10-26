package com.demo.gridviewdemo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SimpleAdapter;

public class GridViewDemo extends Activity {
	
	private GridView gridView = null;
	SimpleAdapter adapter = null;
	List<Map<String, Integer>> dataList = new ArrayList<Map<String,Integer>>(); 
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.intiAdapter();
		gridView = (GridView)this.findViewById(R.id.gridView);
		
		gridView.setAdapter(this.adapter);
		
		// 添加动画
		gridView.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
		
		gridView.setOnItemClickListener(new OnItemClickListenerImpl());
		
	}
	
	private void intiAdapter(){
		Field[] fields = R.drawable.class.getDeclaredFields();
		for(int i = 0, len = fields.length; i<len; i++){
			Map<String, Integer> map = new HashMap<String, Integer>();
			try {
				if(fields[i].getName().startsWith("pic_")){
					map.put("img", fields[i].getInt(R.drawable.class));
					this.dataList.add(map);
				}
			} catch(Exception e) {
			}
		}
		
		this.adapter = new SimpleAdapter(this, this.dataList,
				R.layout.icon_layout, new String[] { "img" },
				new int[] { R.id.img });
		
	}
	
	private class OnItemClickListenerImpl implements OnItemClickListener{

		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ImageView imgView = new ImageView(GridViewDemo.this);
			imgView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			
			Map<String,Integer> map = (Map<String,Integer>)parent.getItemAtPosition(position);
			imgView.setImageResource(map.get("img"));
			
			Dialog dialog = new AlertDialog.Builder(GridViewDemo.this)
			.setTitle("图片查看")
			.setView(imgView)
			.setNegativeButton("Close", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					 // TODO
				}
			})
			.create();
			
			dialog.show();
		}
	}
}
