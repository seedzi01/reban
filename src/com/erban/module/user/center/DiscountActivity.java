package com.erban.module.user.center;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater.Filter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.bean.SaleCount;
import com.erban.module.user.center.control.UserCenterControl;
import com.erban.view.DiscountAdapter;
import com.erban.view.FilterView;
import com.erban.view.ShopAdapter;
import com.erban.view.pullrefreshview.PullToRefreshBase.OnRefreshListener;
import com.erban.view.pullrefreshview.PullToRefreshListView;
import com.erban.widget.TitleBar;

public class DiscountActivity extends AbstractActivity<UserCenterControl> 
	implements View.OnClickListener,OnRefreshListener{


    public static void startActivity(Activity ac){
        Intent intent = new Intent(ac,DiscountActivity.class);
        ac.startActivity(intent);
    }

    private TitleBar mTitlebar;
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mListView;
    private DiscountAdapter mAdapter;
    
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
        
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        mPullToRefreshListView.setPullToRefreshEnabled(false);
        mListView = mPullToRefreshListView.getRefreshableView();
        mAdapter = new DiscountAdapter();
        mAdapter.setListener(this);
        mListView.setAdapter(mAdapter);
        
        mFirstTitle.performClick();
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
    	getSaleCount();
//        mControl.showFavList();
    }
    
    @Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.first:
			mFirstTitle.setSelected(true);
			mSecondTitle.setSelected(false);
			mThirdTttle.setSelected(false);
            mAdapter.mCurrentPage = DiscountAdapter.LI_JI_SHI_YONG;
			mControl.getMySaleAsyn("gets", "1");
			break;
		case R.id.second:
			mFirstTitle.setSelected(false);
			mSecondTitle.setSelected(true);
			mThirdTttle.setSelected(false);
			mAdapter.mCurrentPage = DiscountAdapter.GUO_QI_SHI_XIAO;
			mControl.getMySaleAsyn("expire", "1");
			break;
		case R.id.third:
			mFirstTitle.setSelected(false);
			mSecondTitle.setSelected(false);
			mThirdTttle.setSelected(true);
			mAdapter.mCurrentPage = DiscountAdapter.YI_SHI_YONG;
			mControl.getMySaleAsyn("used", "1");
			break;
		case R.id.bt:
			showProgressDialog("加载中...");
			mControl.useYouHuiCard((String)v.getTag());
			break;
		default:
			break;
		}
	}
    
    private void getSaleCount(){
    	mControl.getSaleCountAsyn();
    }
    
    // ======================================= Callback ============================================= //
    
    public void showFavListCallback(){
        mAdapter.setItems(mControl.getModel().getGoodsList());
        mAdapter.notifyDataSetChanged();
    }
    
    public void showFavListExceptionCallback(){
    	mAdapter.setItems(null);
    	mAdapter.notifyDataSetChanged();
    }

    public void getSaleCountAsynCallback(){
    	SaleCount saleCount = mControl.getModel().getSaleCount();
    	if(saleCount!=null){
    		mFirstTitle.setTitle("已抢购" + "(" + saleCount.getGets()+ ")");
    		mSecondTitle.setTitle("已过期" + "(" + saleCount.getExpire()+ ")");
    		mThirdTttle.setTitle("已使用" + "(" + saleCount.getUsed()+ ")");
    	}
    }
    
    public void getSaleCountAsynExceptionCallback(){
    	
    }

	@Override
	public void onRefresh() {
		
	}
	
	public void useYouHuiCardCallback(){
		dismissDialog();
		mControl.getMySaleAsyn("gets", "1");
		getSaleCount();
		Toast.makeText(this, "使用成功", 0).show();
	}
	
	public void useYouHuiCardExceptionCallback(){
		dismissDialog();
		Toast.makeText(this, "加载失败", 0).show();
	}
	
	// =========================================== ProgressDialog ===================================================
	
	private ProgressDialog mProgressDialog;
	private void showProgressDialog(String message){
		if(mProgressDialog!=null && mProgressDialog.isShowing())
			mProgressDialog.dismiss();
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setTitle("提示");
		mProgressDialog.setMessage(message);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
	}
	
	private void dismissDialog(){
		if(mProgressDialog!=null && mProgressDialog.isShowing())
			mProgressDialog.dismiss();
	}
}
