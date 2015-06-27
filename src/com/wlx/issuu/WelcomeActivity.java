package com.wlx.issuu;

import java.util.ArrayList;
import java.util.List;

import com.mob.tools.gui.ViewPagerAdapter;
import com.wlx.issuu.application.MyApplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class WelcomeActivity extends BaseActivity {
	private ViewPager viewPager;
	private List<View> lists = new ArrayList<View>();
	private ViewPagerAdapter adapter;
	Handler handler = new Handler();
	private int currentPage = 0;
	int TIME = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		lists.add(getLayoutInflater().inflate(R.layout.guide_viewpaper1, null));
		lists.add(getLayoutInflater().inflate(R.layout.guide_viewpaper2, null));
		lists.add(getLayoutInflater().inflate(R.layout.guide_viewpaper3, null));
		adapter = new ViewPagerAdapter(lists);
		viewPager.setAdapter(adapter);
		handler.postDelayed(runnable, TIME);

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				currentPage = arg0;
				// TODO Auto-generated method stub
				// 当滑动时，顶部的imageView是通过animation缓慢的滑动
				switch (arg0) {
				case 0:
					// iv_point1.setBackgroundResource(R.drawable.tip_regist_point_selected);
					// iv_point2.setBackgroundResource(R.drawable.tip_regist_point_unselected);
					break;
				case 1:
					// iv_point1.setBackgroundResource(R.drawable.tip_regist_point_unselected);
					// iv_point2.setBackgroundResource(R.drawable.tip_regist_point_selected);
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// handler自带方法实现定时器
			try {			
				viewPager.setCurrentItem(currentPage);
				if (currentPage <2) {
					currentPage++;
					
				}
				handler.postDelayed(this, TIME);
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	};

	public class ViewPagerAdapter extends PagerAdapter {

		List<View> viewLists;

		public ViewPagerAdapter(List<View> lists) {
			viewLists = lists;
		}

		@Override
		public int getCount() { // 获得size
			// TODO Auto-generated method stub
			return viewLists.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View view, int position, Object object) // 销毁Item
		{
			((ViewPager) view).removeView(viewLists.get(position));
		}

		@Override
		public Object instantiateItem(View view, int position) // 实例化Item
		{
			((ViewPager) view).addView(viewLists.get(position), 0);

			return viewLists.get(position);
		}

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacks(runnable);
	}

}
