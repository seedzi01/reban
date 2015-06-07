package com.erban.module.user;


import java.util.Map;
import java.util.Set;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.container.TabsActivity;
import com.erban.widget.TitleBar;
import com.erban.widget.TitleBar.Listener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class LoginAndRegisterActivity extends AbstractActivity
		implements Listener,View.OnClickListener{
	
	private boolean isFromRegister = false;
	@Deprecated
    public static void startActivity(Context context){
        Intent intent = new Intent(context,LoginAndRegisterActivity.class);
        context.startActivity(intent);
    }
    
    public static void startActivity(Activity ac,boolean isLogin){
        Intent intent = new Intent(ac,LoginAndRegisterActivity.class);
        intent.putExtra("is_login", isLogin);
        ac.startActivity(intent);
        ac.overridePendingTransition(R.anim.activity_slid_in_from_right, R.anim.activity_slid_out_no_change);
    }
    
    private final int REFRESH_TIEM = 1;
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case REFRESH_TIEM:
				if(msg.arg2<=0){
					mGetVerificationButton.setText(getResources().getString(R.string.get_verification_code));
					mGetVerificationButton.setEnabled(true);
					return;
				}
				mGetVerificationButton.setText(String.valueOf(msg.arg2) + "s");
				Message msgNew = Message.obtain();
				msgNew.arg1 = REFRESH_TIEM;
				mGetVerificationButton.setEnabled(false);
				msgNew.arg2 = msg.arg2 -1;
				mHandler.sendMessageDelayed(msgNew,1000);
				break;
			default:
				break;
			}
		};
	};
	
	LaunchListener LAUNCH_REGISTER = new LaunchListener(){
		@Override
		public void onLaunch() {
			clearMessge();
			if(!checkInputData4Registe()){
				Toast.makeText(LoginAndRegisterActivity.this, "请输入正确的数据", 0).show();
				return;
			}
			showProgressDialog("注册中...");
//			mControl.registeAsyn(mRegisterUserMobileEdit.getText().toString(),
//					mRegisterUserPasswordEdit.getText().toString(),
//					mRegisterUserVerificationEdit.getEditableText()
//							.toString());
		}
	};
	LaunchListener LAUNCH_LOGIN = new LaunchListener(){
		@Override
		public void onLaunch() {
			clearMessge();
			if(!checkInputData4Login()){
				Toast.makeText(LoginAndRegisterActivity.this, "请输入正确的数据", 0).show();
				return;
			}
			showProgressDialog("登录中...");
//			mControl.loginAsyn(mLoginUserMobileEdit.getText().toString(),
//					mLoginUserPasswordEdit.getText().toString());
		}
	};
	private LaunchListener mLaunchListener =  LAUNCH_LOGIN;
	
	
	
	private TitleBar mTitleBar;
	
	private TextView mLaunchButton;
	
	private ViewGroup mLoginInputLayout, mRegisterInputLayout;
	
	private View forgetPassword;
	
	private TextView mGetVerificationButton;
	/**注册手机号输入*/
	private EditText mRegisterUserMobileEdit;
	/**验证码输入*/
	private EditText mRegisterUserVerificationEdit;
	/**注册密码输入*/
	private EditText mRegisterUserPasswordEdit;
	
	/**注册手机号输入*/
	private EditText mLoginUserMobileEdit;
	/**注册密码号输入*/
	private EditText mLoginUserPasswordEdit;
	
	// ====================  sns 分享   ========================
	private ImageView mQqLogin;
	private ImageView mWeixinLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register_layout);
		initView();
	}

	private void initView(){
		
		mTitleBar = (TitleBar) findViewById(R.id.title_bar_layout);
		mTitleBar.setBackgroundColor(Color.parseColor("#28b937"));
		mTitleBar.setListener(LoginAndRegisterActivity.this);
		
		mLoginInputLayout = (ViewGroup) findViewById(R.id.login_input_layout);
		mRegisterInputLayout = (ViewGroup) findViewById(R.id.register_input_layout);
		mLaunchButton = (TextView) findViewById(R.id.launch);
		mLaunchButton.setOnClickListener(this);
		
		mGetVerificationButton =  (TextView) findViewById(R.id.get_verification);
		mGetVerificationButton.setOnClickListener(this);
		
		mLoginUserMobileEdit = (EditText) findViewById(R.id.login_user_mobile);
		mLoginUserPasswordEdit = (EditText) findViewById(R.id.login_user_password);
		
		mRegisterUserMobileEdit = (EditText) findViewById(R.id.register_user_mobile);
		mRegisterUserVerificationEdit = (EditText) findViewById(R.id.register_user_verification);
		mRegisterUserPasswordEdit = (EditText) findViewById(R.id.register_user_password);
		
		forgetPassword = findViewById(R.id.forget_password);
		forgetPassword.setOnClickListener(this);
		mTitleBar.getTitle().setTextColor(Color.parseColor("#ffffff"));
		if(getIntent().getBooleanExtra("is_login", true))
			mTitleBar.findViewById(R.id.login).performClick();
		else 
			mTitleBar.findViewById(R.id.register).performClick();
	}
	
	@Override
	public void switchLogin() {
		mLoginInputLayout.setVisibility(View.VISIBLE);
		mRegisterInputLayout.setVisibility(View.GONE);
		forgetPassword.setVisibility(View.VISIBLE);
		mLaunchListener  = LAUNCH_LOGIN;
		mLaunchButton.setText("登录");
	}

	@Override
	public void switchRegister() {
		mLoginInputLayout.setVisibility(View.GONE);
		mRegisterInputLayout.setVisibility(View.VISIBLE);
		forgetPassword.setVisibility(View.GONE);
		mLaunchListener  = LAUNCH_REGISTER;
		mLaunchButton.setText("注册");
	}

	@Override
	public void onClick(View v) {	
		int id = v.getId();
		switch (id) {
		case R.id.forget_password:
//			FindPasswordActivity.startActivity(LoginAndRegisterActivity.this);
			break;
		case R.id.launch:
//			InputUtils.hidSoftInput(LoginAndRegisterActivity.this);//强制关闭软键盘
			mLaunchListener.onLaunch();
			break;
		case	R.id.get_verification:
			getVerification(mRegisterUserMobileEdit.getText().toString());
			break;
		default:
			break;
		}
	}
	
	private void getVerification(String mobile){
		/*
		if(!MobileUtil.isMobileNO(mobile)){
			Toast.makeText(LoginAndRegisterActivity.this, "输入的号码格式有误", 0).show();
			return;
		}
		mControl.getVerificationCodeAsyn(mobile);
		Message msg = Message.obtain();
		msg.arg1 = REFRESH_TIEM;
		msg.arg2 = 60;
		mHandler.sendMessage(msg);
		*/
	}
	
	private void clearMessge(){
		mHandler.removeMessages(mHandler.obtainMessage().arg1);
		mGetVerificationButton.setText(getResources().getString(R.string.get_verification_code));
		mGetVerificationButton.setEnabled(true);
	}
	
	private boolean checkInputData4Registe(){
		/*
		if(!MobileUtil.isMobileNO(mRegisterUserMobileEdit.getText().toString()))
			return false;
		if(TextUtils.isEmpty(mRegisterUserPasswordEdit.getText().toString()) || TextUtils.isEmpty(mRegisterUserVerificationEdit.getText().toString()) )
			return false;
			*/
		return true;
	}
	
	private boolean checkInputData4Login(){
		/*
		if(!MobileUtil.isMobileNO(mLoginUserMobileEdit.getText().toString()))
			return false;
		if(TextUtils.isEmpty(mLoginUserMobileEdit.getText().toString()) )
			return false;
			*/
		return true;
	}
	
	private  interface LaunchListener{
		public void onLaunch();
	}
	
	// ===============================  Call Back =======================================
	
	public void getVerificationCodeAsynExceptionCallBack(){
		
	}
	public void getVerificationCodeAsynCallBack(){
		
	}
	
	public void registeAsynCallBack(){
//		dismissDialog();
//		Toast.makeText(LoginAndRegisterActivity.this, "注册成功", 0).show();
		/*
		mControl.loginAsyn(mRegisterUserMobileEdit.getText().toString(),
				mRegisterUserPasswordEdit.getText().toString());
		isFromRegister = true;
		*/
	}
	public void registeAsynExceptionCallBack(){
		dismissDialog();
		Toast.makeText(LoginAndRegisterActivity.this, "注册失败", 0).show();
	}
	
	public void loginAsynCallBack(){
		dismissDialog();
		if(isFromRegister){
			Toast.makeText(LoginAndRegisterActivity.this, "注册成功", 0).show();
			isFromRegister = false;
		}else{
			Toast.makeText(LoginAndRegisterActivity.this, "登录成功", 0).show();
		}
		TabsActivity.startActivity(LoginAndRegisterActivity.this);
		setResult(RESULT_OK);
		finish();
	}
	
	public void loginAsynExceptionCallBack(){
		dismissDialog();
		if(isFromRegister){
			Toast.makeText(LoginAndRegisterActivity.this, "注册失败", 0).show();
			isFromRegister = false;
		}else{
			Toast.makeText(LoginAndRegisterActivity.this, "登录失败", 0).show();
		}
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
