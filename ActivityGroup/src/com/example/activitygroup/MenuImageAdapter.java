package com.example.activitygroup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MenuImageAdapter extends BaseAdapter {
	private Context context;
	private ImageView[] menuImg;
	private int selectedMenuImg;

	/**
	 * @param context
	 * @param imgIds
	 * @param with
	 * @param heiht
	 * @param selectedMenuImg
	 */
	public MenuImageAdapter(Context context, int[] imgIds, int with, int heiht,
			int selectedMenuImg) {
		this.context = context;
		this.selectedMenuImg = selectedMenuImg;
		this.menuImg = new ImageView[imgIds.length];
		for (int i = 0; i < imgIds.length; i++) {
			this.menuImg[i] = new ImageView(this.context);
			this.menuImg[i].setLayoutParams(new GridView.LayoutParams(with,
					heiht));
			this.menuImg[i].setAdjustViewBounds(false);
			this.menuImg[i].setPadding(3, 3, 3, 3);
			this.menuImg[i].setImageResource(imgIds[i]);
		}
	}

	@Override
	public int getCount() {
		return this.menuImg.length;
	}

	@Override
	public Object getItem(int position) {
		if (position >= 0 && position < this.menuImg.length) {
			return this.menuImg[position];
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = null;
		if (convertView == null) {
			imageView = this.menuImg[position];
		} else {
			imageView = (ImageView) convertView;
		}
		return imageView;
	}

	/**
	 * @param selectedId
	 */
	public void setFocus(int selectedId) {
		for (int i = 0; i < this.menuImg.length; i++) {
			if (selectedId == i) {
				this.menuImg[i].setBackgroundResource(this.selectedMenuImg);
			} else {
				this.menuImg[i].setBackgroundResource(0);
			}
		}
	}

}
