package com.erban.module.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.bean.User;
import com.erban.bean.User.UserInfo;
import com.erban.module.user.control.UserInfoControl;
import com.erban.util.UserUtil;
import com.erban.widget.TitleBar;

public class UserInfoModify4SexActivity extends AbstractActivity<UserInfoControl> 
	implements OnClickListener{
	
	private static String SEX_NAN = "0";
	private static String SEX_NV = "1";
	private String mCurrentSex;
	
	public static void startActivity(Activity ac,String sex){
		Intent intent = new Intent(ac,UserInfoModify4SexActivity.class);
		intent.putExtra("sex", sex);
		ac.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_modify_sex_layout);
		setupViews();
	}
	
	private ViewGroup sevNanLayout;
	private ViewGroup sevNvLayout;
	private TitleBar mTitleBar;
	
	private void setupViews(){
		mTitleBar = (TitleBar) findViewById(R.id.titlebar);
		mTitleBar.setTitle("性别");
		mTitleBar.setBackgroundColor(Color.parseColor("#28b937"));
		mTitleBar.findViewById(R.id.right_root).setVisibility(View.VISIBLE);
		mTitleBar.findViewById(R.id.finish).setVisibility(View.VISIBLE);
		mTitleBar.findViewById(R.id.login).setVisibility(View.GONE);
		mTitleBar.findViewById(R.id.register).setVisibility(View.GONE);
		mTitleBar.findViewById(R.id.back).setVisibility(View.VISIBLE);
		mTitleBar.setBackListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		((ImageView)mTitleBar.findViewById(R.id.back)).setImageResource(R.drawable.white_left_arrow);
		mTitleBar.findViewById(R.id.finish).setOnClickListener(this);
		
		sevNanLayout = (ViewGroup) findViewById(R.id.sev_nan);
		sevNvLayout = (ViewGroup) findViewById(R.id.sev_nv); 
		sevNanLayout.setOnClickListener(this);
		sevNvLayout.setOnClickListener(this);
		
		mCurrentSex = getIntent().getStringExtra("sex");
		if(SEX_NAN.equals(mCurrentSex)){
			sevNanLayout.performClick();
		}else if(SEX_NV.equals(mCurrentSex)){
			sevNvLayout.performClick();
		}
	}

	@Override
	public void onClick(View v) {
		ImageView iv = null;
		int id = v.getId();
		switch (id) {
		case R.id.finish:
			showDialog4UserUpload();
			break;
		case R.id.sev_nan:
			iv = (ImageView) sevNanLayout.getChildAt(1);
			iv.setImageResource(R.drawable.sex_checked);
			iv = (ImageView) sevNvLayout.getChildAt(1);
			iv.setImageDrawable(null);
			mCurrentSex = SEX_NAN;
			break;
		case R.id.sev_nv:
			iv = (ImageView) sevNanLayout.getChildAt(1);
			iv.setImageDrawable(null);
			iv = (ImageView) sevNvLayout.getChildAt(1);
			iv.setImageResource(R.drawable.sex_checked);
			mCurrentSex = SEX_NV;
			break;
		default:
			break;
		}
	}
	
	// =========================================== CallBack =================================================
	public void updateUserInfoCallBack(){
		User user = UserUtil.getUser();
		UserInfo userInfo = user.getUserInfo();
		userInfo.setGender(mCurrentSex);
		User.save(user);
	    dismissDialog();
	    Toast.makeText(this, "修改成功", 0).show();
	    finish();
	}
	
	public void updateUserInfoExceptionCallBack(){
	    dismissDialog();
	    Toast.makeText(this, "修改失败", 0).show();
	    finish();
	}
		
	// =========================================== Dialog =================================================
	private ProgressDialog mProgressDialog;
	private boolean showDialog4UserUpload(){
        AlertDialog.Builder builder = new Builder(this);
        builder.setMessage("您确认修改信息吗?");  
        builder.setTitle("提示");  
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mControl.updateUserInfo("gender",mCurrentSex);
                dialog.dismiss();    
                showProgressDialog("用户信息上传中...");
			}
		});
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
                finish();
			}
		});
        builder.create().show();
        return false;
    }
		
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
