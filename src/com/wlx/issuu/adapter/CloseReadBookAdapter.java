package com.wlx.issuu.adapter;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;

import com.wlx.issuu.R;
import com.wlx.issuu.model.BookClassify;
import com.wlx.issuu.model.CloseReadBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CloseReadBookAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<CloseReadBook> closeReadBooks;
	private FinalBitmap finalBitmap;
	
	public CloseReadBookAdapter(Context context,ArrayList<CloseReadBook> closeReadBooks){
		this.context = context;
		this.closeReadBooks = closeReadBooks;
		finalBitmap = FinalBitmap.create(context);
	}

	@Override
	public int getCount() {
		return closeReadBooks.size();
	}

	@Override
	public Object getItem(int position) {
		return closeReadBooks.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.adapter_book_classify, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		CloseReadBook closeReadBooks = (CloseReadBook) getItem(position);
		finalBitmap.display(holder.iv, closeReadBooks.getBookImageUrl());
		
		return convertView;
	}
	
	private class ViewHolder {
		ImageView iv;
	}

}
