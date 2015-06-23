/*   
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
/**
 * 
 */
package com.wlx.issuu.application;

import java.io.File;

import com.wlx.issuu.model.UserInfo;

import android.app.Application;
import android.content.Context;


/**
 * This class is used for 自定义的应用程序全局变量
 * 
 * @author tyn
 * @version 1.0, 2014-9-8 上午10:28:07
 */

public class MyApplication extends Application {
	private static MyApplication globalContext = null;
	/** 当前登录用户的个人信息 */
	private UserInfo userInfo;
	

	@Override
	public void onCreate() {
		super.onCreate();
		globalContext = this;
		
		
	}

	public static MyApplication getInstance() {
		return globalContext;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	

	
}
