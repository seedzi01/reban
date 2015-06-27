package com.erban.module.user.center;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.module.user.center.control.UserCenterControl;
import com.erban.view.ShopAdapter;
import com.erban.view.pullrefreshview.PullToRefreshListView;
import com.erban.widget.TitleBar;

public class DiscountActivity extends AbstractActivity<UserCenterControl> {


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
        setupNavItem((ViewGroup)findViewById(R.id.first));
        setupNavItem((ViewGroup)findViewById(R.id.second));
        setupNavItem((ViewGroup)findViewById(R.id.third));
        
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        mListView = mPullToRefreshListView.getRefreshableView();
        mAdapter = new ShopAdapter();
        mListView.setAdapter(mAdapter);
    }
    
    TextView mFirstTitle;
    TextView mSecondTitle;
    TextView mThirdTttle;
    private void setupNavItem(ViewGroup root){
        TextView title = (TextView) root.findViewById(R.id.title);
        title.setCompoundDrawables(null, null, null, null);
        switch (root.getId()) {
        case R.id.first:
            mFirstTitle = title;
            mFirstTitle.setText("已抢购");
            break;
        case R.id.second:
            mSecondTitle = title;
            mFirstTitle.setText("已过期");
            break;
        case R.id.third:
            mThirdTttle = title;
            mFirstTitle.setText("已使用");
            break;
        default:
            break;
        }
    }
    
    
    private void initData(){
        mControl.showFavList();
    }
    
    // ======================================= Callback ============================================= //
    
    public void showFavListCallback(){
        mAdapter.setItems(mControl.getModel().getGoodsList());
        mAdapter.notifyDataSetChanged();
    }
    
    public void showFavListExceptionCallback(){
        
    }
}
