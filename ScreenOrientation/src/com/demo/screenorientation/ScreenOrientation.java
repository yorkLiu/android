package com.demo.screenorientation;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ScreenOrientation extends Activity {

	private Button changeBtn = null;
	private ImageView img = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		changeBtn = (Button)this.findViewById(R.id.changeBtn);
		img = (ImageView)this.findViewById(R.id.img);
		
		changeBtn.setOnClickListener(new ClickListenerHandler());
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){ // 当前为竖屏
			ScreenOrientation.this.changeBtn.setText("change to 横屏显示(当前为竖屏显示)");
			ScreenOrientation.this.img.setImageResource(R.drawable.portraint);
		}else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){ // 当前为横屏
			ScreenOrientation.this.changeBtn.setText("change to 竖屏显示(当前为横屏显示)");
			ScreenOrientation.this.img.setImageResource(R.drawable.landscape);	
		}
		
		super.onConfigurationChanged(newConfig);
	}
	
	
	private class ClickListenerHandler implements OnClickListener{
		@Override
		public void onClick(View v) {
			int requestOrientation = ScreenOrientation.this.getRequestedOrientation();
			if(requestOrientation == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED){ // 不支持改变显示方向
				ScreenOrientation.this.changeBtn.setText("Error: 不支持改变显示方向");
			} else{
				if(requestOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){ //当前为竖屏显示
					ScreenOrientation.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 变为横屏显示
					
				}else if(requestOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){//当前为横屏显示
					ScreenOrientation.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 变为竖屏显示
				}
			}
		}
	}
}
