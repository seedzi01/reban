package com.erban.widget;

import com.erban.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TitleBar extends FrameLayout implements View.OnClickListener{

	private TextView mTitleView;
	
	private ImageView mBackButton;
	
	private TextView mLoginButton;
	
	private TextView mRegisterButton;
	
	private Listener mListener;
	
	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TitleBar(Context context) {
		super(context);
	}
	
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setupViews();
    }
	
	private void setupViews(){
		mTitleView = (TextView) findViewById(R.id.title);
		mBackButton = (ImageView)findViewById(R.id.back);
		mLoginButton =  (TextView) findViewById(R.id.login);
		mRegisterButton =  (TextView) findViewById(R.id.register);
		mLoginButton.setOnClickListener(this);
		mRegisterButton.setOnClickListener(this);
	}
	
	public void setBackListener(View.OnClickListener listener){
		mBackButton.setOnClickListener(listener);
		mBackButton.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置titlebar标题
	 */
	public void setTitle(String title){
		mTitleView.setText(title);
		mTitleView.setVisibility(View.VISIBLE);
	}
	
	public TextView getTitle(){
		return mTitleView;
	}
	
	public void setListener(Listener listener){
		mListener = listener;
	}
	
	public static interface Listener{
		public void  switchLogin();
		public void switchRegister();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.login:
			mTitleView.setText("登录");
			mLoginButton.setVisibility(View.GONE);
			mRegisterButton.setVisibility(View.VISIBLE);
			if(mListener!=null)
				mListener.switchLogin();
			break;
		case R.id.register:
			mTitleView.setText("注册");
			mLoginButton.setVisibility(View.VISIBLE);
			mRegisterButton.setVisibility(View.GONE);
			if(mListener!=null)
				mListener.switchRegister();
			break;
		default:
			break;
		}
	}
}
