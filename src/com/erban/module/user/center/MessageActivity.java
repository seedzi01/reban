package com.erban.module.user.center;

import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.view.pullrefreshview.PullToRefreshListView;
import com.yuekuapp.BaseActivity;

public class MessageActivity extends AbstractActivity{
    
    public static void startActivity(Activity ac){
        Intent intent = new Intent(ac,MessageActivity.class);
        ac.startActivity(intent);
    }

    private PullToRefreshListView mPullToRefreshListView;
    private ListView mListView;
    
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_layout);
        setupView();
        initData();
    }
    
    private void setupView(){
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        mListView = mPullToRefreshListView.getRefreshableView();
    }

    private void initData(){
        
    }

    
}
