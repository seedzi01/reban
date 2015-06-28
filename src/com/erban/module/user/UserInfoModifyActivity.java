package com.erban.module.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.bean.User;
import com.erban.bean.User.UserInfo;
import com.erban.module.user.control.UserInfoControl;
import com.erban.util.UserUtil;
import com.erban.widget.TitleBar;

public class UserInfoModifyActivity extends AbstractActivity<UserInfoControl> implements View.OnClickListener,TextWatcher{
    @Deprecated
	public static void startActivity(Activity ac,String title){
		Intent intent = new Intent(ac,UserInfoModifyActivity.class);
		intent.putExtra("title", title);
		ac.startActivity(intent);
	}
   public static void startActivity(Activity ac,String title,String key,String value){
        Intent intent = new Intent(ac,UserInfoModifyActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("key", key);
        intent.putExtra("value", value);
        ac.startActivity(intent);
    }
	
	private TitleBar mTitleBar;
	private String mTitle;
	private String mValue;
	private String mKey;
	private EditText mEdit;
	private ImageView mCancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_modify_layout);
		mTitle = getIntent().getStringExtra("title");
		mKey = getIntent().getStringExtra("key");
		mValue = getIntent().getStringExtra("value");
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
		mTitleBar.setBackListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		((ImageView)mTitleBar.findViewById(R.id.back)).setImageResource(R.drawable.white_left_arrow);
		mTitleBar.findViewById(R.id.finish).setOnClickListener(this);
		
		mEdit = (EditText) findViewById(R.id.edit);
		mEdit.setText(mValue);
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
		case R.id.finish:
            String changedTxt = mEdit.getText().toString();
            if(TextUtils.isEmpty(changedTxt)){
                Toast.makeText(this, "内容不能为空", 0).show();
                return;
            }
            showDialog4UserUpload();
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
			if(s.toString().length() >15){
				mEdit.setText(s.toString().substring(0, 15));
				mEdit.setSelection(s.length()-1);
			}
		}
	}
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    /*
	    */
	}
	
	// =========================================== CallBack =================================================
	public void updateUserInfoCallBack(){
		User user = UserUtil.getUser();
		UserInfo userInfo = user.getUserInfo();
		if(mKey.equals("nickname")){
			userInfo.setNickname(mEdit.getText().toString());
		}else if(mKey.equals("username")){
			userInfo.setUsername(mEdit.getText().toString());
		}else if(mKey.equals("addr")){
			userInfo.setAddress(mEdit.getText().toString());
		}else if(mKey.equals("link")){
			userInfo.setLink(mEdit.getText().toString());
		}else if(mKey.equals("qq")){
			userInfo.setQq(mEdit.getText().toString());
		}
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
        
        builder.setPositiveButton("确认", new OnClickListener() {   
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mControl.updateUserInfo(mKey,mEdit.getText().toString());
                dialog.dismiss();    
                showProgressDialog("用户信息上传中...");
             }
        });  
        builder.setNegativeButton("取消", new OnClickListener() {   @Override
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
