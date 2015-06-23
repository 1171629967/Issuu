package com.wlx.issuu;




import com.wlx.issuu.application.MyApplication;

import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends BaseActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		
//		new Handler().postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				if (MyApplication.getInstance().getCurrentUid() != 0) {
//					UIHelper.jumpToHome(SplashActivity.this);
//				} else {
//					UIHelper.jumpToChooseLoginOrRegister(SplashActivity.this);
//				}
//			}
//		}, 2000);
	}

}
