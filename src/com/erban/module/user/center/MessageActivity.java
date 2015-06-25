package com.erban.module.user.center;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.module.user.center.control.UserCenterControl;
import com.erban.view.pullrefreshview.PullToRefreshListView;
import com.erban.widget.TitleBar;

public class MessageActivity extends AbstractActivity<UserCenterControl>{
    
    public static void startActivity(Activity ac){
        Intent intent = new Intent(ac,MessageActivity.class);
        ac.startActivity(intent);
    }

    private TitleBar mTitlebar;
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mListView;
    
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_layout);
        setupView();
        initData();
    }
    
    private void setupView(){
    	mTitlebar = (TitleBar) findViewById(R.id.titlebar);
    	mTitlebar.setBackgroundColor(Color.parseColor("#28b937"));
    	mTitlebar.findViewById(R.id.right_root).setVisibility(View.GONE);
    	mTitlebar.findViewById(R.id.back).setVisibility(View.VISIBLE);
    	mTitlebar.setBackListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    	mTitlebar.setTitle("我的消息");
    	
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        mListView = mPullToRefreshListView.getRefreshableView();
    }

    private void initData(){
    	mControl.showUserNotice();
    }

  // ======================================= Callback ============================================= //
    public void showUserNoticeCallback(){
    	
    }
    
    public void showUserNoticeExceptionCallback(){
    	
    }
}
