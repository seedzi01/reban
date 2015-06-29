package com.erban.launcher;

import android.os.Bundle;
import android.os.Handler;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.container.TabsActivity;

public class LauncherActivity extends AbstractActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher_layout);
//		new Handler().postDelayed(new Runnable() {
//			
//			@Override
//			public void run() {
//				TabsActivity.startActivity(LauncherActivity.this);
//			}
//		}, 3000);
	}

}
