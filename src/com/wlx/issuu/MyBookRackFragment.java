package com.wlx.issuu;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wlx.issuu.widget.MyActionBar;

public class MyBookRackFragment extends BaseFragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mybookract, null);
		
		this.initView(view);
		return view;
	}

	private void initView(View view) {
		
	}

	

}
