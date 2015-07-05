package com.erban.module.user.center;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.util.AppUtil;
import com.erban.widget.TitleBar;

public class AboutActivity extends AbstractActivity implements View.OnClickListener{

	public static void startActivity(Activity ac){
		Intent intent = new Intent(ac,AboutActivity.class);
		ac.startActivity(intent);
//		ac.overridePendingTransition(R.anim.activity_slid_in_from_right, R.anim.activity_slid_out_no_change);
	}
	
	private TitleBar mTitleBar;
	
	private TextView mVersionTv;
	
	private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_layout);
		setUpView();
	}

	private void setUpView(){
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setBackgroundColor(Color.parseColor("#28b937"));
		mTitleBar.setTitle("关于");
		mTitleBar.findViewById(R.id.right_root).setVisibility(View.GONE);
		mTitleBar.setBackListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		mVersionTv  = (TextView) findViewById(R.id.version);
		mVersionTv.setText("版本" + AppUtil.getVersion(this));
	}
	
	private void setUpItem(ViewGroup itemGroup,int drawableResource,String title){
		ImageView icon = (ImageView) itemGroup.findViewById(R.id.icon);
		icon.setImageResource(drawableResource);
		TextView tV = (TextView) itemGroup.findViewById(R.id.title);
		tV.setText(title);
	}

    @Override
    public void onClick(View v) {
        String url = "";
        int id = v.getId();
    }

}
