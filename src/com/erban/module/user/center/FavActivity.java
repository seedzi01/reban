package com.erban.module.user.center;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.erban.R;
import com.erban.view.pullrefreshview.PullToRefreshListView;
import com.erban.widget.TitleBar;
import com.yuekuapp.BaseActivity;

public class FavActivity extends BaseActivity{
	
	public static void startActivity(Activity ac){
		Intent intent = new Intent(ac,FavActivity.class);
		ac.startActivity(intent);
	}

	private TitleBar mTitlebar;
	private PullToRefreshListView mPullToRefreshListView;
	private ListView mListView;
	
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_layout);
        setupView();
    };
    
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
    	mTitlebar.setTitle("我的收藏");
    	
    	mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
    	mListView = mPullToRefreshListView.getRefreshableView();
    	
    }
    
    private class FavAdapter extends BaseAdapter{
    	
    	public FavAdapter(){
    	}
    	
		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return null;
		}
    	
    }
}
