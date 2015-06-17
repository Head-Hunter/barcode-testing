package com.sdutlinux.adapter;

import com.sdutlinux.R;

import android.R.raw;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainUIAdapter extends BaseAdapter {

	
	private LayoutInflater inflater;
	private Context context;
	
	public MainUIAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(this.context);
	}
	
	private static final String[] NAMES = new String[] {
		"Скан", "Выход"
	};
	
	private static final int[] ICONS = new int[] {
		R.drawable.scan, R.drawable.exit
	};
	
	@Override
	public int getCount() {
		return NAMES.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}


	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MainViews views;
		View view;
		
		if (convertView == null) {
			views= new MainViews();
			view = inflater.inflate(R.layout.main_item, null);
			views.imageView = (ImageView) view.findViewById(R.id.iv_main_icon);
			views.textView = (TextView) view.findViewById(R.id.tv_main_name);
			
			views.imageView.setImageResource(ICONS[position]);
			views.textView.setText(NAMES[position]);
			
			view.setTag(views);
		} else {
			view = convertView;
			views = (MainViews) convertView.getTag();
			views.imageView = (ImageView) view.findViewById(R.id.iv_main_icon);
			views.textView = (TextView) view.findViewById(R.id.tv_main_name);
            views.imageView.setImageResource(ICONS[position]);
            views.textView.setText(NAMES[position]);
		}
		
		return view;
	}

	private class MainViews {
		ImageView imageView;
		TextView textView;
	}
}
