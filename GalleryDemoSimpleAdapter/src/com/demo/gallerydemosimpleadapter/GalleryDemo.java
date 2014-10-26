package com.demo.gallerydemosimpleadapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ViewSwitcher.ViewFactory;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class GalleryDemo extends Activity {
	
	private Gallery gallery = null;
	private ImageSwitcher imageSwitcher = null;
	SimpleAdapter simpleAdapter = null;
	List<Map<String, Integer>> list = new ArrayList<Map<String,Integer>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		gallery = (Gallery) this.findViewById(R.id.gallery);
		imageSwitcher = (ImageSwitcher)this.findViewById(R.id.imageSwitcher);
		
		// 初如始化SimpleAdapter
		this.initAdapter();
		// 设置Adapter
		gallery.setAdapter(this.simpleAdapter);
		gallery.setSelection(0, true);
		
		///////////////////
		imageSwitcher.setFactory(new ImageSwitcherViewFactoryImpl());
		imageSwitcher.setImageResource(this.list.get(0).get(list.get(0).keySet().iterator().next()));
		
		// 给gallery添加事件
		gallery.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
		
	}
	 
	/**
	 * 使用Java的反射机制来实现加载@drawable-hdpi里面的图片
	 */
	private void initAdapter() {
		Field[] fields = R.drawable.class.getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			if (fields[i].getName().startsWith("pic_")) {
				Map<String, Integer> map = new HashMap<String, Integer>(1);
				try {
					Log.i(fields[i].getName(),
							"" + fields[i].getInt(R.drawable.class));
					map.put("img", fields[i].getInt(R.drawable.class));
				} catch (Exception e) {
				}
				this.list.add(map);
			}
		}
		
		System.out.println("list size:" + this.list.size());
		this.simpleAdapter = new SimpleAdapter(this, this.list, 
				R.layout.gallery_icon, new String[]{"img"}, new int[]{R.id.img});
	}
	
	private class OnItemSelectedListenerImpl implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			
			Map<String, Integer> imgMap = (Map<String, Integer>)GalleryDemo.this.gallery.getItemAtPosition(position);
			GalleryDemo.this.imageSwitcher.setImageResource(imgMap.get("img"));
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	}

	private class ImageSwitcherViewFactoryImpl implements ViewFactory{
		@Override
		public View makeView() {
			ImageView img = new ImageView(GalleryDemo.this);
			img.setScaleType(ImageView.ScaleType.CENTER);
			return img;
		}
	}
}
