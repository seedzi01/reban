package com.erban.launcher;

import android.os.Bundle;
import android.os.Handler;
import cn.waps.AppConnect;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.container.TabsActivity;

public class LauncherActivity extends AbstractActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher_layout);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				TabsActivity.startActivity(LauncherActivity.this);
				finish();
			}
		}, 3000);
	    AppConnect.getInstance("95e938b2ca353502f3719b08aa7ca94f", "001", this);
	    AppConnect.getInstance(this).initAdInfo();
	}

}
