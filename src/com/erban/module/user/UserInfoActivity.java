package com.erban.module.user;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.WifiApplication;
import com.erban.bean.User;
import com.erban.bean.User.UserInfo;
import com.erban.module.user.control.UserInfoControl;
import com.erban.util.UserUtil;
import com.erban.widget.CircleImageView;
import com.erban.widget.TitleBar;
import com.nostra13.universalimageloader.core.ImageLoader;

public class UserInfoActivity extends AbstractActivity<UserInfoControl> implements View.OnClickListener{

	public static void startActivity(Activity ac){
		Intent intent = new Intent(ac,UserInfoActivity.class);
		ac.startActivity(intent);
		 ac.overridePendingTransition(R.anim.activity_slid_in_from_right, R.anim.activity_slid_out_no_change);
	}
	
	private TitleBar mTitlebar;
	
	private CircleImageView mUserIcon;
	
	private ProgressDialog mProgressDialog;
	
	private String iconPath;
	
	/**昵称*/
	private EditText nickNameEd;
	/**联系方式*/
	private EditText linkEd;
	/**身份证号*/
	private EditText shengFenZhengHaoEd;
	/**地址*/
	private EditText locationEd;
	/**用户头像*/
	private CircleImageView  userIcon;
	/**用户名*/
	private TextView userNameTv;
	/**vip说明*/
	private TextView vipTxtTv;
	/**Vip等级*/
//	private VipGradeView vipGrade;
	
