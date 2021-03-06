package com.example.activitygroup;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

public class ActivityGroupDemo extends ActivityGroup {

	private GridView bottomToolBar;
	private LinearLayout conteLayout;
	private MenuImageAdapter imageAdapter;
	private int[] menu_imgs = new int[] { R.drawable.menu_main,
			R.drawable.menu_news, R.drawable.menu_sms, R.drawable.menu_more,
			R.drawable.menu_exit };
	private int menuIconWidth = 0;
	private int menuIconHeight = 0;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置此页面没有title
		super.setContentView(R.layout.main);

		this.conteLayout = (LinearLayout) this.findViewById(R.id.content);
		this.bottomToolBar = (GridView) this.findViewById(R.id.bottomToolbar);
		//---------------bottom tool 的设置------------------------------------
		this.bottomToolBar.setGravity(Gravity.CENTER);
		this.bottomToolBar.setSelector(new ColorDrawable(Color.TRANSPARENT));
		this.bottomToolBar.setBackgroundColor(Color.DKGRAY);
		this.bottomToolBar.setNumColumns(this.menu_imgs.length);
		this.bottomToolBar.setVerticalSpacing(0);
		//!-------------------------------------------------------------------

		// -------------计算第一个icon的宽度, 高度-------------------------------
		this.menuIconWidth = this.getWindowManager().getDefaultDisplay()
				.getWidth() / menu_imgs.length;
		this.menuIconHeight = this.getWindowManager().getDefaultDisplay()
				.getHeight() / 8;
		//!-------------------------------------------------------------------

		this.imageAdapter = new MenuImageAdapter(this, this.menu_imgs,
				this.menuIconWidth, this.menuIconHeight,
				R.drawable.menu_selected);
		
		this.bottomToolBar.setAdapter(this.imageAdapter);

		//默认显示 "首页"
		this.swichActivity(0);
		
		 // 添加单击选中选项事件
		this.bottomToolBar.setOnItemClickListener(new OnBottomToobarClickListenerImpl());
	}
	
	private class OnBottomToobarClickListenerImpl implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			
			ActivityGroupDemo.this.swichActivity(position);
		}
	}
	
	/**
	 * @param activityIdx
	 */
	private void swichActivity(int activityIdx){
		this.imageAdapter.setFocus(activityIdx);
		this.conteLayout.removeAllViews();
		switch (activityIdx) {
		case 0:
			this.intent = new Intent(this, MyContentActivity.class);
			break;
		case 1:
			this.intent = new Intent(this, MyContentActivity.class);
			break;
		case 2:
			this.intent = new Intent(this, MyContentActivity.class);
			break;
		case 3:
			this.intent = new Intent(this, MyContentActivity.class);
			break;
		case 4:
			exitDialog();
		}
		
		this.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		Window subActivity = this.getLocalActivityManager().startActivity(
				"subActivity", this.intent);						
		this.conteLayout.addView(subActivity.getDecorView(), LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT); 
	}

	private void exitDialog(){
		Dialog dialog = new AlertDialog.Builder(this)
		.setTitle("退出程序")
		.setMessage("你确定你要退出该程序吗?")
		.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ActivityGroupDemo.this.finish();
			}
		}).setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ActivityGroupDemo.this.swichActivity(0);
			}
		}).create();
		dialog.show();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			this.exitDialog();
		}
		return false;
	}
	
	
}
