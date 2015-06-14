package com.erban.module.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.widget.TitleBar;
import com.yuekuapp.BaseActivity;

public class UserInfoModifyActivity extends AbstractActivity implements View.OnClickListener,TextWatcher{

	public static void startActivity(Activity ac,String title){
		Intent intent = new Intent(ac,UserInfoModifyActivity.class);
		intent.putExtra("title", title);
		ac.startActivity(intent);
	}
	
	private TitleBar mTitleBar;
	private String mTitle;
	private EditText mEdit;
	private ImageView mCancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_modify_layout);
		mTitle = getIntent().getStringExtra("title");
		setUpView();
		initData();
	}
	
	private void setUpView(){
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle(mTitle);
		mTitleBar.setBackgroundColor(Color.parseColor("#28b937"));
		mTitleBar.findViewById(R.id.right_root).setVisibility(View.VISIBLE);
		mTitleBar.findViewById(R.id.finish).setVisibility(View.VISIBLE);
		mTitleBar.findViewById(R.id.login).setVisibility(View.GONE);
		mTitleBar.findViewById(R.id.register).setVisibility(View.GONE);
		mTitleBar.findViewById(R.id.back).setVisibility(View.VISIBLE);
		((ImageView)mTitleBar.findViewById(R.id.back)).setImageResource(R.drawable.white_left_arrow);
		
		mEdit = (EditText) findViewById(R.id.edit);
		mEdit.addTextChangedListener(this);
		mCancel = (ImageView) findViewById(R.id.cancel);
		mCancel.setOnClickListener(this);
	}
	
	private void initData(){
		
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.cancel:
			mEdit.setText("");
			break;
		default:
			break;
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		if(TextUtils.isEmpty(s.toString())){
			mCancel.setVisibility(View.GONE);
		}else{
			mCancel.setVisibility(View.VISIBLE);
		}
	}
	
}
