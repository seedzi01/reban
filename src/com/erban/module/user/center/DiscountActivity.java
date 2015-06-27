package com.erban.module.user.center;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater.Filter;
import android.widget.ListView;
import android.widget.TextView;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.module.user.center.control.UserCenterControl;
import com.erban.view.FilterView;
import com.erban.view.ShopAdapter;
import com.erban.view.pullrefreshview.PullToRefreshListView;
import com.erban.widget.TitleBar;

public class DiscountActivity extends AbstractActivity<UserCenterControl> implements View.OnClickListener{


    public static void startActivity(Activity ac){
        Intent intent = new Intent(ac,DiscountActivity.class);
        ac.startActivity(intent);
    }

    private TitleBar mTitlebar;
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mListView;
    private ShopAdapter mAdapter;
    
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_favourable_layout);
        setupView();
        initData();
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
        mTitlebar.setTitle("我的优惠");
        
        //init inavgation
        mFirstTitle = (FilterView) findViewById(R.id.first);
        mSecondTitle = (FilterView) findViewById(R.id.second);
        mThirdTttle = (FilterView) findViewById(R.id.third);
        setupNavItem(mFirstTitle);
        setupNavItem(mSecondTitle);
        setupNavItem(mThirdTttle);
        mFirstTitle.performClick();
        
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        mListView = mPullToRefreshListView.getRefreshableView();
        mAdapter = new ShopAdapter();
        mListView.setAdapter(mAdapter);
    }
    
    FilterView mFirstTitle;
    FilterView mSecondTitle;
    FilterView mThirdTttle;
    private void setupNavItem(FilterView root){
    	root.setRightIconVisiable(false);
        TextView title = (TextView) root.findViewById(R.id.title);
        title.setCompoundDrawables(null, null, null, null);
        switch (root.getId()) {
        case R.id.first:
            mFirstTitle.setTitle("已抢购");
            break;
        case R.id.second:
            mSecondTitle.setTitle("已过期");
            break;
        case R.id.third:
            mThirdTttle.setTitle("已使用");
            break;
        default:
            break;
        }
        root.setOnClickListener(this);
    }
    
    
    private void initData(){
        mControl.showFavList();
    }
    
    @Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.first:
			mFirstTitle.setSelected(true);
			mSecondTitle.setSelected(false);
			mThirdTttle.setSelected(false);
			break;
		case R.id.second:
			mFirstTitle.setSelected(false);
			mSecondTitle.setSelected(true);
			mThirdTttle.setSelected(false);
			break;
		case R.id.third:
			mFirstTitle.setSelected(false);
			mSecondTitle.setSelected(false);
			mThirdTttle.setSelected(true);
			break;
		default:
			break;
		}
	}
    
    // ======================================= Callback ============================================= //
    
    public void showFavListCallback(){
        mAdapter.setItems(mControl.getModel().getGoodsList());
        mAdapter.notifyDataSetChanged();
    }
    
    public void showFavListExceptionCallback(){
        
    }

	
}
