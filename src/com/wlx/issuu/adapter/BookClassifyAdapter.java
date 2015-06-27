package com.wlx.issuu.adapter;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;

import com.wlx.issuu.R;
import com.wlx.issuu.model.BookClassify;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookClassifyAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<BookClassify> bookClassifies;
	private FinalBitmap finalBitmap;
	
	public BookClassifyAdapter(Context context,ArrayList<BookClassify> bookClassifies){
		this.context = context;
		this.bookClassifies = bookClassifies;
		finalBitmap = FinalBitmap.create(context);
	}

	@Override
	public int getCount() {
		return bookClassifies.size();
	}

	@Override
	public Object getItem(int position) {
		return bookClassifies.get(position);
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
		
		BookClassify bookClassify = (BookClassify) getItem(position);
		finalBitmap.display(holder.iv, bookClassify.getImageUrl());
		
		return convertView;
	}
	
	private class ViewHolder {
		ImageView iv;
	}

}
