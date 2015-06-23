package com.wlx.issuu;

import java.util.List;

import android.R.color;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wlx.issuu.BaseFragment.menuClicklistener;

public class MainActivity extends FragmentActivity implements OnClickListener,
		menuClicklistener {
	public SlidingMenu menu;
	private RelativeLayout rl_myBookRack, rl_bookStore, rl_myInfo, rl_myShopCart,
			rl_news, rl_collect, rl_setting, rl_aboutUs;

	public static final String MY_BOOK_RACK = "menu_myBookRack";
	public static final String BOOK_STORE = "menu_BookStore";
	public static final String MY_INFO = "menu_myInfo";
	public static final String MY_SHOP_CART = "menu_myShopCraft";
	public static final String NEWS = "menu_news";
	public static final String COLLECT = "menu_collect";
	public static final String SETTING = "menu_setting";
	public static final String ABOUT_US = "menu_aboutUs";
	private String currentFragment;

	long waitTime = 2000;
	long touchTime = 0;

	private LinearLayout ll;

	private FrameLayout fl_fragments;

	

	public final static String ACTION_FINISH_MAIN_ACTIVITY = "com.wlx.issuu.action_finish_main_activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.registerBoradcastReceiver();
		this.initSlidingMenu();
		this.initView();

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fl_fragments, new BookStoreFragment()).commit();
		currentFragment = BOOK_STORE;

	}

	private void initView() {

		fl_fragments = (FrameLayout) findViewById(R.id.fl_fragments);

		rl_myBookRack = (RelativeLayout) menu.findViewById(R.id.rl_myBookRack);
		rl_bookStore = (RelativeLayout) menu.findViewById(R.id.rl_bookStore);
		rl_myInfo = (RelativeLayout) menu.findViewById(R.id.rl_myInfo);
		rl_myShopCart = (RelativeLayout) menu.findViewById(R.id.rl_myShopCart);
		rl_news = (RelativeLayout) menu.findViewById(R.id.rl_news);
		rl_collect = (RelativeLayout) menu.findViewById(R.id.rl_collect);
		rl_setting = (RelativeLayout) menu.findViewById(R.id.rl_setting);
		rl_aboutUs = (RelativeLayout) menu.findViewById(R.id.rl_aboutUs);

		rl_myBookRack.setOnClickListener(this);
		rl_bookStore.setOnClickListener(this);
		rl_myInfo.setOnClickListener(this);
		rl_myShopCart.setOnClickListener(this);
		rl_news.setOnClickListener(this);
		rl_collect.setOnClickListener(this);
		rl_setting.setOnClickListener(this);
		rl_aboutUs.setOnClickListener(this);

	}

	private void initSlidingMenu() {
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		// menu.setShadowWidthRes(R.dimen.shadow_width);
		// menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.slidingmenu);
	}

	@Override
	public void onClick(View v) {

		if (v == rl_myBookRack && !currentFragment.equals(MY_BOOK_RACK)) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fl_fragments, new MyBookRackFragment())
					.commit();
			currentFragment = MY_BOOK_RACK;
		} else if (v == rl_bookStore && !currentFragment.equals(BOOK_STORE)) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fl_fragments, new BookStoreFragment())
					.commit();
			currentFragment = BOOK_STORE;
		} else if (v == rl_myInfo && !currentFragment.equals(MY_INFO)) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fl_fragments, new MyInfoFragment()).commit();
			currentFragment = MY_INFO;
		} else if (v == rl_myShopCart && !currentFragment.equals(MY_SHOP_CART)) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fl_fragments, new MyShopCraftFragment())
					.commit();
			currentFragment = MY_SHOP_CART;
		} else if (v == rl_news && !currentFragment.equals(NEWS)) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fl_fragments, new NewsFragment()).commit();
			currentFragment = NEWS;
		}
		else if (v == rl_collect && !currentFragment.equals(COLLECT)) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fl_fragments, new CollectFragment()).commit();
			currentFragment = COLLECT;
		} else if (v == rl_setting && !currentFragment.equals(SETTING)) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fl_fragments, new SettingFragment()).commit();
			currentFragment = SETTING;
		} else if (v == rl_aboutUs && !currentFragment.equals(ABOUT_US)) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fl_fragments, new AboutUsFragment()).commit();
			currentFragment = ABOUT_US;
		}

		menu.toggle(true);

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub

		super.onStop();
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void menuClick() {
		menu.toggle(true);
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(mBroadcastReceiver);
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			if (menu.isMenuShowing()) {
				finish();
			} else {
				menu.toggle(true);
			}
			return true;
		} else if (event.getAction() == KeyEvent.KEYCODE_MENU) {
			menu.toggle(true);
		}

		return super.onKeyDown(keyCode, event);
	}

	public void registerBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction(ACTION_FINISH_MAIN_ACTIVITY);
		// 注册广播
		registerReceiver(mBroadcastReceiver, myIntentFilter);
	}

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(ACTION_FINISH_MAIN_ACTIVITY)) {
				finish();
			}
		}
	};

}
