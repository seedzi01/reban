package com.erban;

import com.erban.module.user.LoginAndRegisterActivity;
import com.erban.widget.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class WebViewActivity extends AbstractActivity {
	
	public static void startActivity(Activity ac){
		Intent intent = new Intent(ac,WebViewActivity.class);
		ac.startActivity(intent);
	}
	
	TitleBar mTitleBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_web_layout);
		
		mTitleBar = (TitleBar) findViewById(R.id.title_bar_layout);
		mTitleBar.setBackgroundColor(Color.parseColor("#28b937"));
		mTitleBar.setTitle("用户协议");
		mTitleBar.setBackListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		findViewById(R.id.right_root).setVisibility(View.GONE);
		
		WebView webView = (WebView) findViewById(R.id.webview);
		webView.loadUrl("http://sunzhenyang.yuxiedu.org.cn/user/userAgreement.html");
	}
}