	private TextView userTypeDesc;
	/**累计消费*/	
	private TextView costTv;
	private View loginOutBt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_layout);
		initView();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		ImageLoader.getInstance().displayImage(UserUtil.getUser().getUserInfo().getAvatar(),mUserIcon);
		UserInfo user = UserUtil.getUser().getUserInfo();
		initViewItem((ViewGroup)findViewById(R.id.item2), "昵称", user.getNickname());
		initViewItem((ViewGroup)findViewById(R.id.item3), "姓名", user.getUsername());
		initViewItem((ViewGroup)findViewById(R.id.item4), "性别", user.getSex());
		initViewItem((ViewGroup)findViewById(R.id.item5), "出生年月", user.getBirth());
		initViewItem((ViewGroup)findViewById(R.id.item6), "所在地", user.getAddress());
		initViewItem((ViewGroup)findViewById(R.id.item7), "手机", user.getLink());
		initViewItem((ViewGroup)findViewById(R.id.item8), "QQ", user.getQq());
	}
	
	private void initView(){
		mTitlebar = (TitleBar) findViewById(R.id.titlebar);
		mTitlebar.setTitle("用戶信息");
		mTitlebar.setBackgroundColor(Color.parseColor("#28b937"));
		mTitlebar.findViewById(R.id.right_root).setVisibility(View.GONE);
//		mTitlebar.setBackListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//			    if(!showDialog4UserUpload()){
//			        finish();
//			    }
//			}
//		});
//		findViewById(R.id.loginout).setOnClickListener(this);
		mUserIcon = (CircleImageView) findViewById(R.id.user_icon);
		mUserIcon.setOnClickListener(this);
		loginOutBt = findViewById(R.id.login_out);
		loginOutBt.setOnClickListener(this);
	}
	
	private void initViewItem(ViewGroup viewGroup,String tilte, String data){
		TextView title = (TextView) viewGroup.findViewById(R.id.title);
		TextView value = (TextView) viewGroup.findViewById(R.id.value);
		title.setText(tilte);
		value.setText(data);
		viewGroup.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		/*
		case R.id.loginout:
            AlertDialog.Builder builder = new Builder(this);
            builder.setMessage("您确认要登出吗?");
            builder.setTitle("提示");
            builder.setPositiveButton("确认", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    loginOut();
                }
            });
            builder.setNegativeButton("取消", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();

			break;*/
		case R.id.user_icon:
	         Intent intent = new Intent();  
             /* 开启Pictures画面Type设定为image */  
             intent.setType("image/*");  
             /* 使用Intent.ACTION_GET_CONTENT这个Action */  
             intent.setAction(Intent.ACTION_GET_CONTENT);   
             /* 取得相片后返回本画面 */  
             startActivityForResult(intent, 1);
			break;
		case R.id.item2:
			UserInfoModifyActivity.startActivity(this, "昵称", "nickname", UserUtil.getUser().getUserInfo().getNickname());
			break;
        case R.id.item3:
            UserInfoModifyActivity.startActivity(this, "姓名", "username", UserUtil.getUser().getUserInfo().getUsername());
            break;
        case R.id.item4:
            UserInfoModify4SexActivity.startActivity(this,UserUtil.getUser().getUserInfo().getGender());
            break;
        case R.id.item6:
            UserInfoModifyActivity.startActivity(this, "所在地", "addr", UserUtil.getUser().getUserInfo().getAddress());
            break;
        case R.id.item7:
            UserInfoModifyActivity.startActivity(this, "手机", "link", UserUtil.getUser().getUserInfo().getLink());
            break;
        case R.id.item8:
            UserInfoModifyActivity.startActivity(this, "QQ", "qq", UserUtil.getUser().getUserInfo().getQq());
            break;
        case R.id.login_out:
        	 AlertDialog.Builder builder = new Builder(this);
             builder.setMessage("您确认要登出吗?");
             builder.setTitle("提示");
             builder.setPositiveButton("确认", new OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                     loginOut();
                 }
             });
             builder.setNegativeButton("取消", new OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                 }
             });
             builder.create().show();
        	break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {  
            Uri uri = data.getData();  
            ContentResolver cr = this.getContentResolver();  
            Cursor cursor = cr.query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);  
            cursor.moveToFirst();  
            String path = cursor.getString(column_index);  
            iconPath = path;
            uploadIcon(path);
            
        }  
		super.onActivityResult(requestCode, resultCode, data);
	}
	// ===========================================  api ===================================================
	
	private void loginOut(){
		showProgressDialog("登出中...");
		mControl.loginOutAsyn();
	}
	
	private void uploadIcon(String filePath){
		showProgressDialog("上传图片中...");
		mControl.uploadIcon(filePath);
	}
	
	// =========================================== ProgressDialog ==========================================
	
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
	
	// =========================================== CallBack =================================================
	public void loginOutAsynCallBack(){
	    UserUtil.clearUser();
		dismissDialog();
		AbstractActivity.clearActivity();
		LoginAndRegisterActivity.startActivity(this);
	}
	
	public void loginOutAsynExceptionCallBack(){
		dismissDialog();
	}
	
	public void uploadIconCallBack(){
		dismissDialog();
		if(iconPath!=null){
	        Bitmap bitmap = BitmapFactory.decodeFile(iconPath);
			mUserIcon.setImageBitmap(bitmap);
			User user = UserUtil.getUser();
			user.getUserInfo().setAvatar(mControl.getModel().getUploadFileUrl());
			UserUtil.getUser().save(user);
		}
	}
	
	public void uploadIconExceptionCallBack(){
		dismissDialog();
	}
	
	public void uploadUserInfoCallBack(){
	    Toast.makeText(this, "用户信息更新成功", 0).show();
        dismissDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },500);
	}
	
	public void uploadUserInfoExceptionCallBack(){
	    Toast.makeText(this, "用户信息更新失败", 0).show();
        dismissDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },500);
	}
	/*
	@Override
	public void onBackPressed() {
		
	    if(showDialog4UserUpload()){
	    }else{
	        super.onBackPressed();
	    }
	}*/
	
    // =========================================== user upload =================================================
	/**
     * 检查用户信息是否修改
     */
	private boolean isUserMessgaeChanged(){
		/*
	    try {
	        User.UserInfo userInfo = UserUtil.getUser().getUserInfo();
	        if(!nickNameEd.getText().toString().trim().equals(userInfo.getUsername()))//姓名
	            return true;
	        if(!linkEd.getText().toString().trim().equals(userInfo.getMobile()))  //联系方式
	            return true;
	        if(!shengFenZhengHaoEd.getText().toString().trim().equals(userInfo.getIdcard()))//身份证 
	            return true;
	        if(!locationEd.getText().toString().trim().equals(userInfo.getAddress())) //地址
	            return true;
	        if(!TextUtils.isEmpty(mControl.getModel().getUploadFileUrl())) //头像
	            return true;
	        return false;
        } catch (Exception e) {
            return false;
        }*/
		return false;
	}

	
}
