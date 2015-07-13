package com.erban.module.user.center;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.bean.Msg;
import com.erban.widget.TitleBar;

public class MessageDetailActivity extends AbstractActivity {

	public static void startActivity(Activity ac,Msg msg){
		Intent intent = new Intent(ac,MessageDetailActivity.class);
		intent.putExtra("msg", msg);
		ac.startActivity(intent);
	}
	
	TitleBar mTitlebar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_detail_layout);
		
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
    	mTitlebar.setTitle("消息详情");
    	
    	Msg msg = (Msg) getIntent().getSerializableExtra("msg");
    	if(msg==null)
    	    return;
    	TextView titleTv = (TextView) findViewById(R.id.title);
    	TextView fromTv =  (TextView) findViewById(R.id.from);
    	TextView  timeTv = (TextView) findViewById(R.id.time);
    	TextView contentTv = (TextView) findViewById(R.id.content);
    	titleTv.setText(msg.getTitle());
    	fromTv.setText(msg.getFrom());
    	timeTv.setText(msg.getUptime());
    	contentTv.setText(msg.getContent());
    	
	}
}
