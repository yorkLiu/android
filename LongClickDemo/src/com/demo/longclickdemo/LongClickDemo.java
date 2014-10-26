package com.demo.longclickdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class LongClickDemo extends Activity {
	
	private TextView msg = null;
	private ImageView img = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		msg = (TextView)this.findViewById(R.id.msg);
		img = (ImageView)this.findViewById(R.id.img);
		
		img.setOnLongClickListener(new OnLongClickListenerImpl());
	}
	
	private class OnLongClickListenerImpl implements OnLongClickListener{

		@Override
		public boolean onLongClick(View v) {
			
			try {
				LongClickDemo.this.clearWallpaper(); // 清除原有的桌面图片
				// 设置新的桌面图片
				LongClickDemo.this.setWallpaper(LongClickDemo.this.img.getResources().openRawResource(R.drawable.wall01));
				
				LongClickDemo.this.msg.setText("背景图片设置成功");
			}  catch (Exception e) {
				LongClickDemo.this.msg.setText("背景图片设置失败");
				e.printStackTrace();
				e.printStackTrace();
			}
			return false;
		}
	}
}
