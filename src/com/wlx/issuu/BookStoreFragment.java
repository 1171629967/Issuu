package com.wlx.issuu;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.wlx.issuu.adapter.BookClassifyAdapter;
import com.wlx.issuu.adapter.CloseReadBookAdapter;
import com.wlx.issuu.model.BookClassify;
import com.wlx.issuu.model.CloseReadBook;
import com.wlx.issuu.widget.MyActionBar;

public class BookStoreFragment extends BaseFragment implements OnItemClickListener{
	private Gallery gallery_classify;
	private Gallery gallery_closeRead;
	private BookClassifyAdapter bookClassifyAdapter;
	private CloseReadBookAdapter closeReadBookAdapter;
	private ArrayList<BookClassify> bookClassifies;
	private ArrayList<CloseReadBook> closeReadBooks;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_bookstore, null);
		
		this.initView(view);
		return view;
	}

	private void initView(View view) {
		gallery_classify = (Gallery) view.findViewById(R.id.gallery_classify);
		gallery_closeRead = (Gallery) view.findViewById(R.id.gallery_closeRead);
		
		bookClassifies = new ArrayList<BookClassify>();
		closeReadBooks = new ArrayList<CloseReadBook>();
		
		BookClassify bookClassify1 = new BookClassify();
		BookClassify bookClassify2 = new BookClassify();
		BookClassify bookClassify4 = new BookClassify();
		BookClassify bookClassify5 = new BookClassify();
		bookClassify1.setImageUrl("http://d.hiphotos.baidu.com/image/w%3D400/sign=5a808b8f6e81800a6ee5880e813433d6/a5c27d1ed21b0ef4900dea5edfc451da80cb3ecc.jpg");
		bookClassify2.setImageUrl("http://e.hiphotos.baidu.com/image/w%3D400/sign=b056ed995cdf8db1bc2e7d643923dddb/bba1cd11728b4710552411cdc1cec3fdfc0323ac.jpg");
		bookClassify4.setImageUrl("http://b.hiphotos.baidu.com/image/w%3D400/sign=e2288abba41ea8d38a227504a70b30cf/50da81cb39dbb6fd83b4d8260b24ab18962b37c6.jpg");
		bookClassify5.setImageUrl("http://g.hiphotos.baidu.com/image/w%3D400/sign=77706a22b11bb0518f24b228067ada77/09fa513d269759eee36a06cbb0fb43166d22dffc.jpg");
		bookClassifies.add(bookClassify1);
		bookClassifies.add(bookClassify2);
		bookClassifies.add(bookClassify4);
		bookClassifies.add(bookClassify5);
		
		CloseReadBook closeReadBook1 = new CloseReadBook();
		CloseReadBook closeReadBook2 = new CloseReadBook();
		closeReadBook1.setBookImageUrl("http://d.hiphotos.baidu.com/image/w%3D400/sign=5a808b8f6e81800a6ee5880e813433d6/a5c27d1ed21b0ef4900dea5edfc451da80cb3ecc.jpg");
		closeReadBook2.setBookImageUrl("http://e.hiphotos.baidu.com/image/w%3D400/sign=b056ed995cdf8db1bc2e7d643923dddb/bba1cd11728b4710552411cdc1cec3fdfc0323ac.jpg");
		closeReadBooks.add(closeReadBook1);
		closeReadBooks.add(closeReadBook2);
		
		
		
		
		bookClassifyAdapter = new BookClassifyAdapter(getActivity(), bookClassifies);
		closeReadBookAdapter = new CloseReadBookAdapter(getActivity(), closeReadBooks);
		gallery_classify.setAdapter(bookClassifyAdapter);
		gallery_closeRead.setAdapter(closeReadBookAdapter);
		gallery_classify.setOnItemClickListener(this);
		gallery_closeRead.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (parent == gallery_classify) {
			Toast.makeText(getActivity(), position+"", Toast.LENGTH_SHORT).show();
		}
		else if(parent == gallery_closeRead){
			Toast.makeText(getActivity(), "最近阅读+"+position, Toast.LENGTH_SHORT).show();
		}
		
	}

	
}
