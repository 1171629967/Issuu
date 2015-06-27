package com.wlx.issuu;

import java.util.HashMap;

import com.mob.tools.utils.UIHandler;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements OnClickListener,
		PlatformActionListener, Callback {
	private TextView tv_weibo;
	private TextView tv_qq;
	private TextView tv_weixin;
	private TextView tv_douban;

	private EditText et_userName;
	private EditText et_password;
	private TextView tv_forget;
	private TextView tv_regist;
	private TextView tv_suibian;

	private static final int MSG_TOAST = 1;
	private static final int MSG_ACTION_CCALLBACK = 2;
	private static final int MSG_CANCEL_NOTIFY = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ShareSDK.initSDK(this);
		initViews();
	}

	private void initViews() {
		tv_weibo = (TextView) findViewById(R.id.tv_weibo);
		tv_qq = (TextView) findViewById(R.id.tv_qq);
		tv_weixin = (TextView) findViewById(R.id.tv_weixin);
		tv_douban = (TextView) findViewById(R.id.tv_douban);
		et_userName = (EditText) findViewById(R.id.et_userName);
		et_password = (EditText) findViewById(R.id.et_password);
		tv_forget = (TextView) findViewById(R.id.tv_forget);
		tv_regist = (TextView) findViewById(R.id.tv_regist);
		tv_suibian = (TextView) findViewById(R.id.tv_suibian);

		tv_weibo.setOnClickListener(this);
		tv_qq.setOnClickListener(this);
		tv_weixin.setOnClickListener(this);
		tv_douban.setOnClickListener(this);
		tv_forget.setOnClickListener(this);
		tv_regist.setOnClickListener(this);
		tv_suibian.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == tv_weibo) {

		} else if (v == tv_qq) {
			// 初始化QQ平台,这两种方式都可以
			// First,Platform pf = ShareSDK.getPlatform(MainActivity.this,
			// SinaWeibo.NAME);
			QQ pf = new QQ(LoginActivity.this);
			// 关闭SSO授权，用网页授权代替；true-关闭；flase，开启
			// Closing SSO authorize, using web authorize replace. True is not
			// sso.
			// If you want to SSO, your app's md5 must be equal with the md5 on
			// SinaWeibo Development Platform
			// pf.SSOSetting(true);
			// //设置监听
			// //Setting authorization listener
			// pf.setPlatformActionListener(LoginActivity.this);
			// //执行授权
			// //Perform authorization
			// pf.authorize();
			authorize(pf);
		} else if (v == tv_weixin) {

		} else if (v == tv_douban) {

		} else if (v == tv_forget) {

		} else if (v == tv_regist) {

		} else if (v == tv_suibian) {

		}
	}

	// 设置监听http://sharesdk.cn/androidDoc/cn/sharesdk/framework/PlatformActionListener.html
	// 监听是子线程，不能Toast，要用handler处理，不要犯这么二的错误
	// Setting listener,
	// http://sharesdk.cn/androidDoc/cn/sharesdk/framework/PlatformActionListener.html
	// The listener is the child-thread that can not handle ui
	@Override
	public void onCancel(Platform platform, int action) {
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 3;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg, this);
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case MSG_TOAST: {
			String text = String.valueOf(msg.obj);
			Toast.makeText(LoginActivity.this, text, Toast.LENGTH_SHORT).show();
		}
			break;
		case MSG_ACTION_CCALLBACK: {
			switch (msg.arg1) {
			case 1: { // 成功, successful notification
				showNotification(2000, "Auth successfully");

				// 授权成功后,获取用户信息，要自己解析，看看oncomplete里面的注释
				// ShareSDK只保存以下这几个通用值
				Platform pf = ShareSDK.getPlatform(LoginActivity.this, QQ.NAME);
				System.out.println("sharesdk use_id---->"
						+ pf.getDb().getUserId());
				System.out.println("sharesdk use_name---->"
						+ pf.getDb().getUserName());
				System.out.println("sharesdk use_icon---->"
						+ pf.getDb().getUserIcon());
				Log.e("sharesdk use_id", pf.getDb().getUserId()); // 获取用户id
				Log.e("sharesdk use_name", pf.getDb().getUserName());// 获取用户名称
				Log.e("sharesdk use_icon", pf.getDb().getUserIcon());// 获取用户头像
				// pf.author()这个方法每一次都会调用授权，出现授权界面
				// 如果要删除授权信息，重新授权
				// pf.getDb().removeAccount();
				// 调用后，用户就得重新授权，否则下一次就不用授权

				/*
				 * ShareSDK provide some methods to get some normal userinfo,for
				 * example Platform pf = ShareSDK.getPlatform(MainActivity.this,
				 * SinaWeibo.NAME); Log.e("sharesdk use_id",
				 * pf.getDb().getUserId()); //get user id
				 * Log.e("sharesdk use_name", pf.getDb().getUserName());//get
				 * user name Log.e("sharesdk use_icon",
				 * pf.getDb().getUserIcon());//get user icon
				 * 
				 * pf.isValid(),this method is used to determine the weibo
				 * whether is authorized or not. But isValid this method is used
				 * to detemine these platform of Wechat\QQ\Google+\Instragram
				 * has Client. If you want to remove authorization, you can use
				 * the method of pf.getDB().removeAccount()
				 */
			}
				break;
			case 2: { // 失败, fail notification
				String expName = msg.obj.getClass().getSimpleName();
				if ("WechatClientNotExistException".equals(expName)
						|| "WechatTimelineNotSupportedException"
								.equals(expName)) {
					showNotification(2000,
							getString(R.string.wechat_client_inavailable));
				} else if ("GooglePlusClientNotExistException".equals(expName)) {
					showNotification(2000,
							getString(R.string.google_plus_client_inavailable));
				} else if ("QQClientNotExistException".equals(expName)) {
					showNotification(2000,
							getString(R.string.qq_client_inavailable));
				} else {
					showNotification(2000, "Auth unsuccessfully");
				}
			}
				break;
			case 3: { // 取消, cancel notification
				showNotification(2000, "Cancel authorization");
			}
				break;
			}
		}
			break;
		case MSG_CANCEL_NOTIFY: {
			NotificationManager nm = (NotificationManager) msg.obj;
			if (nm != null) {
				nm.cancel(msg.arg1);
			}
		}
			break;
		}
		return false;
	}

	@Override
	public void onComplete(Platform platform, int action,
			HashMap<String, Object> arg2) {
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 1;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg, this);

	}

	@Override
	public void onError(Platform platform, int action, Throwable t) {
		t.printStackTrace();
		t.getMessage();

		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 2;
		msg.arg2 = action;
		msg.obj = t;
		UIHandler.sendMessage(msg, this);
	}

	// 在状态栏提示分享操作,the notification on the status bar
	private void showNotification(long cancelTime, String text) {
		try {
			Context app = getApplicationContext();
			NotificationManager nm = (NotificationManager) app
					.getSystemService(Context.NOTIFICATION_SERVICE);
			final int id = Integer.MAX_VALUE / 13 + 1;
			nm.cancel(id);

			long when = System.currentTimeMillis();
			Notification notification = new Notification(
					R.drawable.ic_launcher, text, when);
			PendingIntent pi = PendingIntent.getActivity(app, 0, new Intent(),
					0);
			notification.setLatestEventInfo(app, "sharesdk test", text, pi);
			notification.flags = Notification.FLAG_AUTO_CANCEL;
			nm.notify(id, notification);

			if (cancelTime > 0) {
				Message msg = new Message();
				msg.what = MSG_CANCEL_NOTIFY;
				msg.obj = nm;
				msg.arg1 = id;
				UIHandler.sendMessageDelayed(msg, cancelTime, this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void authorize(Platform plat) {
		if (plat == null) {
			Toast.makeText(LoginActivity.this, "plat为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		// 判断指定平台是否已经完成授权
		if (plat.isValid()) {
			String userId = plat.getDb().getUserId();
			if (userId != null) {
				Toast.makeText(LoginActivity.this, "MSG_USERID_FOUND",
						Toast.LENGTH_SHORT).show();
				// UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
				// login(plat.getName(), userId, null);
				return;
			}
		}
		plat.setPlatformActionListener(this);
		// true不使用SSO授权，false使用SSO授权
		plat.SSOSetting(true);
		// 获取用户资料
		plat.showUser(null);
	}

}
