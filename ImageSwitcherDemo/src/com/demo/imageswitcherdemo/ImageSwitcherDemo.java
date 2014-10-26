package com.demo.imageswitcherdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class ImageSwitcherDemo extends Activity {
	
	private ImageSwitcher imageSwitcher = null;
	private Button prevBtn = null;
	private Button nextBtn = null;
	
	private int images[] = {
		R.drawable.pic_0,R.drawable.pic_1, R.drawable.pic_2,
		R.drawable.pic_3,R.drawable.pic_4
	};
	private int index = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		imageSwitcher = (ImageSwitcher)this.findViewById(R.id.imageSwitcher);
		prevBtn = (Button)this.findViewById(R.id.prevBtn);
		nextBtn = (Button)this.findViewById(R.id.nextBtn);
		
		prevBtn.setOnClickListener(new OnPrevClickListener());
		nextBtn.setOnClickListener(new OnNextClickListener());
		
		//设置ViewFactory
		imageSwitcher.setFactory(new ViewFactoryImpl());
		//设置默认的显示图片
		imageSwitcher.setImageResource(this.images[index]);
		
		//设置切换图片动画
		//android.R.anim.fade_in
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
		//android.R.anim.fade_out
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
	}
	
	private void buttonFlag(){
		this.prevBtn.setEnabled(this.index != 0);
		this.nextBtn.setEnabled(this.index != (this.images.length-1));
	}
	
	private class OnPrevClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			imageSwitcher.setImageResource(ImageSwitcherDemo.this.images[--ImageSwitcherDemo.this.index]);
			ImageSwitcherDemo.this.buttonFlag();
		}
	}
	
	private class OnNextClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			imageSwitcher.setImageResource(ImageSwitcherDemo.this.images[++ImageSwitcherDemo.this.index]);
			ImageSwitcherDemo.this.buttonFlag();
		}
	}
	
	private class ViewFactoryImpl implements ViewFactory{
		@Override
		public View makeView() {
			ImageView img = new ImageView(ImageSwitcherDemo.this);
			// 默认显示第一张图片
			img.setImageResource(ImageSwitcherDemo.this.images[0]);
			// 设置背景颜色
			img.setBackgroundColor(0xFFFFFF);
			// 设置图片的对齐方式 
			img.setScaleType(ImageView.ScaleType.CENTER);
			return img;
		}
	}
}
